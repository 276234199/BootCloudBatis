package com.casit.dao;

import com.casit.entity.PO.User;
import com.casit.entity.PO.UserExample;
import java.util.List;

public interface UserMapper {
    /**
     *  * @mbg.generated 根据主键删除数据库的记录,t_user
     *
     * @param id
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *  * @mbg.generated 新写入数据库记录,t_user
     *
     * @param record
     */
    int insert(User record);

    /**
     *  * @mbg.generated 动态字段,写入数据库记录,t_user
     *
     * @param record
     */
    int insertSelective(User record);

    /**
     *  * @mbg.generated 根据指定的条件查询符合条件的数据库记录,t_user
     *
     * @param example
     */
    List<User> selectByExample(UserExample example);

    /**
     *  * @mbg.generated 根据指定主键获取一条数据库记录,t_user
     *
     * @param id
     */
    User selectByPrimaryKey(Integer id);

    /**
     *  * @mbg.generated 动态字段,根据主键来更新符合条件的数据库记录,t_user
     *
     * @param record
     */
    int updateByPrimaryKeySelective(User record);

    /**
     *  * @mbg.generated 根据主键来更新符合条件的数据库记录,t_user
     *
     * @param record
     */
    int updateByPrimaryKey(User record);
}