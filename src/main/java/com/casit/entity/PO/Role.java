package com.casit.entity.PO;

import java.io.Serializable;

public class Role implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 19999L;

	/**
     * 
     * @mbg.generated * 表字段 : t_role.role_id
     */
    private Integer roleId;

    /**
     * 
     * @mbg.generated * 表字段 : t_role.role_name
     */
    private String roleName;

    /**
     * @mbg.generated * 获取  字段:t_role.role_id
     *
     * @return t_role.role_id, 
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @mbg.generated * 设置  字段:t_role.role_id
     *
     * @param roleId the value for t_role.role_id, 
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @mbg.generated * 获取  字段:t_role.role_name
     *
     * @return t_role.role_name, 
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @mbg.generated * 设置  字段:t_role.role_name
     *
     * @param roleName the value for t_role.role_name, 
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }
}