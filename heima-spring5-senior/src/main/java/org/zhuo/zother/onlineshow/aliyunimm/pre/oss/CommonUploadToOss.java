package org.zhuo.zother.onlineshow.aliyunimm.pre.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zhuo.zother.onlineshow.aliyunimm.pre.AliYunAssessKey;

import java.util.*;

import static org.zhuo.zother.onlineshow.aliyunimm.pre.AliYunAssessKey.ALIYUN_OSS_ENDPOINT;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/19
 * <p>
 *  上传到阿里云oss的公共代码
 * </p>
 */

@Slf4j
@Component
public class CommonUploadToOss {

    /**
     * container
     */
    private final Map<String, Object> resultMap = new HashMap<>();

    /**
     * 值注入
     * @param aliYunAssessKey 密钥文件
     */
    @Autowired
    public void autowire(AliYunAssessKey aliYunAssessKey) {
        if(log.isInfoEnabled()){
            log.info("package org.zhuo.zother.onlineshow.aliyunimm.pre.oss.CommonUploadToOss执行依赖注入: {}", aliYunAssessKey);
        }
        resultMap.put("aliYunAssessKey",aliYunAssessKey);
    }


    /**
     * 获取oss实例
     */
    public Map<String, Object> uploadOssNecessary()  {
        if(log.isInfoEnabled()){
            log.info("=====CommonUploadToOss.uploadOssNecessary.enable=====");
        }
        AliYunAssessKey aliYunAssessKey = (AliYunAssessKey) resultMap.get("aliYunAssessKey");
        // 创建OSSClient实例
        try {
            log.info("创建OSSClient实例");
            OSS ossClient = new OSSClientBuilder().build(ALIYUN_OSS_ENDPOINT, aliYunAssessKey.getACCESSKEYID(), aliYunAssessKey.getACCESSKEYSECRET());
            resultMap.put("ossClient",ossClient);
        }catch (Exception e){
            log.error("创建OSSClient实例失败: {}",e.getMessage());
            throw new  RuntimeException("=====CommonUploadToOss.uploadOssNecessary.runtimeException=====");
        }
        // 创建上传文件的元信息，可以通过文件元信息设置HTTP header(设置了才能通过返回的链接直接访问)。
        ObjectMetadata objectMetadata = new ObjectMetadata();
//        objectMetadata.setContentType("image/jpg");
        resultMap.put("objectMetadata",objectMetadata);
        log.info("=====CommonUploadToOss.uploadOssNecessary.end=====");
        return resultMap;
    }

}
