package com.casit.distributedLock2;

import java.util.concurrent.TimeUnit;

/**
* 类说明:
* @author zhouhai
* @version 创建时间：2018年12月11日 上午9:10:22
*/
public interface Lock {
	
	void lock();
	void unlock();
	
	boolean tryLock();
	
	boolean tryLock(long time,TimeUnit unit);
	
	void releaseLock();

}
