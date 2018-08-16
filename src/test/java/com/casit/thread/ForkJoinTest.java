package com.casit.thread;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;


/**
 * 
 * @author ASUS
 *forjoin
 *fork 分拆总任务，多个子任务join 
 *JAVA8 Stream API 似乎比 fork join更6
 *fork join 适用于 复杂的逻辑 似乎
 * 比如此处 count = count+arr【i】 时 sleep 1ms 模拟 复杂的逻辑
 * 
 * 如果单纯的逻辑 直接相加 不sleep 单线程更快
 * 多线程在上下文切换 会有较大花销
 */
public class ForkJoinTest {
	
	//总数量
	private  static  Integer arrLength = 30000;
	
	//fork任务阈值  最多3000个数  单任务处理
	private  static  Integer thresHold = 3000;
	
	public static void main(String[] args) throws InterruptedException {

        ForkJoinPool pool = new ForkJoinPool();
        Integer[] src = makeArray(arrLength);

        myReCursiveTask task = new myReCursiveTask(src,0,src.length-1);

        long start = System.currentTimeMillis();

        System.out.println("fork join Task is Running.....");
        //同步调用
        pool.invoke(task);
        
        //异步调用 RecursiveAction  无返回值
 //       pool.execute(RecursiveAction);

        System.out.println("fork join The count is "+task.join()
                +" spend time:"+(System.currentTimeMillis()-start)+"ms");
        
        /////////////单线程对比
        System.out.println("startSingle is Running.....");
        long startSingle = System.currentTimeMillis();
        
        Integer count = 0;
        
        for(Integer i=0;i<src.length;i++) {
        	Thread.sleep(1);
	    	count = count + src[i];
		}
        
        System.out.println("single thread The count is "+count
        +" spend time:"+(System.currentTimeMillis()-startSingle)+"ms");

    }
	
	
	public static class myReCursiveTask extends RecursiveTask<Integer>{
		
		private static final long serialVersionUID = 1213213123L;

		//总任务数组
		private Integer[] srcArr;
		
		//开始统计的数组下标
		private Integer fromIndex;
		
		//结束统计的数组下标
		private Integer toIndex;

		public myReCursiveTask(Integer[] srcArr, Integer fromIndex, Integer toIndex) {
			super();
			this.srcArr = srcArr;
			this.fromIndex = fromIndex;
			this.toIndex = toIndex;
		}

		@Override
		protected Integer compute() {
			if(toIndex-fromIndex < thresHold) {
				Integer count = 0;
				for(Integer i=fromIndex;i<=toIndex;i++) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			    	count = count + srcArr[i];
				}
				return count;
			}else {
				//fromIndex....mid....toIndex
				//1...................70....100
				Integer mid = (fromIndex+toIndex)/2;
				myReCursiveTask left = new myReCursiveTask(srcArr,fromIndex,mid);
				myReCursiveTask right = new myReCursiveTask(srcArr,mid+1,toIndex);
				invokeAll(left,right);
				return left.join()+right.join();
			}
		}
		
	} 
	
	//产生整形数组
	public  static Integer[] makeArray(int arr_legnth) {

        //new一个随机数发生器
        Random r = new Random();
        Integer[] result = new Integer[arr_legnth];
        for(int i=0;i<arr_legnth;i++){
            //用随机数填充数组
            result[i] =  r.nextInt(arr_legnth*3);
        }
        return result;

    }
}
