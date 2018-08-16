package com.casit.thread.simulateBatchTask.service;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import com.casit.thread.simulateBatchTask.assist.SL_QuestionBank;
import com.casit.thread.simulateBatchTask.entity.QuestionInCacheVo;
import com.casit.thread.simulateBatchTask.entity.QuestionInDBVo;
import com.casit.thread.simulateBatchTask.entity.TaskResultVo;


/**
 *
 *类说明：并发处理题目的服务
 */
public class ParallerQuestionService {
	
	//已处理题目的缓存
	private static ConcurrentHashMap<Integer, QuestionInCacheVo> questionCache 
	  = new ConcurrentHashMap<>();
	
	//正在处理题目的缓存map
	private static ConcurrentHashMap<Integer, Future<QuestionInCacheVo>> 
		processingQuestionCache = new ConcurrentHashMap<>();
	
	private static ExecutorService makeQuestionService 
	= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2); 
	
	public static TaskResultVo makeQuestion(Integer questionId) {
		QuestionInCacheVo qstCacheVo = questionCache.get(questionId);
		if(null==qstCacheVo) {
			System.out.println("......题目["+questionId+"]在缓存中不存在，"
					+ "准备启动任务.");
			return new TaskResultVo(getQstFuture(questionId));
		}else {
			//拿摘要
			String questionSha = SL_QuestionBank.getSha(questionId);
			if(questionSha.equals(qstCacheVo.getQuestionSha())) {
				System.out.println("......题目["+questionId+"]在缓存中已存在，且未变化.");
				return new TaskResultVo(qstCacheVo.getQuestionDetail());
			}else {
				System.out.println("......题目["+questionId+"]在缓存中已存在，"
						+ "但是发生了变化，更新缓冲.");
				return new TaskResultVo(getQstFuture(questionId));
			}
		}
	}
	//缓存中没有 开启线程去DB中拿数据
	private static Future<QuestionInCacheVo> getQstFuture(Integer questionId){
		//用于判定是否有线程正在处理本任务
		Future<QuestionInCacheVo> questionFuture 
			= processingQuestionCache.get(questionId);
		try {
			if(questionFuture==null) {
				QuestionInDBVo qstDbVo = SL_QuestionBank.getQuetion(questionId);
				QuestionTask questionTask = new QuestionTask(qstDbVo,questionId);
				
				
				//必须先测试放入map，在执行任务，避免两个线程处理同一个题目
				//concurrentmap不能放入null，因此要把为null的futre封装为futuretask，放入map中
				
				//注释代码会出错 ，因为concurrentmap中放入了null
//				if(processingQuestionCache.putIfAbsent(questionId, questionFuture)==null) {
//					FutureTask<QuestionInCacheVo> ft = new FutureTask<QuestionInCacheVo>(questionTask);
//					makeQuestionService.execute(ft);
//					return ft;
//				}else {
//					System.out.println("<<<<<<<<<<<有其他线程刚刚启动了题目["+questionId
//							+"]的计算任务，本任务无需开启！");
//				}
				
				//必须先测试放入map，在执行任务，避免两个线程处理同一个题目
				//concurrentmap不能放入null，因此要把为null的futre封装为futuretask，放入map中
				//先在map中占位
				FutureTask<QuestionInCacheVo> ft = new FutureTask<QuestionInCacheVo>(questionTask);
				if(processingQuestionCache.putIfAbsent(questionId,ft)==null) {
					//ft付给返回值questionFuture
					questionFuture = ft;
					makeQuestionService.execute(ft);
//					makeQuestionService.submit(ft);
					System.out.println("成功启动了题目["+questionId+"]的计算任务，请等待完成>>>>>>>>");
				}else {
					System.out.println("<<<<<<<<<<<有其他线程刚刚启动了题目["+questionId
							+"]的计算任务，本任务无需开启！");
				}
			}else {
				 System.out.println("题目["+questionId+"]已存在计算任务，无需重新生成.");
			}
		} catch (Exception e) {
			processingQuestionCache.remove(questionId);
			e.printStackTrace();
			throw e;
			
		}
		return questionFuture;
	}
	
	
	//解析题目的任务类
	private static class QuestionTask implements Callable<QuestionInCacheVo>{
		
		private QuestionInDBVo qstDbVo;
		private Integer questionId;
		
		public QuestionTask(QuestionInDBVo qstDbVo, Integer questionId) {
			super();
			this.qstDbVo = qstDbVo;
			this.questionId = questionId;
		}

		@Override
		public QuestionInCacheVo call() throws Exception {
			try {
				String qstDetail = BaseQuestionProcessor.makeQuestion(questionId,
						SL_QuestionBank.getQuetion(questionId).getDetail());
				String questionSha = qstDbVo.getSha();
				QuestionInCacheVo qstCache = new QuestionInCacheVo(qstDetail, questionSha);
				questionCache.put(questionId, qstCache);
				return qstCache;
			} finally {
				//不管生成题目的任务正常与否，这个任务都要从正在处理题目的缓存中移除
				processingQuestionCache.remove(questionId);
			}
		}
		
	}

}
