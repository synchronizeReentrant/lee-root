package com.lee.demo.po;

import com.lee.demo.po.base.BasePo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="sys_account")
public class Account extends BasePo {
    @Id
    private String id;
    @Column
    private String userId;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String salt;
    @Column
    private Boolean locked = new Boolean(false);
    @Column
    private Boolean disabled = new Boolean(false);

    public String getUserId() {
        return userId;
    }

    public String getCredentialsSalt() {
        return username + salt;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id+ '\'' +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", locked=" + locked +
                ", disabled=" + disabled +
                ", createTime=" + getCreatTime() +
                ", updateTime=" + getUpdateTime() +
                '}';
    }
}
