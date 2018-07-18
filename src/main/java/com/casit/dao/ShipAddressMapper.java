package com.casit.dao;

import com.casit.entity.PO.ShipAddress;

public interface ShipAddressMapper {
    /**
     *  * @mbg.generated 根据主键删除数据库的记录,t_shipaddress
     *
     * @param addressId
     */
    int deleteByPrimaryKey(Integer addressId);

    /**
     *  * @mbg.generated 新写入数据库记录,t_shipaddress
     *
     * @param record
     */
    int insert(ShipAddress record);

    /**
     *  * @mbg.generated 动态字段,写入数据库记录,t_shipaddress
     *
     * @param record
     */
    int insertSelective(ShipAddress record);

    /**
     *  * @mbg.generated 根据指定主键获取一条数据库记录,t_shipaddress
     *
     * @param addressId
     */
    ShipAddress selectByPrimaryKey(Integer addressId);

    /**
     *  * @mbg.generated 动态字段,根据主键来更新符合条件的数据库记录,t_shipaddress
     *
     * @param record
     */
    int updateByPrimaryKeySelective(ShipAddress record);

    /**
     *  * @mbg.generated 根据主键来更新符合条件的数据库记录,t_shipaddress
     *
     * @param record
     */
    int updateByPrimaryKey(ShipAddress record);
}