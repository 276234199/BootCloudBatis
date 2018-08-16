package com.casit.lock.myLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



/**
 * @author zhouhai
 * state = 1,表示锁已被线程获取
 * state = 0,表示锁未被任何线程获取
 */
public class MyLock implements Lock {
	
	private class Sync extends AbstractQueuedSynchronizer{

		private static final long serialVersionUID = 1123456782514L;

		@Override
		protected boolean tryAcquire(int arg) {
			//Reentrantlock 重入时，先判定Thread.currentThread()==getExclusiveOwnerThread() ，true然后每次state+1，
			//Reentrantlock在 release时 也需要多次release，每次state-1
			
			
			//不可重入
			if(getState() == 1) {
				return false;
			}
			///////////////////////////////////////
			if(compareAndSetState(0, 1)){
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}else {
				return false;
			}
		}

		@Override
		protected boolean tryRelease(int arg) {
			if (Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new IllegalMonitorStateException();
			}
			if(getState() == 0 ) {
				return false;
			}
			//独占锁  只有一个线程来set释放锁   所以不需要cas
			setState(0);
			setExclusiveOwnerThread(null);
			return true;
		}

		@Override
		protected boolean isHeldExclusively() {
			return getState() == 1;
		}
		
		Condition newCondition() {
			return new ConditionObject();
		}
		
	}

	private final Sync sync = new Sync();
	
	@Override
	public void lock() {
		sync.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		return sync.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireNanos(1, unit.toNanos(time));
	}

	@Override
	public void unlock() {
		sync.release(1);
	}

	@Override
	public Condition newCondition() {
		return sync.newCondition();
	}

}
