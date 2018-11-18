package com.casit;

/**
 * 懒汉单例
 * 线程安全 由虚拟机保证 
 * 推荐使用
 */
public class SingleInstance {
	
	public static SingleInstance getInstance() {
		return InstanceHolder.instance;
	}
	
	private static class InstanceHolder{
		private static final SingleInstance instance = new SingleInstance();
	}

}
