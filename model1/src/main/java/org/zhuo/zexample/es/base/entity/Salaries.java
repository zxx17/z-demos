package org.zhuo.zexample.es.base.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Salaries)实体类
 *
 * @author makejava
 * @since 2024-01-14 15:55:23
 */
public class Salaries implements Serializable {
    private static final long serialVersionUID = 780230073262447209L;
    
    private Integer empNo;
    
    private Integer salary;
    
    private Date fromDate;
    
    private Date toDate;


    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
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

