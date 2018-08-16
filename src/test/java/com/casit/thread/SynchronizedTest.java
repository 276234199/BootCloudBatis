package com.casit.thread;

public class SynchronizedTest {

	public static void main(String args[]) {
        MyObject object = new MyObject();
		MyObject object2 = new MyObject();
		
//对象锁object---------------------------------------------------------------------
		// 线程A与线程B 持有的是同一个对象:object --对象锁---锁住object
		ThreadA a = new ThreadA();
		a.object = object;

		//被a阻塞  
		ThreadB b = new ThreadB();
		b.object = object;
		
//对象锁object.lockC---------------------------------------------------------------------		
		ThreadC c = new ThreadC();
		c.object = object;
		
		//被c阻塞
		ThreadC c2 = new ThreadC();
		c2.object = object;
		
//对象锁object.lockD---------------------------------------------------------------------		
		//只有它调用了lockD 故而无其他锁阻塞
		ThreadD d = new ThreadD();
		d.object = object;
		
//类锁MyObject.class---------------------------------------------------------------------	
		// 线程e与线程e2 持有的是不同对象----类锁
		// 但是methodC锁住的是MyObject.class
		ThreadE e = new ThreadE();
		e.object = object;

		//被e阻塞
		ThreadE e2 = new ThreadE();
		e2.object = object2;

		a.start();
		b.start();
		
		c.start();
		c2.start();
		d.start();
		
		e.start();
		e2.start();
	}

	static class ThreadA extends Thread {
		public MyObject object;

		@Override
		public void run() {
			super.run();
			try {
				object.methodA();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	static class ThreadB extends Thread {
		public MyObject object;

		@Override
		public void run() {
			super.run();
			try {
				object.methodB();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	static class ThreadC extends Thread {
		public MyObject object;

		@Override
		public void run() {
			super.run();
			try {
				object.methodC();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	static class ThreadD extends Thread {
		public MyObject object;

		@Override
		public void run() {
			super.run();
			try {
				object.methodD();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	static class ThreadE extends Thread {
		public MyObject object;

		@Override
		public void run() {
			super.run();
			try {
				object.methodE();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	static class MyObject {

		//对象锁-C
		public String lockC = "lockC";
		//对象锁-D
		public String lockD = "lockD";

		// 对象锁 锁住this
		// 等价于synchronized public void methodA()
		public void methodA() throws InterruptedException {
			synchronized (this) {
				System.out.println("doing A");
				Thread.sleep(2000);
			}

		}

		// 对象锁 锁住this
		// 等价于synchronized public void methodB()
		public void methodB() throws InterruptedException {
			synchronized (this) {
				System.out.println("doing B");
				Thread.sleep(2000);
			}
		}

		// 对象锁 锁住lockC
		public void methodC() throws InterruptedException {
			synchronized (lockC) {
				System.out.println("doing C");
				Thread.sleep(2000);
			}

		}

		// 对象锁 锁住lockD
		public void methodD() throws InterruptedException {
			synchronized (lockD) {
				System.out.println("doing D");
				Thread.sleep(2000);
			}

		}

		// 类锁
		// 等价于synchronized public static void methodC()
		public void methodE() throws InterruptedException {
			synchronized (MyObject.class) {
				System.out.println("doing E");
				Thread.sleep(2000);
			}
		}

	}

}
