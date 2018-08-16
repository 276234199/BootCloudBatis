package com.casit.thread.MyBatchTaskFrame;

import java.util.Random;

import com.casit.tools.SleepTools;

//判断数字的奇数偶数
public class TestTask implements ITaskProcessor<Integer, Boolean>{

	@Override
	public TaskResult<Boolean> taskExecute(Integer data) {
		Random r = new Random();
		int flag = r.nextInt(500);
		SleepTools.ms(flag);
		if(flag<=300) {//正常处理的情况
			Boolean bool = (data % 2) == 0 ? true : false ;
			return new TaskResult<Boolean>(bool);
		}else if(flag>301&&flag<=400) {//处理失败的情况
			return new TaskResult<Boolean>(TaskResultType.Failure,"Failure");
		}else {//发生异常的情况
			try {
				throw new RuntimeException("异常发生了！！");
			} catch (Exception e) {
				return new TaskResult<Boolean>(TaskResultType.Exception,e.getMessage());
			}
		}
	}

}
