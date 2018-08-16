package com.casit.thread.MyBatchTaskFrame;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 假设框架使用者不懂多线程，对外屏蔽多线程
 * 
 * 
 * 提交给框架执行的工作job，一个工作job是批量的Task
 *
 * @param <T> 任务类型
 * @param <R> 任务返回结果
 * 
 * 
 */

public class Job<T,R> {
	
	//标识
	private final String jobName;

	//task数量
	private final int taskLength;
	
	//过期时间 过期之后，直接从结果队列中清除
	private final long expireTime;
	
	//处理器
	private final ITaskProcessor<T, R> taskProcessor;
	
	//存放处理结果队列  并发安全的 双向阻塞链表
	private final LinkedBlockingDeque<TaskResult<R>> taskDetailDeque;

	//已处理任务个数
	private AtomicInteger processedCount;
	//成功任务个数
	private AtomicInteger successCount;

	public Job(String jobName, int taskLength, long expireTime, ITaskProcessor<T, R> taskProcessor) {
		super();
		this.jobName = jobName;
		this.taskLength = taskLength;
		this.expireTime = expireTime;
		this.taskProcessor = taskProcessor;
		this.taskDetailDeque = new LinkedBlockingDeque<TaskResult<R>>(taskLength);
		
		processedCount = new AtomicInteger(0);
		successCount = new AtomicInteger(0);
	}
	
	public ITaskProcessor<T, R> getTaskProcessor() {
		return taskProcessor;
	}

	//返回已处理数
	public int getProcessedCount() {
		return processedCount.get();
	}

	//返回成功数量
	public int getSuccessCount() {
		return successCount.get();
	}
	
	//返回失败数量
	public int getFailCount() {
		return processedCount.get() - successCount.get();
	}
	
	//进度信息
	public String getProcessDetail() {
		return "总共"+taskLength+"个,已处理"+processedCount.get()+"个,"+"成功"+successCount.get()+"个.";
	}

	//从taskDetailDeque处理结果队列中拿走结果
	public List<TaskResult<R>> getJobDetail(){
		List<TaskResult<R>> list = new LinkedList<>();
		TaskResult<R> taskRes;
		while((taskRes = taskDetailDeque.pollFirst()) != null) {
			list.add(taskRes);
		}
		return list;
	}
	
	//将处理记过加入结果队列taskDetailDeque  使用了线程安全的容器和类 所以没有加锁
	public void addTaskResult(TaskResult<R> result) {
		if (TaskResultType.Success.equals(result.getResultType())) {
			successCount.incrementAndGet();
		}
		processedCount.incrementAndGet();

		//放入队列taskDetailDeque尾部
		taskDetailDeque.addLast(result);
		
		//全部做完 将job放入过期队列
		if(processedCount.get() == taskLength) {
			CheckJobExpire.getInstance().putJob(jobName, expireTime);
		}
		
	}

}
