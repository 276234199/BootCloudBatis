package com.casit.thread.MyBatchTaskFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.casit.tools.SleepTools;

/**
 * 类说明：模拟一个应用程序，提交工作和任务，并查询任务进度
 *
 */
public class TestFrame {

	private final static String JOB_NAME = "检测奇数偶数";
	private final static int JOB_LENGTH = 100;

	// 查询任务进度的线程
	private static class QueryResult implements Runnable {

		private PendingJobPool pool;

		public QueryResult(PendingJobPool pool) {
			super();
			this.pool = pool;
		}

		@Override
		public void run() {
			//标记已取出条数
			int count = 0;
			List<TaskResult<Boolean>> result = new ArrayList<>();
			while(count != JOB_LENGTH) {
				List<TaskResult<Boolean>> jobDetail = pool.getJobDetail(JOB_NAME);
				if (!jobDetail.isEmpty()) {
					int size = jobDetail.size();
					System.out.println("取出"+size+"个");
					count+=size;
					System.out.println(pool.getProcessDetail(JOB_NAME));
					result.addAll(jobDetail);
					SleepTools.ms(100);
				}
			}
			PendingJobPool.threadPool.shutdown();
			System.out.println("任务完成，结果为"+result);
		}

	}

	public static void main(String[] args) {
		TestTask myTask = new TestTask();
		PendingJobPool pool = PendingJobPool.getInstance();
		pool.registerJob(JOB_NAME, JOB_LENGTH, 1000 * 5, myTask);
		Random r = new Random();
		for (int i = 0; i < JOB_LENGTH; i++) {
			pool.putTask(JOB_NAME, r.nextInt(1000));
		}
		//负责查询进度
		Thread t = new Thread(new QueryResult(pool));
		t.start();
	}

}
