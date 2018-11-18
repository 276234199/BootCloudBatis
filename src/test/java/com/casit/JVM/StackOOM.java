package com.casit.JVM;
/**
* 类说明: 栈溢出 无限递归调用 栈溢出  设置 -Xss1024k  -Xss256k 栈大小  
* java.lang.StackOverflowError
* @author zhouhai
* @version 创建时间：2018年9月21日 上午10:41:45
*/
public class StackOOM {
	
	static int i = 0;
	
	private static void doit() {
		i++;
		if(i%1000==0) {
			System.out.println(i);
		}
		doit() ;
	}

	public static void main(String[] args) {
		doit();
	}
}
