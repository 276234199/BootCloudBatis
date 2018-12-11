package com.casit.distributedLock2;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * 类说明:基于zk的临时顺序节点分布式锁
 * 
 * @author zhouhai
 * @version 创建时间：2018年12月11日 上午9:12:51
 */
public class ZkDistributeLock extends AbstractLock {

	/** zookeeper地址 */
	static final String CONNECT_ADDR = "localhost:2181";
	/** session超时时间 */
	static final int SESSION_OUTTIME = 10000;// ms
	static final String basePath0 = "/lock";
	/** zookeeper锁节点 */
	static final String basePath = "/lock/test";

	ZkClient zkc;

	String currentPath;
	String beforePath;

	public ZkDistributeLock() {
		zkc = new ZkClient(CONNECT_ADDR, SESSION_OUTTIME);
		if (!zkc.exists(basePath0)) {
			zkc.createPersistent(basePath0);
		}
		if (!zkc.exists(basePath)) {
			zkc.createPersistent(basePath, false);
		}
	}

	@Override
	public boolean tryLock() {
		//如果currentPath为空则为第一次尝试加锁，第一次加锁赋值currentPath
        if(currentPath == null || currentPath.length()<= 0){
        	currentPath = zkc.createEphemeralSequential(basePath + "/tmp", "");
        }

		List<String> children = zkc.getChildren(basePath);

		Collections.sort(children);

		if (currentPath.equals(basePath + "/" + children.get(0))) {
			return true;
		} else {
			int index = Collections.binarySearch(children, currentPath.replace(basePath + "/", ""));
			beforePath = basePath + "/" + children.get(index - 1);
			return false;
		}
	}

	@Override
	public void waitLock() {
		CountDownLatch cdl =  new CountDownLatch(1);
		IZkDataListener listener = new IZkDataListener() {
			
			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				cdl.countDown();
			}
			@Override
			public void handleDataChange(String dataPath, Object data) throws Exception {}
		};
		zkc.subscribeDataChanges(beforePath, listener);
		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		zkc.unsubscribeDataChanges(beforePath, listener);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) {
		return false;
	}

	@Override
	public void releaseLock() {
		boolean success = zkc.delete(currentPath);
		zkc.close();
	}

}
