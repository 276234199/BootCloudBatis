package com.casit.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
* 类说明:nio
* @author zhouhai
* @version 创建时间：2018年11月6日 下午4:53:55
*/
public class NioFileTest {
	
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("D:/test2.txt");
		FileOutputStream fos = new FileOutputStream("D:/test3.txt");
		FileChannel inChannel = fis.getChannel();
		FileChannel outChannel = fos.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(10);
		while(inChannel.read(buffer) != -1) {
            //重设一下buffer的position=0，limit=position
			//从写模式切换到读模式 make buffer ready for read  
			buffer.flip();
			outChannel.write(buffer);
            //重置buffer，重设position=0,limit=capacity
			//切换到写模式 make buffer ready for writing  
			//一旦读完Buffer中的数据，需要让Buffer准备好再次被写入。可以通过clear()或compact()方法来完成。
			buffer.clear();
//			读完调用clear()或compact()方法。clear()方法会清空整个缓冲区。
//			compact()方法只会清除已经读过的数据。
//			任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。 
//			buffer.compact();
		}
		fis.close();
		fos.close();
		
	}

}
