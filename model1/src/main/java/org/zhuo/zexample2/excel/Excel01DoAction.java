package org.zhuo.zexample2.excel;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Xinxuan Zhuo
 * @version 2024/4/17
 * <p>
 *
 * </p>
 */

@CrossOrigin
@SuppressWarnings("ALL")
@Slf4j
@RestController
@RequestMapping("/excel01")
public class Excel01DoAction {

    /**
     * 对外暴露接口
     */
    @PostMapping
    public ResponseEntity<?> excel01Api(@RequestParam("file") MultipartFile file,
                                        @RequestParam(value = "date",defaultValue = "") String date)
            throws IOException {
        if ("".equals(date)){
            date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        List<ExcelActionBO> list = getListInFile(file, date);
        File resultFile = null;
        try{
          File outFile = export(list);
          FileInputStream fis = new FileInputStream(outFile);
          Workbook workbook = WorkbookFactory.create(fis);
          fis.close();
          int numberOfSheets = workbook.getNumberOfSheets();
          resultFile = removeEmptyRows(outFile, numberOfSheets);
          // 使用FileSystemResource封装文件，并设置HTTP头信息
          FileSystemResource resource = new FileSystemResource(resultFile);
          HttpHeaders headers = new HttpHeaders();
          headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
          headers.setContentDispositionFormData("attachment", resultFile.getName());
          // 返回响应实体
          return ResponseEntity.ok()
                  .headers(headers)
                  .contentLength(resultFile.length())
                  .body(resource);
      }catch (Exception e){
          return  ResponseEntity.ok("出错了，联系管理员");
      }
    }


    /**
     * part01 构建实体列表
     */
    private List<ExcelActionBO> getListInFile(MultipartFile file, String date) {
        // 实体列表
        List<ExcelActionBO> dealInfoList = new ArrayList<>();
        // 逐行读取上传的文档
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            ExcelActionBO dealInfo = null;
            while ((line = reader.readLine()) != null) {
                // 判断是否是新的编号行
                if (line.matches("\\d+：.*") || line.matches("\\d+:.*")) {
                    // 如果已经有上一个dealInfo，则将其添加到列表中
                    if (dealInfo != null) {
                        dealInfoList.add(dealInfo);
                    }
                    // 创建新的dealInfo对象
                    dealInfo = new ExcelActionBO();
                    dealInfo.setTime(date);
                    String[] parts = line.split("：", 2);
                    if (parts.length != 2) {
                        parts = line.split(":", 2);
                        if (parts.length != 2) {
                            throw new RuntimeException("文件格式不正确");
                        }
                    }
                    // key是id
                    String key = parts[0].trim();
                    dealInfo.setId(key);
                    // 这里的value要过滤出成交店-成交员-楼号-房号-楼盘位置作为工作表
                    String value = parts[1].trim();
                    doAction01(value, dealInfo);
                } else if (!line.trim().isEmpty() && checkStrSplit2(line)) { // 忽略空行
                    // 解析其他信息并设置到dealInfo对象中
                    String[] parts = line.split("：", 2);
                    if (parts.length != 2) {
                        parts = line.split(":", 2);
                        if (parts.length != 2) {
                            throw new RuntimeException("文件格式不正确");
                        }
                    }
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    switch (key) {
                        case "面积":
                            dealInfo.setSize(value);
                            break;
                        case "单价":
                            dealInfo.setMonoValent(value);
                            break;
                        case "成交价":
                            dealInfo.setTotalPrice(value);
                            break;
                        case "装修情况":
                            dealInfo.setFixInfo(value);
                            break;
                    }
                }
            }
            // 添加最后一个dealInfo对象到列表中
            if (dealInfo != null) {
                dealInfoList.add(dealInfo);
            }
        } catch (Exception e) {
            throw new RuntimeException("服务器异常，联系管理员");
        }
        return dealInfoList;
    }

