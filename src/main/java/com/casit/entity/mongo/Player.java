package com.casit.entity.mongo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
* 类说明:
* @author zhouhai
* @version 创建时间：2019年2月14日 上午10:14:12
*/
@Document(collection = "cool")
public class Player implements Serializable{
	

	private static final long serialVersionUID = -4865817758205629600L;
	@Id
    private String  playerId;
	private String name;
	private Integer count;
	
	
	public String getPlayerId() {
		return playerId;
	}
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	

}
