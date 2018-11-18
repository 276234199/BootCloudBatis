package com.casit.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
* 类说明:
* @author zhouhai
* @version 创建时间：2018年11月16日 下午2:47:02
*/
public class FirstNettyServer {
	
	private final int port;
	
	public FirstNettyServer(int port ) {
		super();
		this.port = port;
	}
	

	public void start() throws InterruptedException {
		//线程组
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			//服务器启动类
			ServerBootstrap boot = new ServerBootstrap();
			
			//服务器处理器 多线程可以共享   @ChannelHandler.Sharable注解
			//如不共享 去掉注解 需在下面new出ch.pipeline().addLast(new FirstServerHandler()) 
			final FirstServerHandler handler =new FirstServerHandler();
			
			boot.group(bossGroup,workerGroup)//配置线程组
					.channel(NioServerSocketChannel.class)//配置nio通讯
					.localAddress(port)//服务器端口
					//接收到请求，启动一个SocketChannel通信
					//SocketChannel中有一个handler处理事件
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(handler); //处理器
						}
					});
			//绑定到服务器端口，阻塞等待直到连接完成
			ChannelFuture future = boot.bind().sync();
			//阻塞，直到channel关闭
			future.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully().sync();
			workerGroup.shutdownGracefully().sync();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		FirstNettyServer server = new FirstNettyServer(9999);
		System.out.println("准备启动服务器");
		server.start();
	}
}
