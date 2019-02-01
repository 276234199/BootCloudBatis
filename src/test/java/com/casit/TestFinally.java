package com.casit;

/**
 * 类说明:
 * 
 * @author zhouhai
 * @version 创建时间：2019年1月24日 下午7:19:08
 */
public class TestFinally {

	public static void main(String[] args) {
		ddd();
		System.out.println("done");
	}

	public static String ddd() {
		try {
			return "Aaaa";
		} finally {
			System.out.println("qqqqqqqqqqqqqqqqq");
		}
	}

}
