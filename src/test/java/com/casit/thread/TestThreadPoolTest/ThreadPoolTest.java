package com.casit.thread.TestThreadPoolTest;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


import com.casit.tools.SleepTools;

import io.netty.util.concurrent.Future;

public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {   
    	
    	System.out.println("CPU核心数："+Runtime.getRuntime().availableProcessors());
    	
    	//Executors工厂生成定义好的线程池
    	ExecutorService pool2 = Executors.newCachedThreadPool();
    	
    	//定期执行周期任务
    	ScheduledThreadPoolExecutor pool3 = new ScheduledThreadPoolExecutor(5);
    	
    	//自定义线程池   使用最多
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 1000, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));
        
        ConcurrentHashMap<Integer, FutureTask<Integer>> map = new ConcurrentHashMap<>();
        
        //核心池  5个
        //线程最大值  10个
        //等待阻塞队列可以放5个
        //因此这个线程池最多可以放 max10+阻塞队列5 = 15个线程
        //多一个就会抛出RejectedExecutionException
        for(int i=0;i<15;i++){
//            MyTask myTask = new MyTask(i);
//            executor.execute(myTask);
            FutureTask<Integer> fk = new FutureTask<>(new CallableThing(i));
            map.putIfAbsent(i, fk);
            executor.execute(fk);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
            executor.getQueue().size());
        }
        for(Map.Entry<Integer, FutureTask<Integer>> entry : map.entrySet()) {
        	System.out.println(entry.getKey()+"---"+entry.getValue().get());
        }
        executor.shutdown();
    }
}

class CallableThing implements Callable<Integer>{
	
	private int taskNum;

	public CallableThing(int taskNum) {
		super();
		this.taskNum = taskNum;
	}



	@Override
	public Integer call() throws Exception {
		System.out.println("正在执行task "+taskNum);
		SleepTools.second(2);
	    System.out.println("task "+taskNum+"执行完毕");
		return new Random().nextInt(9999);
	}
	
}


class MyTask implements Runnable {
   private int taskNum;
    
   public MyTask(int num) {
       this.taskNum = num;
   }
    
   @Override
   public void run() {
       System.out.println("正在执行task "+taskNum);
       SleepTools.second(2);
       System.out.println("task "+taskNum+"执行完毕");
   }
}