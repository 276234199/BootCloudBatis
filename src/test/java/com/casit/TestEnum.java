package com.casit;
/**
* 类说明:
* @author zhouhai
* @version 创建时间：2019年1月24日 下午4:54:02
*/
public enum TestEnum {
	
	AAA("qqq"),BBB("avc");
	
	private String dahubi;
	
	TestEnum(String aaa){
		this.dahubi =aaa;
	}

	public String getDahubi() {
		return dahubi;
	}

	public void setDahubi(String dahubi) {
		this.dahubi = dahubi;
	}
	
	public static void main(String[] args) {
		System.out.println(Enum.valueOf(TestEnum.class, "AAA").getDahubi());
		System.out.println(Enum.valueOf(TestEnum.class, "CCC").getDahubi());
	}

}
