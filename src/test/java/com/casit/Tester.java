package com.casit;

public class Tester {

	public static void main(String[] args) {
		// getBoo()未执行
		if (getBoo2() && getBoo()) {
			System.out.println("ccc");
		}
		// getBoo2()未执行
		System.out.println("-------------------");

		if (getBoo() || getBoo2()) {
			System.out.println("xxxx");
		}
	}

	public static Boolean getBoo() {
		System.out.println("aaa");
		return true;
	}

	public static Boolean getBoo2() {
		System.out.println("bbb");
		return false;
	}
	
	public static class A {
		public static int i = 0;
	}

}
