package com.casit.thread.simulateBatchTask.service;


import java.util.List;

import com.casit.thread.simulateBatchTask.assist.CreatePendingDocs;
import com.casit.thread.simulateBatchTask.assist.SL_QuestionBank;
import com.casit.thread.simulateBatchTask.entity.SrcDocVo;

/**
 *
 *类说明：最初的实现，速度较慢，需要进行性能提升
 *没用使用多线程技术 非常low
 */
public class SingleWeb {
    public static void main(String[] args) {
        System.out.println("题库开始初始化...........");
        SL_QuestionBank.initBank();
        System.out.println("题库初始化完成。");
        
        //创建两个待处理文档
        List<SrcDocVo> docList = CreatePendingDocs.makePendingDoc(2);
        long startTotal = System.currentTimeMillis();
        for(SrcDocVo doc:docList){
            System.out.println("开始处理文档："+doc.getDocName()+".......");
            long start = System.currentTimeMillis();
            String localName = ProduceDocService.makeDocSingle(doc);
            System.out.println("文档"+localName+"生成耗时："
            		+(System.currentTimeMillis()-start)+"ms");
            start = System.currentTimeMillis();
            String remoteUrl = ProduceDocService.upLoadDoc(localName);
            System.out.println("已上传至["+remoteUrl+"]耗时："
            		+(System.currentTimeMillis()-start)+"ms");
        }
        System.out.println("------------共耗时："
        		+(System.currentTimeMillis()-startTotal)+"ms-------------");
    }
}
