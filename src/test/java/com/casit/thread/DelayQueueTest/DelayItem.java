package com.casit.thread.DelayQueueTest;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 存放到队列的元素
 */
public class DelayItem<T> implements Delayed{
	
	//到期时间  MILLISECONDS
	private long endDate;
	
	private T item;
	
	/**
	 * @param durationMills 过期时长 单位 millsecond = 1/1000s
	 */
	public DelayItem(long durationMills , T item) {
        super();
        this.endDate = System.nanoTime()+TimeUnit.NANOSECONDS.convert(durationMills, TimeUnit.MILLISECONDS);
        this.item =  item;
    }

	public long getEndDate() {
		return endDate;
	}

	public T getItem() {
		return item;
	}

	@Override
	public int compareTo(Delayed o) {
		long d = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
		//d = 0 返回0，>0返回1 ,  <0返回-1
		return d==0 ? 0 : ( d>0 ? 1 : -1);
	}

	/**
	 * @param unit 返回剩余时间时间单位 
	 * @return 返回剩余时间
	 */
	@Override
	public long getDelay(TimeUnit unit) {
        return unit.convert(endDate-System.nanoTime(), TimeUnit.NANOSECONDS);
	}
	
}
