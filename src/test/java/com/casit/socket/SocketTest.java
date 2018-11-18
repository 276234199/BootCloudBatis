package com.casit.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 类说明:
 * 
 * @author zhouhai
 * @version 创建时间：2018年10月18日 上午11:03:25
 */
public class SocketTest {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s = new Socket("localhost", 19999);
		s.setSoTimeout(15000);
		InputStreamReader reader = new InputStreamReader(s.getInputStream(), "ASCII");

		BufferedReader bufferedReader = new BufferedReader(reader);
		String str;
		while ((str = bufferedReader.readLine()) != null) {
			System.out.println(str);
		}

		// StringBuilder sb = new StringBuilder();
		// for(int c=reader.read();c!=-1;c=reader.read()) {
		// sb.append((char)c);
		// }
		// System.out.println(sb);

		s.close();
	}

}
