package com.casit.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTaskTest {
	
	static{
		System.out.println("static");
	}
	
	
	public static class Caculator implements Callable<Integer>{
		@Override
		public Integer call() throws Exception {
			Integer count = 0;
			for(int i = 0 ;i<100; i++) {
				Thread.sleep(100);
				count = count+i;
			}
			return count;
		}
	}
	
	public static void main (String args[]) throws InterruptedException, ExecutionException, TimeoutException {
		FutureTask<Integer> task = new FutureTask<Integer>(new Caculator());
		Thread t = new Thread(task);
		t.start();
		System.out.println(task.isCancelled());
//		task.cancel(true);
		System.out.println(task.get(20,TimeUnit.SECONDS));
		System.out.println(task.get());
		System.out.println(task.isDone());
	}

}
