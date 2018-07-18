package com.casit.lock;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class TestReentrantReadWriteLock {
	
	public static void main(String args[]) {
		 Dbdata dabdata = new Dbdata();
		 for(int i = 0;i<1;i++){
			 new Thread() {
				 public void run(){
					 while(true)
						 dabdata.getData();
				 }
			 }.start();
			 new Thread() {
				 public void run(){
					 while(true)
						 dabdata.setData(new Random().nextInt(10000));
				 }
			 }.start();
		 }
	}

	static class Dbdata {
		
		private Object data = null;
		
		private ReadWriteLock rwl = new ReentrantReadWriteLock(true);
		
		public void getData() {
			if(rwl.readLock().tryLock()) {
				try {
					System.out.println(Thread.currentThread().getName() + " be ready to read data!");
					Thread.sleep((long)(Math.random()*1000));
					System.out.println(Thread.currentThread().getName() + "have read data :" + data); 
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					rwl.readLock().unlock();
				}
			}else {
				System.out.println(Thread.currentThread().getName() + " read data fail");
			}
			
		}
		
		public void setData(Object data) {
			rwl.writeLock().lock();
			try {
				System.out.println(Thread.currentThread().getName() + " be ready to write data!");
				Thread.sleep((long)(Math.random()*1000));
				this.data = data;
				System.out.println(Thread.currentThread().getName() + "have written data :" + data); 
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				rwl.writeLock().unlock();
			}
		}
	}
	
}
