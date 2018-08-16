package com.casit.thread.simulateBatchTask.service;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.casit.thread.simulateBatchTask.assist.CreatePendingDocs;
import com.casit.thread.simulateBatchTask.assist.SL_QuestionBank;
import com.casit.thread.simulateBatchTask.entity.SrcDocVo;


/**
 * 
 * 类说明：rpc服务端，采用生产者消费者模式，生产者消费者还会级联
 * 
 */
public class RpcModeWeb {

	// 负责生成文档
	private static ExecutorService docMakeService = Executors
			.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

	// 负责上传文档
	private static ExecutorService docUploadService = Executors
			.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

	//先完成的先执行CompletionService
	private static CompletionService<String> docCs = new ExecutorCompletionService<>(docMakeService);

	private static CompletionService<String> docUploadCs = new ExecutorCompletionService<>(docUploadService);

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println("题库开始初始化...........");
		SL_QuestionBank.initBank();
		System.out.println("题库初始化完成。");

		// 创建60个待处理文档
		List<SrcDocVo> docList = CreatePendingDocs.makePendingDoc(60);
		long startTotal = System.currentTimeMillis();

		for (SrcDocVo doc : docList) {
			docCs.submit(new MakeDocTask(doc));
		}
		
		for (SrcDocVo doc : docList) {
			Future<String> future = docCs.take();
			docUploadCs.submit(new UploadDocTask(future.get(),doc.getDocName()));
		}

		// 在实际的业务过程中可以不要，主要为了取得时间
		for (SrcDocVo doc : docList) {
			docUploadCs.take().get();
		}

		System.out.println("------------共耗时：" + (System.currentTimeMillis() - startTotal) + "ms-------------");
	}

	// 生成文档的任务
	private static class MakeDocTask implements Callable<String> {

		private SrcDocVo pendingDocVo;

		public MakeDocTask(SrcDocVo pendingDocVo) {
			super();
			this.pendingDocVo = pendingDocVo;
		}

		@Override
		public String call() throws Exception {
			long start = System.currentTimeMillis();
			// 单线程生成文档
			// String localName = ProduceDocService.makeDocSingle(pendingDocVo);
			// 多线程生成文档
			String localName = ProduceDocService.makeDocAsyn(pendingDocVo);
			System.out.println("文档" + localName + "生成耗时：" + (System.currentTimeMillis() - start) + "ms");
			return localName;
		}
	}

	// 上传文档的任务
	private static class UploadDocTask implements Callable<String> {

		private  String filePath;
		
		private  String docName;

		public UploadDocTask(String filePath , String docName) {
			super();
			this.filePath = filePath;
			this.docName = docName;
		}

		@Override
		public String call() throws Exception {
			long start = System.currentTimeMillis();
			String remoteUrl = ProduceDocService.upLoadDoc(filePath);
			System.out.println(docName+"已上传至[" + remoteUrl + "]耗时：" + (System.currentTimeMillis() - start) + "ms");
			return remoteUrl;
		}
	}

}
