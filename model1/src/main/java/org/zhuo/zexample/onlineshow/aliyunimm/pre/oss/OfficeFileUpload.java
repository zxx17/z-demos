package org.zhuo.zexample.onlineshow.aliyunimm.pre.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zhuo.zexample.onlineshow.aliyunimm.pre.handler.FileTypeEnum;
import org.zhuo.zexample.onlineshow.aliyunimm.pre.handler.FileTypeHandler;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static org.zhuo.zexample.onlineshow.aliyunimm.pre.AliYunAssessKey.ALIYUN_OSS_BUCKETNAME;
import static org.zhuo.zexample.onlineshow.aliyunimm.pre.AliYunAssessKey.ALIYUN_OSS_ENDPOINT;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/19
 * <p>
 *
 * </p>
 */

@Slf4j
@Service
public class OfficeFileUpload implements FileTypeHandler {

    @Resource
    private CommonUploadToOss commonUploadToOss;


    @Override
    public FileTypeEnum getFileType() {
        return FileTypeEnum.OFFICE_FILE;
    }

    /**
     * 办公文件的上传 office-type-file
     * @return url
     */
    @Override
    public String upload(MultipartFile file) {
        Map<String, Object> commonMap = commonUploadToOss.uploadOssNecessary();
        OSS ossClient = (OSS) commonMap.get("ossClient");
        ObjectMetadata objectMetadata = (ObjectMetadata) commonMap.get("objectMetadata");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        // 获取文件名
        String fileName = file.getOriginalFilename();
//        // 获取文件后缀名
//        assert fileName != null;
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));
//        // 最后上传生成的文件名
//        String finalFileName = System.currentTimeMillis() + "" + new SecureRandom().nextInt(0x0400) + suffixName;
        // oss中的文件夹名
        String objectName = FileTypeEnum.OFFICE_FILE.getDesc() + sdf.format(new Date()) + "/" + fileName;

        // 文件上传
        try {
            ossClient.putObject(ALIYUN_OSS_BUCKETNAME, objectName, new ByteArrayInputStream(file.getBytes()), objectMetadata);
            // 拼接URL
            String url = "https://" + ALIYUN_OSS_BUCKETNAME + "." + ALIYUN_OSS_ENDPOINT + "/" + objectName;
            ossClient.shutdown();
            return url;
        } catch (Exception e) {
            log.error("OfficeFileUpload.upload.error {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }


}
