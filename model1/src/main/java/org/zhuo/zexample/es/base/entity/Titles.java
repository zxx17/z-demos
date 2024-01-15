package org.zhuo.zexample.es.base.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Titles)实体类
 *
 * @author makejava
 * @since 2024-01-14 15:55:49
 */
public class Titles implements Serializable {
    private static final long serialVersionUID = 567719593369578094L;
    
    private Integer empNo;
    
    private String title;
    
    private Date fromDate;
    
    private Date toDate;


    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

}

