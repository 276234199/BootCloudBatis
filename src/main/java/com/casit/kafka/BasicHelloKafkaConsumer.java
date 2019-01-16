package com.casit.kafka;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;

public class BasicHelloKafkaConsumer {

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		// 群组
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test1");

		// 同时生效 以下两个 组合使用 大小、时间谁先到 谁用
		// 一次最小字节数 不足不取
		properties.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 100);
		// 一次最多等500ms 过时了就直接取
		properties.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, 500);
		// 一次最大字节数
		properties.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, 1000);
		
		//不自动提交
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		
		//如果自动提交 5000ms提交一次
//		properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 5000);

		// KafkaConsumer线程不安全
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		try {
			consumer.subscribe(Collections.singletonList("hellokafka"));
			while (true) {
				// 批量接受 一接就是批量条
				ConsumerRecords<String, String> records = consumer.poll(500);
				for (ConsumerRecord<String, String> record : records) {
					System.out.println(String.format("topic:%s,分区：%d,偏移量：%d," + "key:%s,value:%s", record.topic(),
							record.partition(), record.offset(), record.key(), record.value()));
					// do my work
					// 打包任务投入线程池
				}
				
//				//异步提交，非阻塞，失败不会进行重试  ，所以来一波回调
//				consumer.commitAsync(new OffsetCommitCallback() {
//					@Override
//					public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
//						//do sth
//					}
//				});
//				
//				//手动提交（同步提交，失败会重试，直到成功为止，阻塞）  效率较低
//				consumer.commitSync();
				
			}
		} finally {
			consumer.close();

		}

	}

}
