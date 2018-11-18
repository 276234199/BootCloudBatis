package com.casit.socket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

import com.casit.socket.ServerSocketThreadsTest.ServerTask;
import com.casit.tools.SleepTools;

/**
 * 类说明:
 * 
 * @author zhouhai
 * @version 创建时间：2018年11月12日 下午8:45:26
 */
public class ServerSocketSimpleTest {

	public static void main(String[] args) throws IOException {
		try(ServerSocket serverSocket = new ServerSocket(19999)) {
			while(true) {
				try(Socket socket = serverSocket.accept()){
					System.out.println("卧槽？");
					SleepTools.second(10);
					Writer writer = new OutputStreamWriter(socket.getOutputStream());
					writer.write("time:" + System.currentTimeMillis()+"\n");
					writer.write("time:" + System.currentTimeMillis() + "\r\n");
					writer.flush();
					System.out.println("大虎");
				}
			}
		}

	}

}
