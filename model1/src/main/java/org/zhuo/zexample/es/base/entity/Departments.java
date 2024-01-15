package org.zhuo.zexample.es.base.entity;

import java.io.Serializable;

/**
 * (Departments)实体类
 *
 * @author makejava
 * @since 2024-01-14 15:55:20
 */
public class Departments implements Serializable {
    private static final long serialVersionUID = 204704393586107024L;
    
    private String deptNo;
    
    private String deptName;


    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

}

