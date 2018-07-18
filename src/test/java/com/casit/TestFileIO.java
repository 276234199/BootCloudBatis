package com.casit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Date;

public class TestFileIO {

	public static void main(String args[]) throws IOException, ClassNotFoundException {
		File file = new File("D:\\test.txt");
		OutputStream out = new FileOutputStream(file);
		String hello ="HELLOWORLD大虎";
		byte[] b =  hello.getBytes();
		out.write(b);
		out.close();
		
		InputStream in = new FileInputStream(file);
		byte[] b1 = new byte[1024];
		int size = in.read(b1);
		System.out.println(size);
		System.out.println(new String(b1));
		in.close();
		
		File file2 = new File("D:\\test2.txt");
		ObjectOutputStream out2 = new ObjectOutputStream(new FileOutputStream(file2));
		out2.writeObject(new Date());
		out2.close();
		ObjectInputStream in2 = new ObjectInputStream(new FileInputStream(file2));
		Date date  = (Date)in2.readObject();
		System.out.println(date.getTime());
		in2.close();
	}

}
