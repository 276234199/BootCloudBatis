package com.casit.thread;


public class TestMultiThread {
	public static void main(String[] args) throws InterruptedException {

		TestRunnable runnable = new TestRunnable("ff");
		
		Thread ee = new Thread(runnable);
		Thread ff = new Thread(runnable);
		ee.start();
		ff.start();
		

		System.out.println(ee.hashCode());
		System.out.println(ff.hashCode());
		
	}
	
}
