package com.casit.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo {

	private static ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<>();

	private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		ExecutorService executorService2 = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 100; i++) {
			executorService.submit(new DateUtil("2019-11-25 09:00:" + i % 60));
//			executorService2.submit(new DateUtil2("2019-11-25 09:00:" + i % 60));
		}
		executorService.shutdown();
		executorService2.shutdown();
	}

	static class DateUtil implements Runnable {
		private String date;

		public DateUtil(String date) {
			this.date = date;
		}

		@Override
		public void run() {
			if (sdf.get() == null) {
				sdf.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			} else {
				try {
					Date date = sdf.get().parse(this.date);
					System.out.println("DateUtil res:"+date);
				} catch (ParseException e) {
					e.printStackTrace();
				}finally {
					sdf.remove();
				}
			}
		}
	}

	static class DateUtil2 implements Runnable {
		private String date;

		public DateUtil2(String date) {
			this.date = date;
		}

		@Override
		public void run() {
			try {
				Date date = sdf2.parse(this.date);
				System.out.println("DateUtil2 res:"+date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

}
