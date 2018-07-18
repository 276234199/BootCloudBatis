package com.casit;

public class test {
	
	public static void main(String[] args) throws Exception{
		while(true) {
			try {
				throw new Exception();
			}catch (Exception e) {
				e.printStackTrace();
				break;
			}finally {
				System.out.println("qqqq");
			}
		}
		System.out.println("qqqq222");
	}
	
	

}
