package com.casit.thread.DelayQueueTest;

import java.util.concurrent.DelayQueue;


public class RunMain {

	private static DelayQueue<DelayItem<LimitOrder>> queue =  new DelayQueue<DelayItem<LimitOrder>>();
	
	public static void main(String[] args) {

		Thread consumer = new Thread(new Consumer(queue));
		consumer.start();
		
		for(int i = 0 ; i< 3 ; i++ ) {
			Thread producer = new Thread(new Producer(queue));
			producer.start();
		}

	}
	
}
