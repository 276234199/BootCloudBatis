package com.casit.testClassLoader;

public class TestClassLoader {
	
	//用户类由AppClassLoader加载
	//AppClassLoader的parent是ExtClassLoader
	//ExtClassLoader的parent是null
	
	public static  void main(String[] args) {
		
		
		ClassLoader cl = TestClassLoader.class.getClassLoader();
		
		System.out.println("ClassLoader is:"+cl.toString());
		System.out.println("ClassLoader\'s parent is:"+cl.getParent().toString());

		//实际由bootstrap classloader加载
		ClassLoader iL = int.class.getClassLoader();
		System.out.println("int ClassLoader is:"+iL);
		
		//实际由bootstrap classloader加载
		Object oL = Object.class.getClassLoader();
		System.out.println("object ClassLoader is:"+oL);
		
		
	}
	
	
}
