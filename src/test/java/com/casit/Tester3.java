package com.casit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Tester3 {

	public static void main(String[] args) {
		
		ExecutorService pool = Executors.newFixedThreadPool(5);
		System.out.println(pool.isTerminated());
		System.out.println(pool.isShutdown());
		pool.execute(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("done");
			}
		});
		System.out.println(pool.isTerminated());
		System.out.println(pool.isShutdown());

		Byte b = 1;
		byte b1 = 1;
		
		int i = 1;
		
		if(b==i) {
			System.out.println("wow");
		}
		if(b1==i) {
			System.out.println("wow!!!!");
		}

	}
	
}
