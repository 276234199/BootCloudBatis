package com.casit.zookeeper.zkCli;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.junit.Test;

import java.util.List;

public class TestZkClientWatcher {


    /** zookeeper地址 */
    static final String CONNECT_ADDR = "localhost:2181";
    /** session超时时间 */
    static final int SESSION_OUTTIME = 10000;//ms


    @Test
    /**
     * subscribeChildChanges方法 订阅子节点变化
     */
    public  void testZkClientWatcher1() throws Exception {
        ZkClient zkc = new ZkClient(new ZkConnection(CONNECT_ADDR), SESSION_OUTTIME);

        //对父节点添加监听子节点变化。
        zkc.subscribeChildChanges("/super", new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println("parentPath: " + parentPath);
                System.out.println("currentChilds: " + currentChilds);
                System.out.println("---------------------------");
            }
        });

        Thread.sleep(3000);

        zkc.createPersistent("/super");
        Thread.sleep(1000);
        
        zkc.createPersistent("/super" + "/" + "c1", "c1内容");
        Thread.sleep(1000);

        zkc.createPersistent("/super" + "/" + "c2", "c2内容");
        Thread.sleep(1000);

        zkc.delete("/super/c2");
        Thread.sleep(1000);

        zkc.deleteRecursive("/super");
        Thread.sleep(3300);
    }

    @Test
    /**
     * subscribeDataChanges 订阅内容变化
     */
    public void testZkClientWatcher2() throws Exception {
        ZkClient zkc = new ZkClient(new ZkConnection(CONNECT_ADDR), SESSION_OUTTIME);
        
        zkc.createPersistent("/super", "1234");

        //对父节点添加监听子节点变化。
        zkc.subscribeDataChanges("/super", new IZkDataListener() {
            @Override
            public void handleDataDeleted(String path) throws Exception {
                System.out.println("删除的节点为:" + path);
            }

            @Override
            public void handleDataChange(String path, Object data) throws Exception {
                System.out.println("变更的节点为:" + path + ", 变更内容为:" + data);
            }
        });

        Thread.sleep(3000);
        zkc.writeData("/super", "456", -1);
        Thread.sleep(1000);

        zkc.delete("/super");
        Thread.sleep(3333);

    }

}
