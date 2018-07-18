package com.casit;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TestRandomAccessFileIO {

	public static void main(String args[]) throws IOException {
		File file = new File("D:\\test.txt");
		if (file.exists()) {
			System.out.println("文件名：" + file.getName());
			System.out.println("文件路径：" + file.getPath());
			System.out.println("文件最后修改时间：" + file.lastModified());
		} else {
			file.createNewFile();
		}

		RandomAccessFile rdf = new RandomAccessFile(file, "rw");// 读写模式，如果文件不存在，会自动创建
		String name = null;
		int age = 0;
		name = "zhangsan"; // 字符串长度为8
		age = 30; // 数字的长度为4
		rdf.writeBytes(name); // 将姓名写入文件之中
		rdf.writeInt(age); // 将年龄写入文件之中
		name = "lisi    "; // 字符串长度为8
		age = 31; // 数字的长度为4
		rdf.writeBytes(name); // 将姓名写入文件之中
		rdf.writeInt(age); // 将年龄写入文件之中
		name = "wangwu  "; // 字符串长度为8
		age = 32; // 数字的长度为4
		rdf.writeBytes(name); // 将姓名写入文件之中
		rdf.writeInt(age); // 将年龄写入文件之中
		
		rdf.close();
		
		rdf = new RandomAccessFile(file, "rw");// 读写模式，如果文件不存在，会自动创建

		byte b[] = new byte[8]; // 开辟byte数组
		// 读取第二个人的信息，意味着要空出第一个人的信息
		rdf.skipBytes(12); // 跳过第一个人的信息
		for (int i = 0; i < b.length; i++) {
			b[i] = rdf.readByte(); // 读取一个字节
		}
		name = new String(b); // 将读取出来的byte数组变为字符串
		age = rdf.readInt(); // 读取数字
		System.out.println("第二个人的信息 --> 姓名：" + name + "；年龄：" + age);
		// 读取第一个人的信息
		rdf.seek(0); // 指针回到文件的开头
		for (int i = 0; i < b.length; i++) {
			b[i] = rdf.readByte(); // 读取一个字节
		}
		name = new String(b); // 将读取出来的byte数组变为字符串
		age = rdf.readInt(); // 读取数字
		System.out.println("第一个人的信息 --> 姓名：" + name + "；年龄：" + age);
		rdf.skipBytes(12); // 空出第二个人的信息
		for (int i = 0; i < b.length; i++) {
			b[i] = rdf.readByte(); // 读取一个字节
		}
		name = new String(b); // 将读取出来的byte数组变为字符串
		age = rdf.readInt(); // 读取数字
		System.out.println("第三个人的信息 --> 姓名：" + name + "；年龄：" + age);

		rdf.close();

	}

}
