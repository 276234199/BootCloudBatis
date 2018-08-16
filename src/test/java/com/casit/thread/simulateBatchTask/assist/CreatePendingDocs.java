package com.casit.thread.simulateBatchTask.assist;


import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.casit.thread.simulateBatchTask.entity.SrcDocVo;


/**
* 
*类说明：生成待处理文档
* @author zhouhai
* @version 创建时间：2018年8月9日 下午4:24:22
*/
public class CreatePendingDocs {
	
	public static final int QUESTION_COUNT_IN_DOC = 80;

    /**
     * 生成一批待处理文档
     * @param docCount 生成的文档数量
     * @return 待处理文档列表
     */
    public static List<SrcDocVo> makePendingDoc(int count){
        Random r = new Random();
        //文档列表
        List<SrcDocVo> docList = new LinkedList<>();
        for(int i=0;i<count;i++){
            List<Integer> questionList = new LinkedList<Integer>();
            //每个文档中包含的题目列表
            for(int j=0;j< QUESTION_COUNT_IN_DOC;j++){
                int questionId = r.nextInt(SL_QuestionBank.SIZE_OF_QUESTION_Bank);
                questionList.add(questionId);
            }
            SrcDocVo pendingDocVo = new SrcDocVo("pending_"+i,questionList);
            docList.add(pendingDocVo);
        }
        return docList;
    }

}
