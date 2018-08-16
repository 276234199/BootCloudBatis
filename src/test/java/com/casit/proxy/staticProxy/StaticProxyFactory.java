package com.casit.proxy.staticProxy;

public class StaticProxyFactory {

	public static ILawSuit getProxyInstance() {
		return new StaticProxyLawyer(new Plaintiff());
	}
	
	public static void main(String[] args) {
        StaticProxyFactory.getProxyInstance().submit("工资流水在此");
        StaticProxyFactory.getProxyInstance().defend();
    }
}