    /**
     * part02 根据实体列表生成excel
     */
    private File export(List<ExcelActionBO> dataList) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Map<String, Integer> sheetMap = new HashMap<>();
        int sheetPoint = 0;
        // 创建每个sheet
        for (ExcelActionBO sheetData : dataList) {
            Class<?> clazz = sheetData.getClass();
            Sheet sheet = workbook.getSheet(sheetData.getBuildAddress());
            int writeIndex = 1;
            if (sheet == null) {
                sheet = workbook.createSheet(sheetData.getBuildAddress());
                sheetMap.put(sheet.getSheetName(), sheetPoint++);
            }
            // 获取列宽度和注解信息
            Field[] fields = clazz.getDeclaredFields();
            int[] columnWidths = getColumnWidths(fields);
            String[] headerNames = getHeaderNames(fields);
            // 设置列宽
            for (int i = 0; i < columnWidths.length; i++) {
                sheet.setColumnWidth(i, columnWidths[i]);
            }
            // 创建表头行
            Row headerRow = sheet.createRow(0);
            int point = 0;
            for (int i = 0; i < headerNames.length; i++) {
                if (!headerNames[i].equals("暂无")) {
                    Cell cell = headerRow.createCell(point);
                    cell.setCellValue(headerNames[i]);
                    point++;
                }
            }
            List<ExcelActionDTO> datas = BeanUtil.copyToList(dataList, ExcelActionDTO.class);
            // 填充数据
            for (int i = 0; i < datas.size(); i++) {
                ExcelActionDTO bt = datas.get(i);
                ExcelActionBO item = BeanUtil.copyProperties(bt, ExcelActionBO.class);
                int targetSheetIndex = 0;
                if (sheetMap.containsKey(dataList.get(i).getBuildAddress())) {
                    targetSheetIndex = sheetMap.get(dataList.get(i).getBuildAddress());
                } else {
                    continue;
                }
                Sheet targetSheet = workbook.getSheetAt(targetSheetIndex);
                // 填充行 单元格数据
                Row dataRow = targetSheet.createRow(writeIndex++);
                for (int j = 2; j < fields.length; j++) {
                    Field field = fields[j];
                    field.setAccessible(true);
                    try {
                        Object value = field.get(item);
                        if (value != null) {
                            Cell cell = dataRow.createCell(j - 2);
                            cell.setCellValue(value.toString());
                        }
                    } catch (IllegalAccessException e) {
                        log.error(e.getMessage());
                        throw new RuntimeException("服务器异常，联系管理员");
                    }
                }
            }
        }
        File tempFile = new File(System.getProperty("user.dir") +"/"+  LocalDate.now().toString() + UUID.randomUUID()+".xlsx");
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException("服务器异常，联系管理员");
        } finally {
            workbook.close();
        }

        return tempFile;
    }


    /**
     * util01 行根据冒号分割，分不成两半返回false
     */
    private boolean checkStrSplit2(String line) {
        try {
            String[] parts = line.split("：", 2);
            if (parts.length != 2) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    /**
     * util02 成交店-成交员-楼号-房号-楼盘位置作为工作表
     */
    private void doAction01(String targetStr, ExcelActionBO targetObj) {
        if (targetStr.contains("恭喜")) {
            targetStr = targetStr.substring(2);
        }
        int storeIndex = targetStr.indexOf("店");
        String storeName = targetStr.substring(0, storeIndex+1);

        int employIndex = targetStr.indexOf("成交");
        String employName = targetStr.substring(storeIndex + 1, employIndex);

        int subBuildInfoIndex = findFirstAlphanumericIndex(targetStr);
        String location = targetStr.substring(employIndex+2, subBuildInfoIndex);

        int buildingEndIndex = targetStr.indexOf("#");
        String buildId = targetStr.substring(subBuildInfoIndex, buildingEndIndex+1);
        if (buildId.contains("#")){
            buildId = buildId.replace("#", "幢");
        }
        String roomId = targetStr.substring(buildingEndIndex+1);

        targetObj.setBuildId(buildId);
        targetObj.setBuildAddress(location);
        targetObj.setRoomId(roomId);
        targetObj.setDealEmploy(employName);
        targetObj.setDealAddress(storeName);

    }


    /**
     * util03
     * 获取列宽
     *
     * @param fields 字段数组
     * @return 列宽度数组
     */
    private  int[] getColumnWidths(Field[] fields) {
        // 从注解中获取列宽度信息
        int[] columnWidths = new int[fields.length];
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            Excel annotation = field.getAnnotation(Excel.class);
            if (annotation != null) {
                columnWidths[i] = annotation.width();
            } else {
                columnWidths[i] = 4000;
            }
        }
        return columnWidths;
    }

    /**
     * util04
     * 获取表头名称
     *
     * @param fields 字段数组
     * @return 表头名称数组
     */
    private  String[] getHeaderNames(Field[] fields) {
        // 从注解中获取表头名称信息
        String[] headerNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            Excel annotation = field.getAnnotation(Excel.class);
            if (annotation != null) {
                headerNames[i] = annotation.columnName();
            } else {
                headerNames[i] = "暂无";
            }
        }
        return headerNames;
    }

    /**
     * util05
     * 或者字符串中第一个数字或字母的索引位置
     */
    private  int findFirstAlphanumericIndex(String input) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.start();
        } else {
            return -1;
        }
    }

    /**
     * util06
     * 删除空行
     */
    private  File removeEmptyRows(File inputFile, int numberOfSheets) {
        try {
            Workbook workbook = WorkbookFactory.create(inputFile);
            Workbook outputWorkbook = WorkbookFactory.create(true); // 创建一个新的工作簿

            // 对每个工作表进行处理
            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                Sheet outputSheet = outputWorkbook.createSheet(sheet.getSheetName());

                // 循环遍历工作表的行
                for (int rowNum = sheet.getFirstRowNum(); rowNum <= sheet.getLastRowNum(); rowNum++) {
                    Row row = sheet.getRow(rowNum);
                    if (row != null) {
                        // 检查每行中的单元格是否都为空
                        boolean isRowEmpty = true;
                        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
                            Cell cell = row.getCell(cellNum);
                            if (cell != null && cell.getCellType() != CellType.BLANK) {
                                isRowEmpty = false;
                                break;
                            }
                        }
                        // 如果该行不为空，则复制到输出工作表中
                        if (!isRowEmpty) {
                            Row outputRow = outputSheet.createRow(outputSheet.getLastRowNum() + 1);
                            for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
                                Cell cell = row.getCell(cellNum);
                                if (cell != null) {
                                    Cell outputCell = outputRow.createCell(cellNum);
                                    outputCell.setCellValue(getCellValueAsString(cell));
                                }
                            }
                        }
                    }
                }
            }

            File tempFile = new File(System.getProperty("user.dir") +"/"+  LocalDate.now().toString() + UUID.randomUUID().toString().substring(0,4)+".xlsx");
            FileOutputStream fos = new FileOutputStream(tempFile);
            outputWorkbook.write(fos);
            fos.close();
           log.info("空行已移除并保存到临时文件: {}" , tempFile.getAbsolutePath());
            return tempFile;
        } catch (IOException | EncryptedDocumentException ex) {
            log.error(ex.getMessage());
            throw new RuntimeException("出错了，联系管理员");
        }
    }

    /**
     * utils07
     *  获取单元格的值并转换为字符串
     */
    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }

}


