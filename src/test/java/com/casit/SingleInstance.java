package com.casit;

/**
 * 懒汉单例
 *
 */
public class SingleInstance {
	
	public static SingleInstance getInstance() {
		return InstanceHolder.instance;
	}
	
	private static class InstanceHolder{
		private static final SingleInstance instance = new SingleInstance();
	}

}
