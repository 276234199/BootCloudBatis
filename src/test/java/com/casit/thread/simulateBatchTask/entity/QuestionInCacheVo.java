package com.casit.thread.simulateBatchTask.entity;
/**
 *类说明：题目在缓存中的实体
* @author zhouhai
* @version 创建时间：2018年8月9日 下午2:31:16
 */
public class QuestionInCacheVo {
	
	private final String questionDetail;
	private final String questionSha;
	
	public QuestionInCacheVo(String questionDetail, String questionSha) {
		super();
		this.questionDetail = questionDetail;
		this.questionSha = questionSha;
	}
	public String getQuestionDetail() {
		return questionDetail;
	}
	public String getQuestionSha() {
		return questionSha;
	}
	
	

}
