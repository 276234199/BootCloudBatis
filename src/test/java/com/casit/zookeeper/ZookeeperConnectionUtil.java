package com.casit.zookeeper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZookeeperConnectionUtil implements Watcher {

	// zk对象
	private ZooKeeper zk = null;

	// 同步计数器
	private CountDownLatch countDownLatch = new CountDownLatch(1);
	
	

	public ZookeeperConnectionUtil(String hosts, int session_timeout) throws IOException, InterruptedException {
		this.connect(hosts, session_timeout);
	}

	@Override
	public void process(WatchedEvent event) {
		System.out.println("已经触发了" + event.getType() + "事件！");
		if (event.getState() == KeeperState.SyncConnected) {
			countDownLatch.countDown();// 计数器减一
			System.out.println("已经连接上zk，起飞");
		}

		EventType eventType = event.getType();
		KeeperState state = event.getState();
		String watchPath = event.getPath();
		switch (eventType) {
		case NodeCreated:
			break;
		case NodeDataChanged:
			try {
				handleDateChangeMessage(watchPath);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (KeeperException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		case NodeChildrenChanged:
			try {
				handleChilderenChangeMessage(watchPath);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (KeeperException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
	
	public void handleDateChangeMessage(String watchPath) throws KeeperException,InterruptedException, UnsupportedEncodingException {
	    System.out.println(watchPath+"dataChange: "+new String(this.getData(watchPath)));
	    
	}
	
	public void handleChilderenChangeMessage(String watchPath) throws KeeperException,InterruptedException, UnsupportedEncodingException {
	    System.out.println(watchPath+"ChilderenChange");
	    //再监听该子节点
	    List<String> Children = this.getChildren(watchPath);
	    for (String a : Children) {
	        String childrenpath = watchPath + "/" + a;
	        byte[] recivedata = this.getData(childrenpath);
	        String recString = new String(recivedata, "UTF-8");
	        System.out.println("receive the path:" + childrenpath + ":data:"+ recString);
	        //做完了之后，删除该节点
	        this.deletNode(childrenpath);
	    }
	}
	
	public byte[] getData(String path) throws KeeperException,InterruptedException {
	     //监听该节点的变化情况
	     return this.zk.getData(path, this,null);
	}
	
	public Stat setData(String path, byte[] data, int version)throws KeeperException, InterruptedException {
	     return this.zk.setData(path, data, version);
	}
	
	public List<String> getChildren(String path) throws KeeperException,InterruptedException {
	     //监听该节点子节点的变化情况
	     return this.zk.getChildren(path, this);
	}
	
	public void deletNode(String path) throws InterruptedException, KeeperException {
		//删除节点
		this.zk.delete(path, -1);
	}
	

	public ZooKeeper connect(String hosts, int session_timeout) throws IOException, InterruptedException {
		zk = new ZooKeeper(hosts, session_timeout, this);
		countDownLatch.await();// 阻塞程序继续执行
		return zk;
	}

	// 关闭zk
	public void close() throws InterruptedException {
		if (zk != null) {
			try {
				zk.close();
			} catch (InterruptedException e) {
				throw e;
			} finally {
				zk = null;
				System.gc();
			}
		}
	}

}
