package com.casit.thread;

public  class StopThreadByInterrupt {

	static	Thread t = new Thread(new Runnable() {
		@Override
		public void run() {
			while(!Thread.currentThread().isInterrupted()) {
				try {
					System.out.println(Thread.currentThread().getName());
					Thread.sleep(111);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					System.out.println("thread Interrupted");
					e.printStackTrace();
				}
			}
			System.out.println("thread end");
		}
	});
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println(Thread.currentThread().getName());
		t.start();
		Thread.sleep(500);
		t.interrupt();
	}

}
