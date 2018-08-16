package com.casit.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
	
	public static void main(String args[]) {
		
		CallableItem callableItem = new CallableItem();
		FutureTask<Integer> fk = new FutureTask<>(callableItem);
		new Thread(fk).start();
		
		try {
			// 调用get()阻塞主线程，反之，线程不会阻塞
			Integer result = fk.get();
			System.out.println(result);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static class CallableItem implements Callable<Integer>{

		@Override
		public Integer call() throws Exception {
			return 99999;
		}
		
	}
}
