package com.casit.lock;

import java.util.concurrent.locks.LockSupport;

public class TestLockSupport {
	 public static void main(String[] args) {
	        Thread thread = new Thread(() -> {
	        	System.out.println(Thread.currentThread().getName() + "被wait");
	            LockSupport.park();
	            System.out.println(Thread.currentThread().getName() + "被唤醒");
	        });
	        thread.start();
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        LockSupport.unpark(thread);
	    }
}
