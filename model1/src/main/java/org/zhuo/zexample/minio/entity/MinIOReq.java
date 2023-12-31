package org.zhuo.zexample.minio.entity;

import lombok.Getter;

import java.awt.*;
import java.io.Serializable;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/31
 * <p>
 *
 * </p>
 */

@Getter
public class MinIOReq implements Serializable {

    private String bucketName;

    private String objectName;

    private String fileName;

    private List content;

    //这里就不抽了
    private String pageNo;
    private String pageSize;

}
