package com.casit.distributedLock2;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.casit.tools.SleepTools;

/**
* 类说明:
* @author zhouhai
* @version 创建时间：2018年12月11日 上午10:32:32
*/
public class TestLock {
	
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(50);
		for(int i = 0 ; i <50 ;i++) {
			pool.execute(new Runnable() {
				@Override
				public void run() {
					ZkDistributeLock myLock = new ZkDistributeLock();
					try {
						myLock.lock();
						System.out.println(Thread.currentThread()+"获得锁");
						SleepTools.second(1);
					}finally {
						System.out.println(Thread.currentThread()+"释放锁");
						myLock.unlock();
					}
				}
			});
		}
	}

}
