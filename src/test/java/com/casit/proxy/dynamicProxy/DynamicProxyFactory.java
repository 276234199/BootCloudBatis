package com.casit.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;


public class DynamicProxyFactory {
	
	//此方式，只能代理实现了接口的类，如本例的ILawSuit
	//无接口可以使用CGLIB

	public static Object getProxyInstance(Object target) {

		// 我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
		InvocationHandler handler = new DynamicProxy(target);
		/*
         * 通过Proxy的newProxyInstance方法来创建我们的代理对象
         * 第一个参数 handler.getClass().getClassLoader() ，这里使用handler这个类的ClassLoader对象来加载我们的代理对象
         * 第二个参数 target.getClass().getInterfaces()，这里为代理对象提供的接口是真实对象所实现的接口，表示要代理的是该真实对象，这样就能调用这组接口中的方法了
         * 第三个参数handler， 我们这里将这个代理对象关联到了上方的 InvocationHandler 这个对象上
         */
		Object proxy =  Proxy.newProxyInstance(handler.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);

		//返回值proxy类型是com.sun.proxy.$Proxy0，
		//Proxy.newProxyInstance方法的第二个参数，我们给这个代理对象提供了一组什么接口，那么我这个代理对象返回值proxy就会实现了这组接口
		
		//Proxy.newProxyInstance 创建的代理对象是在jvm运行时动态生成的一个对象，它并不是我们的InvocationHandler类型，
		//也不是我们定义的那组接口的类型，而是在运行是动态生成的一个对象，并且命名方式都是这样的形式，
		//以$开头，proxy为中，最后一个数字表示对象的标号。
		System.out.println("proxy: "+proxy.getClass().getName());
		
//		System.out.println("ClassLoader is:"+handler.getClass().getClassLoader().toString());
//		System.out.println("ClassLoader is:"+target.getClass().getClassLoader().toString());
		
		return proxy;
	}
	
	
	public static void main(String[] args) {
		
		//com.sun.proxy.$Proxy0，构建该对象时， Proxy.newProxyInstance方法的第二参数的时候实现了ILawSuit
		ILawSuit proxy = (ILawSuit)DynamicProxyFactory.getProxyInstance(new Plaintiff());
				
		proxy.submit("银行流水单");
		proxy.defend();
    }

}
