package com.casit.dao;

import com.casit.entity.PO.Role;

public interface RoleMapper {
    /**
     *  * @mbg.generated 根据主键删除数据库的记录,t_role
     *
     * @param roleId
     */
    int deleteByPrimaryKey(Integer roleId);

    /**
     *  * @mbg.generated 新写入数据库记录,t_role
     *
     * @param record
     */
    int insert(Role record);

    /**
     *  * @mbg.generated 动态字段,写入数据库记录,t_role
     *
     * @param record
     */
    int insertSelective(Role record);

    /**
     *  * @mbg.generated 根据指定主键获取一条数据库记录,t_role
     *
     * @param roleId
     */
    Role selectByPrimaryKey(Integer roleId);

    /**
     *  * @mbg.generated 动态字段,根据主键来更新符合条件的数据库记录,t_role
     *
     * @param record
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     *  * @mbg.generated 根据主键来更新符合条件的数据库记录,t_role
     *
     * @param record
     */
    int updateByPrimaryKey(Role record);
}