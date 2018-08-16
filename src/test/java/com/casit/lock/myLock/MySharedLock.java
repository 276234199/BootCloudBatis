package com.casit.lock.myLock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
/**
 * 共享锁
 * @author zhouhai
 * 有点类似 Semaphore
 */
public class MySharedLock {

	private Sync sync;

	public MySharedLock(int count) {
		sync = new Sync(count);
	}

	private static final class Sync extends AbstractQueuedSynchronizer {

		public Sync(int count) {
			if (count < 0) {
				throw new IllegalArgumentException("count must be greater than 0");
			}
			setState(count);
		}

		@Override
		protected int tryAcquireShared(int reduceCount) {
			
			if(reduceCount<0) {
        		throw new Error("Maximum permit count exceeded");
        	}
			//多线程同时获取 ，需要CAS来获取锁 ,于是循环
			//返回值 <0直接进入等待队列，》=0 且cas成功 则获取锁成功
			for (;;) {
				int currentAvailable = getState();
				int newCount = currentAvailable - reduceCount;
				System.out.println("newCount:"+newCount);
				if (newCount < 0 || compareAndSetState(currentAvailable, newCount)) {
					return newCount;
				}
			}
		}

		@Override
		protected boolean tryReleaseShared(int addCount) {
			
			if(addCount<0) {
        		throw new Error("Maximum permit count exceeded");
        	}
			//多线程同时释放 所以需要CAS来释放锁,于是循环
			for (;;) {
                int current = getState();
                int newCount = current + addCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
		}

		@Override
		protected boolean isHeldExclusively() {
			int state = getState();
			if (state <= 0) {
				return true;
			}
			return false;
		}

		final ConditionObject newCondition() {
			return new ConditionObject();
		}

	}

	public void lock() {
		sync.acquireShared(1);
	}

	public void unlock() {
		sync.releaseShared(1);
	}

	public void lockInterruptibly() throws InterruptedException {
		sync.acquireSharedInterruptibly(1);
	}

	public boolean tryLock() {
		return sync.tryAcquireShared(1) >= 0;
	}

	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
	}

	public Condition newCondition() {
		return sync.newCondition();
	}

}
