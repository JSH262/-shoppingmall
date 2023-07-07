package com.tjoeun.vo;

import java.sql.Timestamp;
public class UserVO {
    private String userId;
    private String userPassword;
    private String userEmail;
    private String userPhone;
    private String userType;
    private String userName;
    private String userUseYn;
    private Timestamp userCreateDate;
    private Timestamp userModifyDate;
    private String company_id;

    public UserVO() {
    }

    public UserVO(String userId, String userPassword, String userEmail, String userPhone, String userType, String userName, String company_id) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userType = userType;
        this.userName = userName;
        this.company_id = company_id;
    }
    
    public UserVO(String userId, String userPassword, String userEmail, String userPhone, String userType, String userName) {
    	this.userId = userId;
    	this.userPassword = userPassword;
    	this.userEmail = userEmail;
    	this.userPhone = userPhone;
    	this.userType = userType;
    	this.userName = userName;
    }
    
    public UserVO(String userId, String userPassword) {
    	this.userId = userId;
    	this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserUseYn() {
        return userUseYn;
    }

    public void setUserUseYn(String userUseYn) {
        this.userUseYn = userUseYn;
    }

    public Timestamp getUserCreateDate() {
        return userCreateDate;
    }

    public void setUserCreateDate(Timestamp userCreateDate) {
        this.userCreateDate = userCreateDate;
    }

    public Timestamp getUserModifyDate() {
        return userModifyDate;
    }

    public void setUserModifyDate(Timestamp userModifyDate) {
        this.userModifyDate = userModifyDate;
    }

    public String getcompany_id() {
        return company_id;
    }

    public void setcompany_id(String company_id) {
        this.company_id = company_id;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "userId='" + userId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userType='" + userType + '\'' +
                ", userName='" + userName + '\'' +
                ", userUseYn='" + userUseYn + '\'' +
                ", userCreateDate=" + userCreateDate +
                ", userModifyDate=" + userModifyDate +
                ", company_id='" + company_id + '\'' +
                '}';
    }
}
