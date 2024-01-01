package org.zhuo.zexample.minio.entity;

import lombok.Data;

import java.awt.*;
import java.io.Serializable;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/31
 * <p>
 *
 * </p>
 */

@Data
public class MinIOReq implements Serializable {

    private String bucketName;

    private String objectName;

}
