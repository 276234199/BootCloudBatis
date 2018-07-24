package com.casit.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {
	private static CountDownLatch sCountDownLatch = null;
	private static final int THREAD_NUMBER = 3;
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		sCountDownLatch = new CountDownLatch(THREAD_NUMBER);
		//线程池
		ExecutorService fixedThreadPool = Executors
				.newFixedThreadPool(THREAD_NUMBER);
		//执行线程
		fixedThreadPool.execute(new ConsumeRunnable("one"));
		fixedThreadPool.execute(new ConsumeRunnable("two"));
		fixedThreadPool.execute(new ConsumeRunnable("three"));
		System.out.println("等待3个子线程执行完毕...");
		try {
			sCountDownLatch.await();
			System.out.println("3个子线程已经执行完毕");
			System.out.println("继续执行主线程");
			fixedThreadPool.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
 
	private static class ConsumeRunnable implements Runnable {
		private String mName;
 
		public ConsumeRunnable(String name) {
			this.mName = name;
		}
		public void run() {
			System.out.println("子线程" + mName + "正在执行");
			try {
				Thread.sleep(3000);// 模拟耗时操作
				System.out.println("子线程" + mName + "执行完毕");
				sCountDownLatch.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
