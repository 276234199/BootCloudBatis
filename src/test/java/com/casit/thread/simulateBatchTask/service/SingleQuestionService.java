package com.casit.thread.simulateBatchTask.service;

import com.casit.thread.simulateBatchTask.assist.SL_QuestionBank;

/**
 *
 *类说明：调用单个题目的处理器对题目进行处理的服务实现
 *串行化的处理
 */
public class SingleQuestionService {

    /**
     * 对题目进行处理
     * @param questionId 题目id
     * @return 题目解析后的文本
     */
    public static String makeQuestion(Integer questionId){
        return BaseQuestionProcessor.makeQuestion(questionId,
                SL_QuestionBank.getQuetion(questionId).getDetail());
    }

}
