package org.zhuo.zexample.bigfileupload.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zhuo.zexample.bigfileupload.entity.FileMergeReq;
import org.zhuo.zexample.bigfileupload.entity.FileUploadReq;
import org.zhuo.zexample.zutils.FileNameUtil;
import org.zhuo.zexample.zutils.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/15
 * <p>
 *
 * </p>
 */
@Slf4j
@Service
public class FileUploadService {

    private static final String folderPath = System.getProperty("user.dir") +"/upload-file";


    public ResponseEntity<Object> fileUpload(FileUploadReq fileUploadReq){
        File temporaryFolder = new File(folderPath);
        File temporaryFile = new File(folderPath + "/" + fileUploadReq.getHash());
        //如果文件夹不存在则创建
        if (!temporaryFolder.exists()) {
            temporaryFolder.mkdirs();
        }
        //如果文件存在则删除
        if (temporaryFile.exists()) {
            temporaryFile.delete();
        }
        MultipartFile file = fileUploadReq.getFile();
        try {
            file.transferTo(temporaryFile);
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok("success");
    }

    public ResponseEntity<Object> merge(FileMergeReq fileMergeReq) {
        String finalFilename = fileMergeReq.getFilename();
        File folder = new File(folderPath);
        //获取暂存切片文件的文件夹中的所有文件
        File[] files = folder.listFiles();
        //合并的文件
        File finalFile = new File(folderPath + "/" + finalFilename);
        int lastDotIndex = finalFilename.lastIndexOf(".");
        String finalFileMainName="";
        if (lastDotIndex >= 0) {
            finalFileMainName = finalFilename.substring(0, lastDotIndex);
        }
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(finalFile, true);
            List<File> list = new ArrayList<>();
            for (File file : files) {
                String filename = FileNameUtil.mainName(file);
                //判断是否是所需要的切片文件
                if (StringUtils.equals(filename, finalFileMainName)) {
                    list.add(file);
                }
            }
            //如果服务器上的切片数量和前端给的数量不匹配
            if (fileMergeReq.getFileNum() != list.size()) {
                return ResponseEntity.badRequest().body("文件缺失，请重新上传");
            }
            //根据切片文件的下标进行排序
            List<File> fileListCollect = list.parallelStream().sorted(((file1, file2) -> {
                String filename1 = FileNameUtil.extName(file1);
                String filename2 = FileNameUtil.extName(file2);
                return filename1.compareTo(filename2);
            })).toList();
            //根据排序的顺序依次将文件合并到新的文件中
            for (File file : fileListCollect) {
                inputStream = new FileInputStream(file);
                int temp = 0;
                byte[] byt = new byte[2 * 1024 * 1024];
                while ((temp = inputStream.read(byt)) != -1) {
                    outputStream.write(byt, 0, temp);
                }
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null){
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outputStream != null){
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 产生的文件大小和前端一开始上传的文件不一致
        if (finalFile.length() != Long.parseLong(fileMergeReq.getFileSize())) {
            return ResponseEntity.badRequest().body("上传文件大小不一致");
        }

        return  ResponseEntity.ok().body("上传成功");
    }

}
