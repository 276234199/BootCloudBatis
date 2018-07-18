package com.casit.MQ;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;    
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class MsgConsumer {

	private final static String QUEUE_NAME = "rabbit_persist";  
	private final static boolean durable = true;
	private final static boolean autoDelete = false;
    
    public static void main(String[] argv) throws Exception {    
    
    	 // 获取到连接以及mq通道
        Connection connection = RabbitMqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, durable, false, autoDelete, null);
        
        channel.basicQos(1);
        

        // 定义队列的消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                throws IOException {
              String message = new String(body, "UTF-8");
              System.out.println(" consumer1[x] Received '" + message + "'");
              doWork(message);
              channel.basicAck(envelope.getDeliveryTag(), false);
            }
          };
        // 监听队列,手动回复
        channel.basicConsume(QUEUE_NAME, false, consumer);
        
    }  
    
	private static void doWork(String task) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
