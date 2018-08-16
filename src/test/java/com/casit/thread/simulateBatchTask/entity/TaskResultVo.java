package com.casit.thread.simulateBatchTask.entity;

import java.util.concurrent.Future;


/**
 *类说明：生成题目时返回结果的定义
 *@author zhouhai
 * @version 创建时间：2018年8月9日 下午2:31:16
 */
public class TaskResultVo {
	
	private final String questionDetail;
	private final Future<QuestionInCacheVo> questionFuture;
	
	/**
	 * 直接拿到结果questionDetail
	 * @param questionDetail
	 */
	public TaskResultVo(String questionDetail) {
		this.questionDetail = questionDetail;
		this.questionFuture = null;
	}
	
	/**
	 * 通过questionFuture.get().getQuestionDetail()拿到结果
	 * @param questionFuture
	 */
	public TaskResultVo(Future<QuestionInCacheVo> questionFuture) {
		this.questionDetail = null;
		this.questionFuture = questionFuture;
	}
	
	public String getQuestionDetail() {
		return questionDetail;
	}
	public Future<QuestionInCacheVo> getQuestionFuture() {
		return questionFuture;
	}
	
	

}
