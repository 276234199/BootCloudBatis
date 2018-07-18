package com.casit.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZKtest {

	public static void main(String as[]) throws IOException, KeeperException, InterruptedException {

		ZookeeperConnectionUtil zkutil = new ZookeeperConnectionUtil("localhost:2181", 2000);
		ZooKeeper zk = zkutil.connect("localhost:2181", 2000);
		
		
//		zk.create("/testPath", "testPath".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//		zk.create("/testPath/testChildPathOne", "testChildDataOne".getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL); 
//		System.out.println("testPath data :"+new String(zk.getData("/testPath",true,null))); 
//		System.out.println("children:"+zk.getChildren("/testPath",true)); 
		zk.setData("/testPath", "testPath饭wwwwwwwwwww".getBytes(),-1);
		zk.create("/testPath/testChildPathttttttttttt", "testChildPathttttttttttt".getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
//		System.out.println("new testPath data :"+new String(zk.getData("/testPath",true,null)));
//		Thread.sleep(6000);
//		zk.delete("/testPath/testChildPathOne",-1);
//		zk.delete("/testPath", -1);
		zk.close();
	
	}

}