/**
 * 业务实体类
 */
@Data
class ExcelActionBO {
    private String id;
    private String buildAddress;
    @Excel(index = 1, columnName = "日期")
    private String time;

    @Excel(index = 2, columnName = "楼号")
    private String buildId;

    @Excel(index = 3, columnName = "房号")
    private String roomId;

    @Excel(index = 4, columnName = "装修")
    private String fixInfo;

    @Excel(index = 5, columnName = "面积")
    private String size;

    @Excel(index = 6, columnName = "单价")
    private String monoValent;

    @Excel(index = 7, columnName = "总价")
    private String totalPrice;

    @Excel(index = 8, columnName = "成交店")
    private String dealAddress;

    @Excel(index = 9, columnName = "成交员")
    private String dealEmploy;

    @Excel(index = 10, columnName = "备注")
    private String remark;

}

@Data
class ExcelActionDTO {

    @Excel(index = 1, columnName = "日期")
    private String time;

    @Excel(index = 2, columnName = "楼号")
    private String buildId;

    @Excel(index = 3, columnName = "房号")
    private String roomId;

    @Excel(index = 4, columnName = "装修")
    private String fixInfo;

    @Excel(index = 5, columnName = "面积")
    private String size;

    @Excel(index = 6, columnName = "单价")
    private String monoValent;

    @Excel(index = 7, columnName = "总价")
    private String totalPrice;

    @Excel(index = 8, columnName = "成交店")
    private String dealAddress;

    @Excel(index = 9, columnName = "成交员")
    private String dealEmploy;

    @Excel(index = 10, columnName = "备注")
    private String remark;

}