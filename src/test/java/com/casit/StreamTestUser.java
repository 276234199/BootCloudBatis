package com.casit;
/**
* 类说明:
* @author zhouhai
* @version 创建时间：2019年2月27日 上午10:35:27
*/
public class StreamTestUser {
	private String name;
	public StreamTestUser(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "StreamTestUser [name=" + name + "]";
	}
}
