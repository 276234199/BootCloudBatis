package com.casit.JVM;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

/**
 * 类说明:设置堆5m
 * java.lang.OutOfMemoryError
 * -Xmx5m -Xms5m
 * @author zhouhai
 * @version 创建时间：2018年9月21日 上午10:50:01
 */
public class OOM {
	public static void main(String[] args) {

		
		//java.lang.OutOfMemoryError: Java heap space
		//堆溢出
		String[] strings = new String[100000000];
		
		//直接内存溢出 多见于nio
		//java.lang.OutOfMemoryError: Direct buffer memory
		ByteBuffer bb =  ByteBuffer.allocateDirect(1024*1024*6);
		
		//gc不动了
		//java.lang.OutOfMemoryError: GC overhead limit exceeded
		List<Object> list = new LinkedList<>();
		int i = 0;
		while (true) {
			i++;
			if (i % 10000 == 0)
				System.out.println("i=" + i);
			list.add(new Object());
		}

		 
	}
}
