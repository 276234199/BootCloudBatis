package com.casit.io;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
* 类说明:
* @author zhouhai
* @version 创建时间：2018年10月23日 下午2:28:36
*/
public class TestURLEncoder {

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(URLEncoder.encode("http://baidu.com", "utf-8"));
	}
}
