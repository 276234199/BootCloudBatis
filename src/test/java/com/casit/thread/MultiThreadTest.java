package com.casit.thread;


public class MultiThreadTest {
	public static void main(String[] args) throws InterruptedException {

		RunnableTest runnable = new RunnableTest("ff");
		
		Thread ee = new Thread(runnable);
		Thread ff = new Thread(runnable);
		ee.start();
		ff.start();
		

		System.out.println(ee.hashCode());
		System.out.println(ff.hashCode());
		
	}
	
}
