package com.casit.lock;

import com.casit.tools.SleepTools;

/**
 * 类说明:
 * 先jps查询pid
 * 借助 jstack -l pid 寻找死锁
 * @author zhouhai
 * @version 创建时间：2018年10月8日 下午9:59:36
 */
public class TestDeadLock {

	public static byte[] l1 = new byte[1];
	public static byte[] l2 = new byte[1];

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (l1) {
					SleepTools.ms(50);
					synchronized (l2) {

					}
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (l2) {
					SleepTools.ms(50);
					synchronized (l1) {

					}
				}
			}
		});
		t1.start();
		t2.start();
	}

}
