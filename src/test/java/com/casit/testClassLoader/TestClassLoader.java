package com.casit.testClassLoader;

public class TestClassLoader {
	
	//用户类由AppClassLoader加载
	//AppClassLoader的parent是ExtClassLoader
	//ExtClassLoader的parent是null
	
	public static  void main(String[] args) {
		
		
		ClassLoader cl = TestClassLoader.class.getClassLoader();
		
		System.out.println("ClassLoader is:"+cl.toString());
		System.out.println("ClassLoader\'s parent is:"+cl.getParent().toString());
		

		
	}
	
	
}
