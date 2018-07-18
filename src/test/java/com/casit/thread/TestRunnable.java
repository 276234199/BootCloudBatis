package com.casit.thread;

public class TestRunnable implements Runnable {

	private String flag;

	private int tickets = 20;

	private byte[] lock = new byte[0];

	public TestRunnable(String flag) {
		super();
		this.flag = flag;

	}

	@Override
	public void run() {

		synchronized (this) {

		}
//锁住 线程安全
		synchronized (lock) {
			while (tickets > 0) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println(flag + "---thread----" + tickets);
				tickets--;
			}
		}

	}
}
