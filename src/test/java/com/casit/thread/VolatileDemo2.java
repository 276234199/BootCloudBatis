package com.casit.thread;

//volatile关键字具有可见性 有序性 但是不具有原子性
public class VolatileDemo2 {
	private static volatile Integer counter = 0;
	

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
//                	synchronized(this) {
                		 for (int i = 0; i < 10; i++) {
                      		counter++; //不是原子操作
                		 }
//                		 this.notify();
//                	}
                }
            });
            thread.start();
            
//            synchronized(thread) {
//            	try {
//					thread.wait();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//            }
        }

        //如果具有原子性，结果应该是100，要得到100需要加锁
        try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(counter);
    }

}
