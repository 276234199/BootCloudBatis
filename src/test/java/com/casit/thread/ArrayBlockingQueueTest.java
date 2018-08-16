package com.casit.thread;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueTest {

	public static void main(String[] args) {
		System.out.println("----------------------");

		ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<String>(1);
		abq.add("11");
		try {
			abq.add("12"); // 反向操作对应remove
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("offer isSuccess : " + abq.offer("12"));// 反向操作对应poll

		System.out.println("begin put ,blocked ");
		try {
			abq.put("12");// 反向操作对应take
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
