package com.casit.netty;



import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
* 类说明:客户端处理器
* @author zhouhai
* @version 创建时间：2018年11月16日 下午3:22:17
*/
public class FirstClientHandler extends SimpleChannelInboundHandler<ByteBuf>{

	/**
	 * 客户端收到数据后
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
		System.out.println("client accept : "+msg.toString(CharsetUtil.UTF_8));
	}
	/**
	 * 客户端被通知channel激活
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		ctx.writeAndFlush(Unpooled.copiedBuffer("Hello netty",CharsetUtil.UTF_8));
	}
	/**
	 * 异常处理
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
	
	

}
