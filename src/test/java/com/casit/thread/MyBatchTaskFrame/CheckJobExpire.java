package com.casit.thread.MyBatchTaskFrame;

import java.util.concurrent.DelayQueue;


public class CheckJobExpire {

	//过期队列
	private static DelayQueue<DelayItem<String>> queue = new DelayQueue<>();

	//单例--begin---
	private CheckJobExpire(){}
	
	public static CheckJobExpire getInstance() {
		return CheckJobExpireHolder.expireCheck;	
	}
	
	private static class CheckJobExpireHolder{
		private static CheckJobExpire expireCheck = new CheckJobExpire();
	}
	//单例--end-----
	
	//把job放入过期队列
	public void putJob(String jobName,long expireTime) {
		DelayItem<String> item = new DelayItem<String>(expireTime, jobName);
		queue.offer(item);
		System.out.println("已经放入过期队列,过期时长："+expireTime);
	}

	
	
	//处理到期的任务
	private static class ExpiredJobDealer implements Runnable{

		public void run() {
			while(true) {
				try {
					//拿到过期job
					DelayItem<String> delayItem = queue.take();
					String jobName = delayItem.getItem();
					//从PendingJobPool中的jobmap移除掉过期的job，防止内存溢出
					PendingJobPool.getJobMap().remove(jobName);
					System.out.println("已清除过期任务："+jobName);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//守护线程 清理过期任务
	static {
		Thread t = new Thread(new ExpiredJobDealer());
		t.setDaemon(true);
		t.start();
		System.out.println("开启任务过期检查守护线程完毕...");
	}
	
	
}
