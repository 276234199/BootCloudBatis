package com.casit.thread.simulateBatchTask.entity;

/**
 * 类说明: 题目在DB中的题目 实体类
 * 
 * @author zhouhai
 * @version 创建时间：2018年8月9日 下午2:31:16
 */
public class QuestionInDBVo {
	// 题目id
	private final int id;
	// 题目详情，平均长度800字节
	private final String detail;
	// 题目内容的摘要
	private final String sha;
	
	
	public QuestionInDBVo(int id, String detail, String sha) {
		this.id = id;
		this.detail = detail;
		this.sha = sha;
	}

	public int getId() {
		return id;
	}

	public String getDetail() {
		return detail;
	}

	public String getSha() {
		return sha;
	}
}
