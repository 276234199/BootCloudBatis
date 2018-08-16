
/**
 * 模拟实战 多线程处理批量任务、
 * 业务流程：
 * 从DB中获取多个题目--》每个题目单独处理--》
 * 将处理后的题目组装成一个练习册（每个用户的练习册不同）--》
 * 生成离线PDF--》上传至oss，供用户下载
 * @author ASUS
 */
package com.casit.thread.simulateBatchTask;