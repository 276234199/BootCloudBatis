package com.casit.thread;
/**
* 类说明:测试AtomicInteger的ABA问题
* @author zhouhai
* @version 创建时间：2018年8月7日 下午7:08:25
*/

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import com.casit.tools.SleepTools;

public class AtomicIntegerTest {

	private static AtomicInteger count = new AtomicInteger(100);
	
	private static CountDownLatch cdl = new CountDownLatch(50);

	public static void main(String[] args) {
		
		ArrayList<AtomicInteger> list =new ArrayList<>();
		AtomicInteger count22 = new AtomicInteger(33);
		list.add(count22); 
		count22.decrementAndGet();
		System.out.println(list.get(0));
		System.out.println(count22);
		count22 = new AtomicInteger(666);
		System.out.println(list.get(0)); 
		System.out.println(count22);
		
		for(int i = 0 ;i<50;i++) {
			
			if(i%2 == 1) {
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						cdl.countDown();
						try {
							cdl.await();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("increaseTo"+count.incrementAndGet());
					}
				});
				
				t.start();
			}else {
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						cdl.countDown();
						try {
							cdl.await();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("decrementTo"+count.decrementAndGet());
					}
				});
				
				t.start();
			}
			
			
		}
		
		SleepTools.ms(100);
		System.out.println("finally:"+count);
	}

}
