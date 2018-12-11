package com.casit.distributedLock2;
/**
* 类说明:
* @author zhouhai
* @version 创建时间：2018年12月11日 上午9:55:10
*/
public abstract class AbstractLock implements Lock{
	
    public void lock() {
        if (tryLock()) {
        	
        } else {
            // 等待
            waitLock();
            // 重新获取锁资源
            lock();
        }
	}
    
    public void unlock() {
        releaseLock();
	}
    
    // 获取锁资源
    public abstract boolean tryLock();

    // 等待
    public abstract void waitLock();

	
}
