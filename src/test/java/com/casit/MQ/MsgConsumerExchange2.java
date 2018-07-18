package com.casit.MQ;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.core.ExchangeTypes;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class MsgConsumerExchange2 {
	
	private static final String EXCHANGE_NAME = "logs_exchange";
	
	private final static boolean durable = true;
	
	private final static boolean autoDelete = false;

	public static void main(String[] argv) throws IOException, TimeoutException {

		// 获取到连接以及mq通道
		Connection connection = RabbitMqConnectionUtil.getConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME,ExchangeTypes.FANOUT, durable,autoDelete,null);
		//生成临时队列，不持久化自动删除
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, EXCHANGE_NAME, "");

		System.out.println("MsgExchangeConsumer2 [*] Waiting for messages.");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println("MsgExchangeConsumer2 [x] Received '" + message + "'");
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
		};
		channel.basicConsume(queueName, false, consumer);
	}

}
