package com.casit.thread.DelayQueueTest;

/**
 * 限时订单
 */
public class LimitOrder {

	private String orderNo;
	private double orderMoney;
	//标记支付状态
	private int orderState;
	
	public static int UNPAYED = 0;
	public static int PAYED = 1;
	public static int TIMEOUT = 2;
	public static int CLOSED = 3;
	
	
	public LimitOrder() {
		super();
	}
	public LimitOrder(String orderNo, double orderMoney) {
		super();
		this.orderNo = orderNo;
		this.orderMoney = orderMoney;
		this.orderState = UNPAYED;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public double getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(double orderMoney) {
		this.orderMoney = orderMoney;
	}
	public int getOrderState() {
		return orderState;
	}
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
	
	
	
}
