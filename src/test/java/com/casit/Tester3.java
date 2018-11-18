package com.casit;


public class Tester3 {

	public static void main(String[] args) {
		Object o1 = new  Object();
		Object o2 = new  Object();
		System.out.println(o1==(o1=o2));
		System.out.println(o1);
		System.out.println(o2);
		System.out.println(o1=o2);
		System.out.println(o1);
		System.out.println(o2);
		System.out.println(o1==(o1=o2));
		
		StringBuffer sb = new StringBuffer();
		sb.append("qq").append("dd").append("ff");
		System.out.println(sb.toString());
		

	}
	
}
