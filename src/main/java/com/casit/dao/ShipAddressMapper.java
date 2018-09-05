package com.casit.dao;

import java.util.List;

import com.casit.entity.PO.ShipAddress;

public interface ShipAddressMapper {
//////////////////////////////////////////////自定义、、、、、、、、、、、、、、、、、、、、、、、
    /**
     * 根据用户id查询 address
     * @param 用户id
     */
    List<ShipAddress> selectByUserID(Integer userid);
    
}