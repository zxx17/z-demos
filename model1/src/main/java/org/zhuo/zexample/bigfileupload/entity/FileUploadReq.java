package org.zhuo.zexample.bigfileupload.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/15
 * <p>
 *
 * </p>
 */

@Data
public class FileUploadReq implements Serializable {


    //切片的文件
    private MultipartFile file;

    //切片的文件名称
    private String hash;

    //原文件名称
    private  String filename;
}


