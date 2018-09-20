package com.casit.entity.PO;

import java.util.List;

/**
* 类说明:公司
* @author zhouhai
* @version 创建时间：2018年9月4日 下午4:31:32
*/


public class Corp  extends BaeEntity{
	
	private static final long serialVersionUID = -588783201047048125L;
	private Integer corpId;
	private String corpName;
	
	private List<User> users;
	
	

	@Override
	public String toString() {
		return "Corp [corpId=" + corpId + ", corpName=" + corpName + ", users=" + users + "]";
	}
	public Integer getCorpId() {
		return corpId;
	}
	public void setCorpId(Integer corpId) {
		this.corpId = corpId;
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

	
	
	
	
	

}
