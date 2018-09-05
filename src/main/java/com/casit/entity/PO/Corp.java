package com.casit.entity.PO;

import java.util.List;

/**
* 类说明:公司
* @author zhouhai
* @version 创建时间：2018年9月4日 下午4:31:32
*/


public class Corp  extends BaeEntity{
	
	private static final long serialVersionUID = -588783201047048125L;
	private Integer corpID;
	private String corpName;
	
	private List<User> users;
	
	public Integer getCorpID() {
		return corpID;
	}
	public void setCorpID(Integer corpID) {
		this.corpID = corpID;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	@Override
	public String toString() {
		return "Corp [corpID=" + corpID + ", corpName=" + corpName + ", users=" + users + "]";
	}
	
	
	
	
	

}
