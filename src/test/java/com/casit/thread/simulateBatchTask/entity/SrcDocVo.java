package com.casit.thread.simulateBatchTask.entity;

import java.util.List;

/**
 * 类说明: 待处理的文档，包含题目列表
 * @author zhouhai
 * @version 创建时间：2018年8月9日 下午2:31:16
 */
public class SrcDocVo {
    //待处理文档名称
    private final String docName;
    //待处理文档中题目id列表
    private final List<Integer> questionList;

    public SrcDocVo(String docName, List<Integer> questionList) {
        this.docName = docName;
        this.questionList = questionList;
    }

    public String getDocName() {
        return docName;
    }

    public List<Integer> getQuestionList() {
        return questionList;
    }
}
