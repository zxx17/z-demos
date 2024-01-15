package org.zhuo.zexample.es.base.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (DeptEmp)实体类
 *
 * @author makejava
 * @since 2024-01-14 15:55:22
 */
public class DeptEmp implements Serializable {
    private static final long serialVersionUID = -55458799477559607L;
    
    private Integer empNo;
    
    private String deptNo;
    
    private Date fromDate;
    
    private Date toDate;


    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
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

