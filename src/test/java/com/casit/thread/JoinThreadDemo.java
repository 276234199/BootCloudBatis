package com.casit.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class JoinThreadDemo {
	public static void main(String[] args) throws InterruptedException {
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 0;
				while(i<10) {
					System.out.println("t2 running");
					i++;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				
				while(true) {
					System.out.println("t1 running");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
//		TimeUnit.SECONDS.sleep(1);
		
		t2.start();
		t2.join(6000);

		t1.start();
    }


    

}
