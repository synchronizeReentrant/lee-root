package com.lee.demo.po;

import com.lee.demo.po.base.BasePo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Table(name="sys_user")
public class User extends BasePo {
    @Id
    private String id;
    @Column
    private String organizationId;
    @Column
    private String name;
    @Column
    private Date birthDay;
    @Column
    private String address;
    @Column
    private String educationBackgroud;
    @Column
    private String idNumber;
    @Column
    private String mobile;

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducationBackgroud() {
        return educationBackgroud;
    }

    public void setEducationBackgroud(String educationBackgroud) {
        this.educationBackgroud = educationBackgroud;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                "organizationId='" + organizationId + '\'' +
                ", name='" + name + '\'' +
                ", birthDay=" + birthDay +
                ", address='" + address + '\'' +
                ", educationBackgroud='" + educationBackgroud + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", mobile='" + mobile + '\'' +
                ", createTime='" + getCreatTime() + '\'' +
                ", updateTime='" + getUpdateTime() + '\'' +
                '}';
    }
}
