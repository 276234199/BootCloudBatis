package com.casit.netty;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
* 类说明:
* @author zhouhai
* @version 创建时间：2018年11月16日 下午2:47:02
*/
public class FirstNettyClient {
	
	private final int port;
	private final String host;
	
	public FirstNettyClient(int port, String host) {
		super();
		this.port = port;
		this.host = host;
	}
	

	public void start() throws InterruptedException {
		//线程组
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			//客户端启动类
			Bootstrap boot = new Bootstrap();
			
			boot.group(group)//配置线程组
			    .channel(NioSocketChannel.class)//配置nio通讯
			    .remoteAddress(new InetSocketAddress(host, port))//服务器地址
			    .handler(new FirstClientHandler());//处理器
			
			//连接到远程节点，阻塞等待直到连接完成
			ChannelFuture future = boot.connect().sync();
			//阻塞，直到channel关闭
			future.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully().sync();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		new FirstNettyClient(9999, "localhost").start();
	}
}
