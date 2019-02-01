package com.casit;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
* 类说明:
* @author zhouhai
* @version 创建时间：2019年1月30日 下午4:03:57
*/
public class BloomFilterTest {

	private static int size = 1000000;

    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size);

    public static void main(String[] args) {
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }

        long startTime = System.nanoTime(); // 获取开始时间
        //判断这一百万个数中是否包含29999这个数
        if (bloomFilter.mightContain(29999)) {
            System.out.println("29999命中了");
        }
        if (bloomFilter.mightContain(29333999)) {
            System.out.println("29333999命中了");
        }else {
        	System.out.println("29333999没命中");
        }
        long endTime = System.nanoTime();   // 获取结束时间
        System.out.println("程序运行时间： " + (endTime - startTime) + "纳秒");
    }

}
