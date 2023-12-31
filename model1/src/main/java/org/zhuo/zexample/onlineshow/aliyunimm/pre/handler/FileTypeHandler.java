package org.zhuo.zexample.onlineshow.aliyunimm.pre.handler;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/19
 * <p>
 *
 * </p>
 */

public interface FileTypeHandler {

    /**
     * 获取文件类型
     */
    FileTypeEnum getFileType();

    /**
     * 上传文件到oss
     * @param file 要上传的文件
     * @return 文件在oss的url
     */
    String upload(MultipartFile file);
}
