package com.casit.distributedLock;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

public class TestDisLock {
	
	public static void main(String[] args) throws Exception {
		
		ZkClient zkclient = new ZkClient("localhost:2181",2000);
		String basePath = "/testLock";
		if(!zkclient.exists(basePath)) {
			zkclient.create(basePath, null, CreateMode.PERSISTENT);
		}
		
		SimpleDistributedLockMutex lock = new SimpleDistributedLockMutex(zkclient, basePath);
		
		System.out.println("waitLock!!!!!!!!!");
		lock.acquire();
		
		System.out.println("getLock!!!!!!!!!");
		
		Thread.sleep(60000);
		
		System.out.println("releaseLock!!!!!!!!!");
		lock.release();
		
	}

}
