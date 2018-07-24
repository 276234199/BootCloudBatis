package com.casit.thread;

//volatile关键字具有可见性 有序性 但是不具有原子性
public class VolatileDemo2 {
	private static  volatile Integer counter = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                	synchronized(this) {
                		 for (int i = 0; i < 1000; i++)
//                         	synchronized (counter){
                         		counter++;
//                         	}
                		 this.notify();
                	}
                }
            });
            thread.start();
            
            synchronized(thread) {
            	try {
					thread.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        }

        //如果具有原子性，结果应该是10000，要得到10000需要加锁
        System.out.println(counter);
    }

}
