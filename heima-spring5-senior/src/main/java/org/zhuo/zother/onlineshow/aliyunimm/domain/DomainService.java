package org.zhuo.zother.onlineshow.aliyunimm.domain;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zhuo.zother.onlineshow.aliyunimm.pre.handler.FileTypeHandler;
import org.zhuo.zother.onlineshow.aliyunimm.pre.handler.FileUploadModelFactory;

import javax.annotation.Resource;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/19
 * <p>
 *
 * </p>
 */

@Service
public class DomainService {

    @Resource
    private FileUploadModelFactory factory;

    /**
     * 文件上传
     * @param file 文件
     * @param fileType 类型码
     */
    public void uploadOss(MultipartFile file, Integer fileType) {
        FileTypeHandler uploadModel = factory.getModelByCode(fileType);
        String ossUrl = uploadModel.upload(file);

        //TODO 数据持久化  异步文件冗余存储 MQ通知
    }


}
