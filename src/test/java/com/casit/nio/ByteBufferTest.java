package com.casit.nio;

import java.io.FileNotFoundException;
import java.nio.ByteBuffer;

/**
 * 类说明:
 * 
 * @author zhouhai
 * @version 创建时间：2018年11月7日 上午9:59:55
 */
public class ByteBufferTest {
	public static void main(String args[]) throws FileNotFoundException {

		System.out.println("----------Test allocate--------");
		System.out.println("before alocate:" + Runtime.getRuntime().freeMemory());

		// 如果分配的内存过小，调用Runtime.getRuntime().freeMemory()大小不会变化？
		// 要超过多少内存大小JVM才能感觉到？
		ByteBuffer buffer = ByteBuffer.allocate(1024000);
		System.out.println("buffer = " + buffer);

		System.out.println("after alocate:" + Runtime.getRuntime().freeMemory());

		// 这部分直接用的系统内存，所以对JVM的内存没有影响
		ByteBuffer directBuffer = ByteBuffer.allocateDirect(1024000);
		System.out.println("directBuffer = " + directBuffer);
		System.out.println("after direct alocate:" + Runtime.getRuntime().freeMemory());

		System.out.println("----------Test wrap--------");
		byte[] bytes = new byte[32];
		buffer = ByteBuffer.wrap(bytes);
		System.out.println(buffer);

		buffer = ByteBuffer.wrap(bytes, 10, 10);
		System.out.println(buffer);
	}
}
