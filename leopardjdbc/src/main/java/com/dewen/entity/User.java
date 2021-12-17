package com.dewen.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /**
     * id
     */
    private long id;

    /**
     * 账号
     */
    private String account;
    /**
     * 姓名
     */
    private String name;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用于子账号
     */
    private long parentId;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 用户的头像
     */
    private String headpic;

    /**
     * 密码随机码
     */
    private String salt;

    /**
     * 用户密码修改时间
     */
    private Date pwdUpdateTime;

    /**
     * 电子邮箱
     */
    private String email;

    private String remark;

    private String post;

    private String ssoUid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getPwdUpdateTime() {
        return pwdUpdateTime;
    }

    public void setPwdUpdateTime(Date pwdUpdateTime) {
        this.pwdUpdateTime = pwdUpdateTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getSsoUid() {
        return ssoUid;
    }

    public void setSsoUid(String ssoUid) {
        this.ssoUid = ssoUid;
    }
}
