package com.casit.netty;


import java.util.concurrent.atomic.AtomicInteger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
* 类说明:服务器处理器 共享 shareable .
* 用成无成员变量（无状态）或者使用线程安全的成员变量 达到线程安全 
* @ChannelHandler.Sharable  标记是共享的 handler 可以共用 要考虑线程安全
* @author zhouhai
* @version 创建时间：2018年11月16日 下午3:57:17
*/
@ChannelHandler.Sharable
public class FirstServerHandler extends ChannelInboundHandlerAdapter{
	
	public AtomicInteger count;

	@Override
	/**
	 * 服务器读到数据后
	 */
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		//netty缓冲
		ByteBuf buf = (ByteBuf)msg;
		System.out.println("server accept : "+buf.toString(CharsetUtil.UTF_8));
		//接收到什么 就返回什么 也可以改成其他的
		ctx.write(buf);
		
//      池化的bytebuf
//		ctx.alloc().buffer(initialCapacity)
	}

	@Override
	/**
	 * 服务器读完数据后
	 */
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		//把缓冲里的所有数据flush出去                         
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
	    	.addListener(ChannelFutureListener.CLOSE);//flush完后 关闭连接
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
	
	
}
