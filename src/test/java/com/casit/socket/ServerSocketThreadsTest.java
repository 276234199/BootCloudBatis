package com.casit.socket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

import com.casit.tools.SleepTools;

/**
 * 类说明: 多线程server
 * 
 * @author zhouhai
 * @version 创建时间：2018年11月6日 下午2:55:25
 */
public class ServerSocketThreadsTest {

	public static void main(String[] args) throws IOException {
		try (ServerSocket serverSocket = new ServerSocket(19999)) {
			while (true) {
				Socket socket = serverSocket.accept();
				Thread t = new Thread(new ServerTask(socket));
				t.start();

			}
		}
	}

	public static class ServerTask implements Runnable {
		private Socket socket;
		public ServerTask(Socket socket) {
			super();
			this.socket = socket;
		}
		@Override
		public void run() {
			
			try {
				System.out.println("卧槽？");
				SleepTools.second(2);
				Writer writer = new OutputStreamWriter(socket.getOutputStream());
				writer.write("qqqqqqqqqqqqqqqqqqqqqqq" + "\n");
				writer.write("time:" + System.currentTimeMillis() + "\r\n");
				writer.flush();
				System.out.println("大虎");
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(socket!=null) {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
