package com.casit.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.casit.tools.SleepTools;

/**
 * condition维护了一个单向链表，有顺序性，
 * 排它锁如ReentrantLock 一个lock下多个condition 也只能一次一个线程拿到所
 * 唤醒顺序并不确定
 */
public class TestCondition3 {
	
	private static Lock lock = new ReentrantLock();
	private static Condition condition1 = lock.newCondition();
	private static Condition condition2 = lock.newCondition();
	
	public static void main(String[] args) {
		
		
		//t0-t4
		for(int i = 0 ; i<5;i++) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					lock.lock();
					try {
						System.out.println(Thread.currentThread().getName()+" wating condition1");
						condition1.await();
//						SleepTools.second(1);
						System.out.println(Thread.currentThread().getName()+" wating condition1 over");
					}
					catch (InterruptedException e) {
						
					}
					finally {
						lock.unlock();
					}
				}
			});
			t.setName("t"+i);
			t.start();
		}
		//t5-t9
		for(int i = 5 ; i<10;i++) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					lock.lock();
					try {
						System.out.println(Thread.currentThread().getName()+" wating condition2");
						condition2.await();
//						SleepTools.second(1);
						System.out.println(Thread.currentThread().getName()+" wating condition2 over");
					}
					catch (InterruptedException e) {
						
					}
					finally {
						lock.unlock();
					}
				}
			});
			t.setName("t"+i);
			t.start();
		}

		
		SleepTools.second(1);
		lock.lock();
		try {
			for(int i = 0 ;i<5;i++)
			{
				condition1.signal();
				condition2.signal();
			}
			
		}finally {
			lock.unlock();
		}
		
	}

}
