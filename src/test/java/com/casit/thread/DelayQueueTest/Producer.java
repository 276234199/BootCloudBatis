package com.casit.thread.DelayQueueTest;

import java.util.UUID;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;
/**
 * 
 * @author ASUS
 *推送订单
 */
public class Producer implements Runnable{
	
	private DelayQueue<DelayItem<LimitOrder>> queue;

	public Producer(DelayQueue<DelayItem<LimitOrder>> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		
		LimitOrder order = new LimitOrder(UUID.randomUUID().toString().substring(0,7), 222d);
		saveOrderToDb(order);
		//5秒到期order
		DelayItem<LimitOrder> delayOrder = new DelayItem<LimitOrder>(5000,order); 
		queue.offer(delayOrder);
		
		System.out.println(order.getOrderNo() +"   "+delayOrder.getDelay(TimeUnit.SECONDS)+ "秒后到期");
		
		
		LimitOrder order2 = new LimitOrder(UUID.randomUUID().toString().substring(0,7), 333d);
		saveOrderToDb(order2);
		//5秒到期order
		DelayItem<LimitOrder> delayOrder2 = new DelayItem<LimitOrder>(8000,order2); 
		queue.offer(delayOrder2);
		System.out.println(order2.getOrderNo() +"   "+delayOrder2.getDelay(TimeUnit.SECONDS)+ "秒后到期");
		
	}
	/**
	 * 入库
	 * @param order
	 */
	public void saveOrderToDb(LimitOrder order) {
		System.out.println(order.getOrderNo() +"已入库");
	}
	


	
}
