package com.casit.proxy.dynamicProxy;

//原告类
public class Plaintiff implements ILawSuit {
	
	

	@Override
	public void submit(String proof) {
		System.out.println("老板欠薪跑路，证据如下："+proof);
	}

	@Override
	public void defend() {
		System.out.println("铁证如山，还钱");
	}

}
