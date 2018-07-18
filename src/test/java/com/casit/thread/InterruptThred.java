package com.casit.thread;

public class InterruptThred extends Thread {

	//修改了stop植 原子stop 使用原子volatile
	volatile boolean stop = false;
	
	public static void main(String args[]) throws Exception {
		InterruptThred thread = new InterruptThred();
		System.out.println("Starting thread...");
		thread.start();
		Thread.sleep(3000);
		System.out.println("Asking thread to stop...");

		// 如果线程阻塞sleep、join，不调用interrupt将不会检查此变量
		thread.stop = true;
		//中断sleep、join
		thread.interrupt();
		
		Thread.sleep(3000);
		System.out.println("Stopping application...");
		
	}

	public void run() {
		
		while (!stop) {
			System.out.println("Thread running...");
			try {
				
				//打断sleep
//				Thread.sleep(100000);
				
				//打断join状态 不在等待t执行完毕 继续执行后面的  但是t会继续执行 不会影响t
				t.start();
				t.join();
//被interrupt后不会执行，不被interrupt则会执行				
				System.out.println("gggggggggggggggggggggggggggggggggggggg");
				
			} catch (InterruptedException e) {
				System.out.println("Thread interrupted...");
			}
		}
		System.out.println("Thread exiting under request...");
		
	}
	
	//打断join thread不在等待 ,但是t不会受影响，会继续运行
	public  Thread t = new Thread(new Runnable() {
		public void run() {
			int i = 0;
			System.out.println(i);
			while(i<10) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				i++;
				System.out.println(i);
			}
		}
	});
}
