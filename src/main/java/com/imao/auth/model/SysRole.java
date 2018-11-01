package com.imao.auth.model;

public class SysRole {
    /**
     * 
     */
    private Long roleId;

    /**
     * 
     */
    private String roleName;

    /**
     * 
     */
    private String roleCode;

    /**
     * 
     * @return role_id 
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 
     * @param roleId 
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 
     * @return role_name 
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 
     * @param roleName 
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 
     * @return role_code 
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 
     * @param roleCode 
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}