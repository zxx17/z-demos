package org.zhuo.zexample.es.base.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Employees)实体类
 *
 * @author makejava
 * @since 2024-01-14 15:55:23
 */
public class Employees implements Serializable {
    private static final long serialVersionUID = -88839435378609487L;
    
    private Integer empNo;
    
    private Date birthDate;
    
    private String firstName;
    
    private String lastName;
    
    private Object gender;
    
    private Date hireDate;


    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

}

