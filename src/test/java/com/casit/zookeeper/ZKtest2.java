package com.casit.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZKtest2 {

	public static void main(String as[]) throws IOException, KeeperException, InterruptedException {

		ZookeeperConnectionUtil zkutil = new ZookeeperConnectionUtil("localhost:2181", 2000);
		System.out.println("testPath data :"+new String(zkutil.getData("/testPath"))); 
		

		ZooKeeper zk = zkutil.connect("localhost:2181", 2000);
		Stat stat = zk.exists("/testPath22", zkutil);
		
		if(stat==null) {
			System.out.println("not exist");
		}else {
			System.out.println("exist");
		}
		
		Thread.sleep(6000000);
		zkutil.close();
	
	}

}
