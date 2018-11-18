package com.casit;
/**
* 类说明:再熟悉一波数组声明和初始化
* @author zhouhai
* @version 创建时间：2018年9月27日 上午10:53:46
*/
public class ArrayTest {
	
	public static void main(String[] args) {
		
		String[] s1 = new String[2];
		s1[0]="s1--0";
		String s2[] = new String[2];
		s2[0]= "s2--0";
		
		String[] s3 = {"q","vv"};
		String s4[] = new String[] {"aaaa","bbbb"};

		print("aa","bb");
		
		print(s1);
		print(s2);
		print(s3);
		print(s4);
	}

	private static void print(String...strings) {
		for(String s:strings) {
			System.out.println(s+"----");
		}
	}
}
