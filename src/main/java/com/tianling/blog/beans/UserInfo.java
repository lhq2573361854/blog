package com.tianling.blog.beans;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;


import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tianling
 * @since 2020-05-08
 */
public class UserInfo extends Model<UserInfo> {

    private static final long serialVersionUID=1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    private String userName;

    private String userPhone;

    private String userAccount;

    private String userPassword;
    @TableLogic
    private String userMark;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserMark() {
        return userMark;
    }

    public void setUserMark(String userMark) {
        this.userMark = userMark;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
        "userId=" + userId +
        ", userName=" + userName +
        ", userPhone=" + userPhone +
        ", userAccount=" + userAccount +
        ", userPassword=" + userPassword +
        ", userMark=" + userMark +
        "}";
    }
}
