package com.imao.auth.model;

import java.util.Date;

public class SysLog {
    /**
     * 日志ID
     */
    private Integer id;

    /**
     * 账户名
     */
    private String userName;

    /**
     * 动态码
     */
    private String codeSys;

    /**
     * 手机
     */
    private String iphone;

    /**
     * 登录IP
     */
    private String loginIp;

    /**
     * 浏览器类型
     */
    private String browerType;

    /**
     * 修改内容
     */
    private String contentChange;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 系统角色
     */
    private String sysRole;

    /**
     * 0为全局，1为定制
     */
    private Integer permission;

    /**
     * 0为正常，1为禁用
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 车队ID
     */
    private Long teamId;

    /**
     * 操作内容
     */
    private String contect;

    /**
     * 操作模块
     */
    private String module;

    /**
     * 日志ID
     * @return id 日志ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 日志ID
     * @param id 日志ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 账户名
     * @return user_name 账户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 账户名
     * @param userName 账户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 动态码
     * @return code_sys 动态码
     */
    public String getCodeSys() {
        return codeSys;
    }

    /**
     * 动态码
     * @param codeSys 动态码
     */
    public void setCodeSys(String codeSys) {
        this.codeSys = codeSys;
    }

    /**
     * 手机
     * @return iphone 手机
     */
    public String getIphone() {
        return iphone;
    }

    /**
     * 手机
     * @param iphone 手机
     */
    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    /**
     * 登录IP
     * @return login_IP 登录IP
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 登录IP
     * @param loginIp 登录IP
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 浏览器类型
     * @return brower_type 浏览器类型
     */
    public String getBrowerType() {
        return browerType;
    }

    /**
     * 浏览器类型
     * @param browerType 浏览器类型
     */
    public void setBrowerType(String browerType) {
        this.browerType = browerType;
    }

    /**
     * 修改内容
     * @return content_change 修改内容
     */
    public String getContentChange() {
        return contentChange;
    }

    /**
     * 修改内容
     * @param contentChange 修改内容
     */
    public void setContentChange(String contentChange) {
        this.contentChange = contentChange;
    }

    /**
     * 登录时间
     * @return login_time 登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 登录时间
     * @param loginTime 登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 系统角色
     * @return sys_role 系统角色
     */
    public String getSysRole() {
        return sysRole;
    }

    /**
     * 系统角色
     * @param sysRole 系统角色
     */
    public void setSysRole(String sysRole) {
        this.sysRole = sysRole;
    }

    /**
     * 0为全局，1为定制
     * @return permission 0为全局，1为定制
     */
    public Integer getPermission() {
        return permission;
    }

    /**
     * 0为全局，1为定制
     * @param permission 0为全局，1为定制
     */
    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    /**
     * 0为正常，1为禁用
     * @return status 0为正常，1为禁用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 0为正常，1为禁用
     * @param status 0为正常，1为禁用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 车队ID
     * @return team_id 车队ID
     */
    public Long getTeamId() {
        return teamId;
    }

    /**
     * 车队ID
     * @param teamId 车队ID
     */
    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    /**
     * 操作内容
     * @return contect 操作内容
     */
    public String getContect() {
        return contect;
    }

    /**
     * 操作内容
     * @param contect 操作内容
     */
    public void setContect(String contect) {
        this.contect = contect;
    }

    /**
     * 操作模块
     * @return module 操作模块
     */
    public String getModule() {
        return module;
    }

    /**
     * 操作模块
     * @param module 操作模块
     */
    public void setModule(String module) {
        this.module = module;
    }
}