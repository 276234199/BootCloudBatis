package com.casit.MQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.core.ExchangeTypes;

public class BasicMsgProducer {

	private final static String QUEUE_NAME = "rabbit_persist";

	private final static String EXCHANGE_NAME = "logs_exchange";

	private final static String Routing_EXCHANGE_NAME = "routing_exchange";

	private final static String Routing = "Routing";

	private final static String Topics_EXCHANGE_NAME = "topics_exchange";

	private final static String Topics = "key1.key2.key3";

	private final static boolean durable = true;

	private final static boolean autoDelete = false;

	public static void main(String[] args) throws IOException, TimeoutException {

		Connection connection = RabbitMqConnectionUtil.getConnection();
		// 从连接中创建通道
		Channel channel = connection.createChannel();

		// 声明（创建）队列
		channel.queueDeclare(QUEUE_NAME, durable, false, autoDelete, null);
		// 发消息到队列
		for (int i = 0; i < 50; i++) {
			String message = "Hello Rabbit!" + i;
			channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
			System.out.println("Queue [x] Sent '" + message + "'");
		}

		// 声明（创建）exchange
		channel.exchangeDeclare(EXCHANGE_NAME, ExchangeTypes.FANOUT, durable, autoDelete, null);
		// 发消息到exchange
		for (int i = 0; i < 50; i++) {
			String message = "Hello Exchange!" + i;
			channel.basicPublish(EXCHANGE_NAME, "", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
			System.out.println("Exchange [x] Sent '" + message + "'");
		}

		// 声明（创建）Routing_EXCHANGE
		channel.exchangeDeclare(Routing_EXCHANGE_NAME, ExchangeTypes.DIRECT, durable, autoDelete, null);
		// 发消息到exchange
		for (int i = 0; i < 50; i++) {
			String message = "Hello Exchange!" + i;
			channel.basicPublish(Routing_EXCHANGE_NAME, Routing, MessageProperties.PERSISTENT_TEXT_PLAIN,
					message.getBytes());
			System.out.println("Routing_EXCHANGE [x] Sent '" + message + "'");
		}

		// 声明（创建）Topics_EXCHANGE
		channel.exchangeDeclare(Topics_EXCHANGE_NAME, ExchangeTypes.TOPIC, durable, autoDelete, null);
		// 发消息到exchange
		for (int i = 0; i < 50; i++) {
			String message = "Hello Exchange!" + i;
			channel.basicPublish(Topics_EXCHANGE_NAME, Topics, MessageProperties.PERSISTENT_TEXT_PLAIN,
					message.getBytes());
			System.out.println("Topics_EXCHANGE [x] Sent '" + message + "'");
		}

		channel.close();
		connection.close();
	}

}
