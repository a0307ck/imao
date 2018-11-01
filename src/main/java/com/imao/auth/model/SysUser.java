package com.imao.auth.model;

import java.util.Date;

public class SysUser {
    /**
     * 
     */
    private Long id;

    /**
     * 登录名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 1男0女
     */
    private Integer sex;

    /**
     * 注册时间
     */
    private Date registerDate;

    /**
     * 1正常2禁用3删除
     */
    private Integer status;

    /**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 登录名
     * @return user_name 登录名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 登录名
     * @param userName 登录名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 密码
     * @return pass_word 密码
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * 密码
     * @param passWord 密码
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * 昵称
     * @return nick_name 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 昵称
     * @param nickName 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 1男0女
     * @return sex 1男0女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 1男0女
     * @param sex 1男0女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 注册时间
     * @return register_date 注册时间
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * 注册时间
     * @param registerDate 注册时间
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * 1正常2禁用3删除
     * @return status 1正常2禁用3删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 1正常2禁用3删除
     * @param status 1正常2禁用3删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}