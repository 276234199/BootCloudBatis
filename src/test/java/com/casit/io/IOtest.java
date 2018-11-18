package com.casit.io;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 类说明:
 * 
 * @author zhouhai
 * @version 创建时间：2018年10月22日 下午10:08:29
 */
public class IOtest {

	public static void main(String[] args) throws IOException {


		
		String sss = "n1王";
		byte[] bytes = sss.getBytes();
		for(byte b :bytes) {
			System.out.println(b);
		}
		System.out.println("------------------------------------------------");
		
		String filepath = "D:\\test.txt";
		String outPath = "D:\\test2.txt";
		
		PrintWriter pw =  new PrintWriter(filepath, "UTF-8");
		pw.print("骷髅王");
		pw.println(123);
		pw.println("巨魔");
		pw.print("dahubi");
		pw.flush();
		pw.close();
		System.out.println(pw.checkError());
		
		System.out.println("------------------------------------------------");
		readFileByByte(filepath);
		System.out.println("------------------------------------------------");
		readFileByCharacter(filepath);
		System.out.println("------------------------------------------------");
		readFileByLine(filepath);
		System.out.println("------------------------------------------------");
		readFileByBybeBuffer(filepath, outPath);
		
		
	}
	
	/** 
     * 以字节为单位读取文件内容 
     * @param filePath：需要读取的文件路径 
     */  
	public static void readFileByByte(String filePath) {  
        File file = new File(filePath);  
        // InputStream:此抽象类是表示字节输入流的所有类的超类。  
        InputStream ins = null ;  
        try{  
            // FileInputStream:从文件系统中的某个文件中获得输入字节。  
            ins = new FileInputStream(file);  
            int temp ;  
            // read():从输入流中读取数据的下一个字节。  
            while((temp = ins.read())!=-1){  
                System.out.write(temp);  
            }  
        }catch(Exception e){  
            e.getStackTrace();  
        }finally{  
            if (ins != null){  
                try{  
                    ins.close();  
                }catch(IOException e){  
                    e.getStackTrace();  
                }  
            }  
        }  
    } 

	 /** 
     * 以字符为单位读取文件内容 
     * @param filePath 
     */  
    public static void readFileByCharacter(String filePath){  
        File file = new File(filePath);  
        // FileReader:用来读取字符文件的便捷类。  
        FileReader reader = null;  
        try{  
            reader = new FileReader(file);  
            int temp ;  
            while((temp = reader.read()) != -1){  
                if (((char) temp) != '\r') {  
                    System.out.print((char) temp);  
                }  
            }  
        }catch(IOException e){  
            e.getStackTrace();  
        }finally{  
            if (reader != null){  
                try {  
                    reader.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
      
    /** 
     * 以行为单位读取文件内容 
     * @param filePath 
     */  
    public static void readFileByLine(String filePath){  
        File file = new File(filePath);  
        // BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。  
        BufferedReader buf = null;  
        try{  
            // FileReader:用来读取字符文件的便捷类。  
            buf = new BufferedReader(new FileReader(file));  
            // buf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));  
            String temp = null ;  
            while ((temp = buf.readLine()) != null ){  
                System.out.println(temp);  
            }  
        }catch(Exception e){  
            e.getStackTrace();  
        }finally{  
            if(buf != null){  
                try{  
                    buf.close();  
                } catch (IOException e) {  
                    e.getStackTrace();  
                }  
            }  
        }  
    }  
    
    /** 
     * 使用Java.nio ByteBuffer字节将一个文件输出至另一文件 
     *  
     * @param filePath 
     */  
    public static void readFileByBybeBuffer(String filePath,String outPath) {  
        FileInputStream in = null;  
        FileOutputStream out = null;  
        try {  
            // 获取源文件和目标文件的输入输出流    
            in = new FileInputStream(filePath);  
            out = new FileOutputStream(outPath);  
            // 获取输入输出通道  
            FileChannel fcIn = in.getChannel();  
            FileChannel fcOut = out.getChannel();  
            ByteBuffer buffer = ByteBuffer.allocate(1024);  
            while (true) {  
                // clear方法重设缓冲区，使它可以接受读入的数据  
                buffer.clear();  
                // 从输入通道中将数据读到缓冲区  
                int r = fcIn.read(buffer);  
                if (r == -1) {  
                    break;  
                }  
                // flip方法让缓冲区可以将新读入的数据写入另一个通道    
                buffer.flip();  
                fcOut.write(buffer);  
            }  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (in != null && out != null) {  
                try {  
                    in.close();  
                    out.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
    
    
}
