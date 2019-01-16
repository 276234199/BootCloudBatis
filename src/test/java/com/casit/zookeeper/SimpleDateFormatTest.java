package com.casit.zookeeper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类说明:sdf线程安全
 * 
 * @author zhouhai
 * @version 创建时间：2019年1月2日 下午4:43:23
 */
public class SimpleDateFormatTest {

	//线程不安全 除非加锁
	private static final SimpleDateFormat sdfUnSafe = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	public static Date parse(String strDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(strDate);
	}

	public static void main(String[] args) {
		
	}
	
}
