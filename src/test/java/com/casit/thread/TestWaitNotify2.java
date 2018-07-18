package com.casit.thread;

import java.util.concurrent.atomic.AtomicBoolean;

public class TestWaitNotify2 {

    // 在多线程间共享的对象上使用wait  
	//---原子类--
    private  AtomicBoolean shareObj = new AtomicBoolean(true);
    //使用非原子类，报错java.lang.IllegalMonitorStateException  进行了赋值操作
    //private  Boolean shareObj = new true;

    public static void main(String[] args) {
    	TestWaitNotify2 test = new TestWaitNotify2();
        ThreadWait threadWait1 = test.new ThreadWait("wait thread1");
        threadWait1.setPriority(2);
        ThreadWait threadWait2 = test.new ThreadWait("wait thread2");
        threadWait2.setPriority(3);
        ThreadWait threadWait3 = test.new ThreadWait("wait thread3");
        threadWait3.setPriority(4);

        ThreadNotify threadNotify = test.new ThreadNotify("notify thread");

        threadWait1.start();
        threadWait2.start();
        threadWait3.start();

        threadNotify.start();
        
        threadWait3.interrupt();
        
        System.out.println("ruaraurauruarura");
    }

    class ThreadWait extends Thread {

        public ThreadWait(String name){
            super(name);
        }

        public void run() {
            synchronized (shareObj) {
                while (shareObj.get() == true) {
                    System.out.println("线程"+ this.getName() + "开始等待");
                    long startTime = System.currentTimeMillis();
                    try {
                        shareObj.wait();
                        System.out.println("线程"+ this.getName() + "被notify");
                    } catch (InterruptedException e) {
                    	System.out.println("线程"+ this.getName() + ".wait() 被中断");
                    	break;
                    }
                    long endTime = System.currentTimeMillis();
                    System.out.println("线程" + this.getName() 
                            + "等待时间为：" + (endTime - startTime));
                }
            }
            System.out.println("线程" + getName() + "等待结束");
        }
    }

    class ThreadNotify extends Thread {

        public ThreadNotify(String name){
            super(name);
        }


        public void run() {
            try {
                // 给等待线程等待时间
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (shareObj) {
                System.out.println("线程" + this.getName() + "开始准备通知");
                
                //赋值操作 ，如果不使用原子类,在赋值语句的之前和之后，this.shareObj并不是同一个对象。
                //shareObj = false;
                //赋值操作  因此需要原子类 
                shareObj.set(false); 
                shareObj.notifyAll();
                System.out.println("线程" + this.getName() + "通知结束");
            }
            System.out.println("线程" + this.getName() + "运行结束");
        }
    }
}