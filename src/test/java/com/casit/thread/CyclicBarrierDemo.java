package com.casit.thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierDemo {
	
	
	public static Runnable run = new Runnable() {
		
		@Override
		public void run() {
			System.out.println("我亲自出马");
		}
	};
	

	public static CyclicBarrier barrier = new CyclicBarrier(5,run);
	
	public static CyclicBarrier barrier2 = new CyclicBarrier(5);
	
	public static void main(String args[]) throws Exception {
		for(int i = 0 ;i<5;i++) {
			Thread t = new Thread(new MyRun());
			t.start();
		}
	}
	
	
	
	public static class MyRun implements Runnable{

		@Override
		public void run() {
			
			Random r = new Random();
			
			if(r.nextBoolean()) {
				try {
					System.out.println(Thread.currentThread().getName()+" is sleeping");
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName()+"waiting other thread");
			
			try {
				barrier.await();
			System.out.println(Thread.currentThread().getName()+"waiting end finish");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
		
	}

}
