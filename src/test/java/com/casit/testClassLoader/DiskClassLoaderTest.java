package com.casit.testClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 加载硬盘上的.class
 * @author ASUS
 *
 */
public class DiskClassLoaderTest {
	public static void main(String[] args) {
        // TODO Auto-generated method stub

        //创建自定义classloader对象。
        DiskClassLoader diskLoader = new DiskClassLoader("D:\\localLibs");
        try {
            //加载class文件
            Class<?> c = diskLoader.loadClass("com.google.gson.JsonObject");

            if(c != null){
                try {
                    Object obj = c.newInstance();
                    System.out.println(c.getDeclaredMethods());
                    Method method = c.getDeclaredMethod("size");
                    //通过反射调用size()
                    System.out.println(method.invoke(obj));
                } catch (InstantiationException | IllegalAccessException 
                        | NoSuchMethodException
                        | SecurityException | 
                        IllegalArgumentException | 
                        InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
