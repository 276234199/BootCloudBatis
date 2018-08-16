package com.casit.thread.DelayQueueTest;

import java.util.concurrent.DelayQueue;
/**
 * 
 * @author ASUS
 * 消费过期订单
 */
public class Consumer implements Runnable{

	private DelayQueue<DelayItem<LimitOrder>> queue;

	public Consumer(DelayQueue<DelayItem<LimitOrder>> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		
		while(true) {
			try {
				DelayItem<LimitOrder> orderItem = queue.take();
				LimitOrder order =  orderItem.getItem();
				
				LimitOrder dbOrder = getOrderFromDbByOrderNo(order);
				
				if(dbOrder.getOrderState() == LimitOrder.PAYED) {
					System.out.println("订单 "+ order.getOrderNo()+" 已经支付");
				}
				
				if(dbOrder.getOrderState() == LimitOrder.UNPAYED) {
					System.out.println("订单 "+ order.getOrderNo()+" 过期");
					expireOrderFromDbByOrderNo(dbOrder);
				}
				
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 
	 * @param order
	 * @return 模拟从数据库中取出order
	 */
	public LimitOrder getOrderFromDbByOrderNo(LimitOrder order) {
		//模拟从数据库中取出order
		return order;
	}
	
	/**
	 * 
	 * @param order
	 * @return 模拟从数据库中修改order状态
	 */
	public void expireOrderFromDbByOrderNo(LimitOrder order) {
		
	}

}
