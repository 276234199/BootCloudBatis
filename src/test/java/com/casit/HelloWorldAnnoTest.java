package com.casit;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@HelloWorldAnno
public class HelloWorldAnnoTest {

	@HelloWorldAnno(name = "周杰伦")
	private String star;

	@HelloWorldAnno(name = "王宝强", age = 33)
	public static void say() {
	}

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, NoSuchMethodException {

		// 获取类的注解
		boolean classHasAnnotation = HelloWorldAnnoTest.class.isAnnotationPresent(HelloWorldAnno.class);
		if (classHasAnnotation) {
			HelloWorldAnno helloWorldAnno = HelloWorldAnnoTest.class.getAnnotation(HelloWorldAnno.class);
			System.out.println("name:" + helloWorldAnno.name());
			System.out.println("age:" + helloWorldAnno.age());
		}

		// 获取field属性上的注解
		Field starField = HelloWorldAnnoTest.class.getDeclaredField("star");
		starField.setAccessible(true);// aField.getAnnotations();
		HelloWorldAnno helloWorldAnno2 = starField.getAnnotation(HelloWorldAnno.class);
		System.out.println("name:" + helloWorldAnno2.name());
		System.out.println("age:" + helloWorldAnno2.age());

		// 获取方法上的注解
		Method sayMethod = HelloWorldAnnoTest.class.getDeclaredMethod("say");
		sayMethod.setAccessible(true);
		HelloWorldAnno helloWorldAnno3 = sayMethod.getAnnotation(HelloWorldAnno.class);
		System.out.println("name:" + helloWorldAnno3.name());
		System.out.println("age:" + helloWorldAnno3.age());
	}

}
