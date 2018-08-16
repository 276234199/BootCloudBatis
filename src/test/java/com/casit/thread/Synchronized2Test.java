package com.casit.thread;

public class Synchronized2Test {
	public static void main(String[] args) {
		Runtest runtest = new Runtest();
		for (int i = 0; i < 10; i++) {
			Thread thread = new MyThread(runtest, i);// 1同一个RunTest1对象但每次有个新的线程
			thread.start();
			
//			 Runtest rruntest = new Runtest(); //2 循环每次都声明一个新的对象   对象锁失效
//			 Thread thread = new MyThread(rruntest, i);
//			 thread.start();
		}
	}
}
 
class MyThread extends Thread {
	Runtest r;
	int i = 0;
 
	public MyThread(Runtest r, int i) {
		this.r = r;
		this.i = i;
	}
 
	public void run() {
		r.noSynMethod(this.getId());
       //用以测试同一个对象在不同线程中访问不同方法
       if(i % 2 == 0){
			r.objSynoMethod2();//对象锁方法2
		}else{
			r.objSynoMethod();//对象锁方法1
		}
       
       Runtest.classSynoMethodPlus(); //类锁方法
	}
}
 
class Runtest {
	static int i = 0;
	
	//无锁方法 不受任何影响
	public void noSynMethod(long threadId) {
		System.out.println("nosyn: class obj->" + this + ", threadId->" + threadId);
	}
 
	//对象锁
	//等价于synchronized (this)
	synchronized public void objSynoMethod() {
		System.out.println("in outMethod begin");
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
		}
	}
 
	//对象锁
	//等价于synchronized (this)
	synchronized public void objSynoMethod2() {
		System.out.println("in outMethod2 begin");
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
		}
	}
 
	//类锁  
	//synchronized + static
	//也可以写成synchronized public static void classSynoMethodPlus() 
	public static void classSynoMethodPlus() {
		synchronized (Runtest.class) {
			System.out.println("start: " + i);
			try {
				Thread.sleep(3000L);
			} catch (InterruptedException e) {
			}
			i++;
			System.out.println("i is " + i);
		}
	}
}
