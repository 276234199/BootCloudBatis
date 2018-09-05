package com.casit.entity.PO;

import java.io.Serializable;

public class ShipAddress  implements Serializable{
	private static final long serialVersionUID = 199423499L;
    /**
     * 
     * @mbg.generated * 表字段 : t_shipaddress.address_id
     */
    private Integer addressId;

    /**
     * 
     * @mbg.generated * 表字段 : t_shipaddress.user_id
     */
    private User user;

    /**
     * 
     * @mbg.generated * 表字段 : t_shipaddress.address
     */
    private String address;

    /**
     * @mbg.generated * 获取  字段:t_shipaddress.address_id
     *
     * @return t_shipaddress.address_id, 
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * @mbg.generated * 设置  字段:t_shipaddress.address_id
     *
     * @param addressId the value for t_shipaddress.address_id, 
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * @mbg.generated * 获取  字段:t_shipaddress.user_id
     *
     * @return t_shipaddress.user_id, 
     */
    public User getUser() {
        return user;
    }

    /**
     * @mbg.generated * 设置  字段:t_shipaddress.user_id
     *
     * @param user the value for t_shipaddress.user_id, 
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @mbg.generated * 获取  字段:t_shipaddress.address
     *
     * @return t_shipaddress.address, 
     */
    public String getAddress() {
        return address;
    }

    /**
     * @mbg.generated * 设置  字段:t_shipaddress.address
     *
     * @param address the value for t_shipaddress.address, 
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

	@Override
	public String toString() {
		return "ShipAddress [addressId=" + addressId + ", user=" + user + ", address=" + address + "]";
	}
    
    
}