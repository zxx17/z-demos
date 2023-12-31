package org.zhuo.zexample.bigfileupload.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/15
 * <p>
 *
 * </p>
 */

@Data
public class FileMergeReq implements Serializable {


    //文件名
    private String filename;

    //切片数量
    private int fileNum;

    //文件大小
    private String fileSize;
}
