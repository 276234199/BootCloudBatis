package com.casit.thread.MyBatchTaskFrame;

/**
 * 
 * 工作处理器任务接口
 * 
 * 传入传出都使用了泛型
 * 
 */
public interface ITaskProcessor<T,R> {
	
	/**
	 * 
	 * @param task 业务方法的
	 * @return 任务执行结果
	 */
	TaskResult<R> taskExecute(T task);

}
