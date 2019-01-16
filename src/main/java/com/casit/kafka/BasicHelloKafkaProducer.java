package com.casit.kafka;


import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class BasicHelloKafkaProducer {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        //可靠性保证  标识多少个副本收到消息才认为成功
//        1-->首领副本收到就阔以   0-->不管收不收到就认为成功   all-->全部副本收到
        properties.put(ProducerConfig.ACKS_CONFIG, "1");
        
        // 表示没有收到发送成功的消息的数量（即有N个还未确认成功的消息最大值），下一个消息不可以发送。  
//        当设置为1时，可以严格保证消息顺序性，单个分区？。 会严重影响性能
        properties.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,5);
        
        //消息批量发送--待消息总大小超过BATCH_SIZE_CONFIG时，批量发送，同时生效--默认为16384
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        //消息批量发送--消息等待时间超过LINGER_MS_CONFIG时，批量发送，同时生效--默认为0 不延迟 来了就发
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 100);
        //压缩 none/gzip/snappy
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "none");
        
      //KafkaProducer线程安全//KafkaProducer线程安全//KafkaProducer线程安全//KafkaProducer线程安全
        KafkaProducer<String,String> producer
                = new KafkaProducer<String, String>(properties);
        try {
            ProducerRecord<String,String> record;
            try {
                record = new ProducerRecord<String,String>("hellokafka",
                        "key001","素质极地");
                producer.send(record);
                System.out.println("message is sent.");
                ProducerRecord<String,String> record2 = new ProducerRecord<String,String>("hellokafka",
                        "key002","骷髅王");
                producer.send(record2, new Callback() {
					@Override
					public void onCompletion(RecordMetadata metadata, Exception exception) {
						if(exception != null) {
							System.out.println("record2 error");
							exception.printStackTrace();
						}
						if(metadata!=null)
						{
							System.out.println("record2 sent,"+metadata.toString());
						}
					}
				});
                while(true) {}
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            producer.close();
        }
    }


}
