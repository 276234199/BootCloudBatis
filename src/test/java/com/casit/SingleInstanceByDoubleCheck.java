package com.casit;

/**
 * 双重检查
 * volatile+双重检查 保证线程安全
 */
public class SingleInstanceByDoubleCheck {
	
	//使用volatile修饰 可以保证线程安全
	private volatile static SingleInstanceByDoubleCheck instance;

    private SingleInstanceByDoubleCheck(){}

    public static SingleInstanceByDoubleCheck getInstance(){

        //第一次检测
        if (instance==null){
            //同步
            synchronized (SingleInstanceByDoubleCheck.class){
                if (instance == null){
                    //多线程环境下可能会出现问题的地方
                	//由于指令重排序造成
                    instance = new SingleInstanceByDoubleCheck();
                }
            }
        }
        return instance;
    }

}
