package org.zhuo.zother.onlineshow.aliyunimm.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * oss文件链接表(TbOssUpload)实体类
 *
 * @author makejava
 * @since 2023-12-19 16:41:44
 */
public class TbOssUpload implements Serializable {
    /**
     * 主键
     */
    private Integer id;
    /**
     * oss文件路径
     */
    private String fileUrl;
    /**
     * imm自定义请求数据
     */
    private String immReqJson;
    /**
     * 上传时间
     */
    private Date createdTime;
    /**
     * 是否删除 0否 1是
     */
    private Integer isDeleted;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getImmReqJson() {
        return immReqJson;
    }

    public void setImmReqJson(String immReqJson) {
        this.immReqJson = immReqJson;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

}

