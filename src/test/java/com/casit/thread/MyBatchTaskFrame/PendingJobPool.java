package com.casit.thread.MyBatchTaskFrame;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 框架主体 调用者使用
 * 
 * 
 * @author ASUS
 *
 */
public class PendingJobPool {

	// 等同于CPU数
	private static int THREAD_COUNT = Runtime.getRuntime().availableProcessors();
	// 限制在5K
	private static BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(5000);
	// 定义线程池
	public static ExecutorService threadPool = new ThreadPoolExecutor(THREAD_COUNT, THREAD_COUNT * 2, 60,
			TimeUnit.SECONDS, taskQueue);

	// 用jobName作为key 保存job的容器
	@SuppressWarnings("rawtypes")
	private static ConcurrentHashMap<String, Job> jobMap = new ConcurrentHashMap<>();

	// 单例begin=============
	// 构造私有化，提供单例模式，限制线程数
	private PendingJobPool() {
	};

	// 通过静态内部类获取懒汉单例
	public static PendingJobPool getInstance() {
		return PendingJobPoolHolder.pendingJobPool;
	}

	// 静态内部类
	private static class PendingJobPoolHolder {
		private static PendingJobPool pendingJobPool = new PendingJobPool();
	}
	// 单例end===============

	/**
	 * 注册job
	 * 
	 * @param jobName
	 *            必须唯一
	 * @param taskLength
	 *            任务长度
	 * @param expireTime
	 *            过期时间
	 * @param taskProcessor
	 *            任务处理器
	 * @throws RuntimeException
	 */
	public <T, R> void registerJob(String jobName, int taskLength, long expireTime, ITaskProcessor<T, R> taskProcessor)
			throws RuntimeException {
		if (jobName == null || jobName.equals("")) {
			throw new RuntimeException("Illegal jobName");
		}
		Job<T, R> job = new Job<>(jobName, taskLength, expireTime, taskProcessor);
		// 使用putIfAbsent避免反复提交
		if (jobMap.putIfAbsent(jobName, job) != null) {
			throw new RuntimeException("任务已经注册");
		}
	}

	@SuppressWarnings("unchecked")
	public <T, R> Job<T, R> getJob(String jobName) {
		Job<T, R> job = jobMap.get(jobName);
		if (job == null) {
			throw new RuntimeException("job not exist or expired");
		}
		return job;
	}

	public <T, R> void putTask(String jobName, T task) {
		if (jobName == null || jobName.equals("")) {
			throw new RuntimeException("Illegal jobName");
		}
		Job<T, R> job = getJob(jobName);
		PendingTask<T, R> pendingTask = new PendingTask<T, R>(job, task);
		threadPool.execute(pendingTask);
	}

	// 将task打包成runnable，供线程池使用。把任务处理的结果写入job的结果缓存，供用户查询
	private static class PendingTask<T, R> implements Runnable {

		private Job<T, R> job;
		private T task;

		public PendingTask(Job<T, R> job, T task) {
			super();
			this.job = job;
			this.task = task;
		}

		@Override
		public void run() {

			ITaskProcessor<T, R> taskProcessor = job.getTaskProcessor();
			TaskResult<R> taskResult = null;
			try {
				// 调用业务人员实现的方法
				taskResult = taskProcessor.taskExecute(task);
				// 检查 预防开发人员处置不当乱填
				if (taskResult == null) {
					taskResult = new TaskResult<R>(TaskResultType.Exception, "Result is null");
				} else if (taskResult.getResultType() == null) {
					taskResult = new TaskResult<R>(TaskResultType.Exception, "ResultType is null");
				}

			} catch (Exception e) {
				e.printStackTrace();
				taskResult = new TaskResult<R>(TaskResultType.Exception, e.getMessage());
			} finally {
				job.addTaskResult(taskResult);
			}

		}

	}

	//从taskDetailDeque处理结果队列中拿走结果
	public <T, R> List<TaskResult<R>> getJobDetail(String jobName) {
		Job<T, R> job = getJob(jobName);
		return job.getJobDetail();
	}

	// 进度信息
	public <T, R> String getProcessDetail(String jobName) {
		Job<T, R> job = getJob(jobName);
		return job.getProcessDetail();
	}

	@SuppressWarnings("rawtypes")
	public static Map<String, Job> getJobMap() {
		return jobMap;
	}
	
	

}
