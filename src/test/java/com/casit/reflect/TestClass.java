package com.casit.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestClass {
	
	private int i ;
	
	
	public TestClass() {
		super();
	}



	public TestClass(int i) {
		super();
		this.i = i;
	}



	public static  void main(String[] args) {
		
		System.out.println("-------Class.forName()--begin-------------");
		
		try {
			Class<?> clazz2 = Class.forName("com.casit.reflect.TestClass");
			for(Field field : clazz2.getDeclaredFields()) {
				System.out.println(field);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			TestClass testItem = (TestClass)Class.forName("com.casit.reflect.TestClass").newInstance();
			testItem.i = 99;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("-------Class.forName()--end-------------");
		
		System.out.println("-------XX.class--begin-------------");
		
		Class<TestClass> clazz = TestClass.class;
				
		System.out.println(clazz.getName());
		System.out.println(clazz.getSuperclass());
		
		System.out.println(clazz.getDeclaredAnnotations().length);
				
		System.out.println("----------------------");
		for(Field field : clazz.getDeclaredFields()) {
			System.out.println(field);
		}
		
		System.out.println("----------------------");
		for(Field field : clazz.getFields()) {
			System.out.println(field);
		}
		
		
		System.out.println("----------------------");
		for(Method method : clazz.getDeclaredMethods()) {
			System.out.println(method);
		}
		
		System.out.println("----------------------");
		for(Method method : clazz.getMethods()) {
			System.out.println(method);
		}
		
		System.out.println("----------------------");
		System.out.println(clazz.getConstructors().length);
		for(Constructor<?> constructor : clazz.getConstructors()) {
			System.out.println(constructor);
			System.out.println(constructor.getName());
		}
		
		System.out.println("----------------------");
		System.out.println(clazz.getClass());
		
		System.out.println("-------XX.class--end-------------");
		

		
	}

}
