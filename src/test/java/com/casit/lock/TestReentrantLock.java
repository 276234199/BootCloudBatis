package com.casit.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


//由于在前面讲到如果采用Lock，必须主动去释放锁，并且在发生异常时，不会自动释放锁.
//因此一般来说，使用Lock必须在try{}catch{}块中进行，并且将释放锁的操作放在finally块中进行，以保证锁一定被被释放，防止死锁的发生。

public class TestReentrantLock {

	private List<Integer> arrayList = new ArrayList<Integer>();
	private Lock lock = new ReentrantLock(); // 注意这个地方

	public static void main(String[] args) {
		final TestReentrantLock test = new TestReentrantLock();

		new Thread() {
			public void run() {
				test.insert(Thread.currentThread());
			};
		}.start();

		new Thread() {
			public void run() {
				test.insert(Thread.currentThread());
			};
		}.start();
	}

	public void insert(Thread thread) {
		lock.lock();
		try {
			System.out.println(thread.getName() + "得到了锁");
			for (int i = 0; i < 5; i++) {
				arrayList.add(i);
				System.out.println(thread.getName());
			}
			printArr();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(thread.getName() + "释放了锁");
			lock.unlock();
		}
	}
	
	public void printArr() {
		lock.lock();
		try {
			for(int i :arrayList){
				System.out.println(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

}
