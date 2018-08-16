package com.casit.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {

	// 被代理的对象
	private Object target;

	public DynamicProxy(Object obj) {
		this.target = obj;
	}

	@Override
	// 调用被代理的target对象的method方法，args是参数
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		// ---AOP---的实现 事前事后 分别做事
		// 在代理真实对象前我们可以添加一些自己的操作
		System.out.println("before Dynamic proxy doing something");
		
		System.out.println("Method:" + method);

		//代理调用method
		Object result = method.invoke(target, args);

		// ---AOP---的实现 事前事后 分别做事
		// 在代理真实对象后我们可以添加一些自己的操作
		System.out.println("after Dynamic proxy doing something");

		// ---AOP---的实现 事前事后 分别做事

		return result;
	}

}
