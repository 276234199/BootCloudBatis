package com.casit.initialClazz;
/**
* 类说明:
* @author zhouhai
* @version 创建时间：2018年9月26日 下午4:17:05
*/
public class RunMain {
	
	public static void main(String[] args) {
		//调用父类static字段，初始化父类
		System.out.println(SubClazz.i);
		//由于j有final修饰 划入常量池（JDK1.8:MetaSpace） 父类子类都不初始化
		System.out.println(SubClazz.j);
		//调用new 都初始化
		SubClazz sub = new SubClazz();
		

		
	}
	


}
