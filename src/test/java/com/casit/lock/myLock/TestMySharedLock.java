package com.casit.lock.myLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.casit.tools.SleepTools;


/**
 *测试自写的MySharedLock
 */
public class TestMySharedLock {
    public void test() {
        final MySharedLock lock = new MySharedLock(3);
        
        class Worker extends Thread {
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                    	SleepTools.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepTools.second(1);
                    } finally {
                        lock.unlock();
                    }
                    SleepTools.second(2);
                }
            }
        }
        // 启动10个子线程
        // 守护主线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
        // 主线程每隔1秒换行
        for (int i = 0; i < 10; i++) {
        	SleepTools.second(1);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TestMySharedLock testMySharedLock = new TestMySharedLock();
        testMySharedLock.test();
    }
}
