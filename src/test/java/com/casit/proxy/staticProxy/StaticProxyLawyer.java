package com.casit.proxy.staticProxy;

//代理律师
public class StaticProxyLawyer implements ILawSuit {
	
	private ILawSuit plaintiff;//持有要代理的那个对象对象
	
	public StaticProxyLawyer(ILawSuit plaintiff) {
		super();
		this.plaintiff = plaintiff;
	}

	@Override
	public void submit(String proof) {
		System.out.println("before static proxy doing something");
		plaintiff.submit(proof);
		System.out.println("after static proxy doing something");
	}

	@Override
	public void defend() {

		System.out.println("before static proxy doing something");
		plaintiff.defend();
		System.out.println("after static proxy doing something");
		
	}

}
