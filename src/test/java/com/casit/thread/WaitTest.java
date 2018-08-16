package com.casit.thread;

public class WaitTest {

	public static void main(String[] args) throws InterruptedException {
		ThreadB b = new ThreadB();
		ThreadB b2 = new ThreadB();
		
		// 启动计算线程
		b.start();
		
		
		// 线程main拥有b对象上的锁。线程为了调用wait()或notify()方法，该线程必须是那个对象锁的拥有者
		synchronized (b) {
			// 线程main等待
			b.wait();
			System.out.println("b线程计算的总和是：" + b.total);
		}
		


//		b2.start();
//		
//		synchronized (b2) {
//			// 线程main等待
//			b2.wait();
//			System.out.println("b2线程计算的总和是：" + b2.total);
//		}
		
		

		System.out.println("再继续做其他事情");


	}
}

/**
 * 计算1+2+3 ... +100的和
 * 
 */
class ThreadB extends Thread {
	int total;

	public void run() {
		synchronized (this) {
			System.out.println("开始计算。。。");
			for (int i = 0; i < 101; i++) {
				total += i;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// （完成计算了）唤醒在此对象监视器上等待的单个线程，在本例中线程main被唤醒
			this.notify();
			System.out.println("计算完成");
		}
	}

}
