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

public class MsgConsumerTopicsExchange {

	private final static String Topics_EXCHANGE_NAME = "topics_exchange";

	private final static String TopicsBindingKey = "#.key1.#";

	private final static boolean durable = true;

	private final static boolean autoDelete = false;

	public static void main(String argsp[]) throws IOException, TimeoutException {

		Connection connection = RabbitMqConnectionUtil.getConnection();
		Channel channel = connection.createChannel();

		// 声明（创建）Topics_EXCHANGE
		channel.exchangeDeclare(Topics_EXCHANGE_NAME, ExchangeTypes.TOPIC, durable, autoDelete, null);
		//生成临时队列，不持久化自动删除
		String queueName = channel.queueDeclare().getQueue();

		channel.queueBind(queueName, Topics_EXCHANGE_NAME, TopicsBindingKey);

		System.out.println(" Topics_EXCHANGE [*] Waiting for messages");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println("Topics_EXCHANGE [x] Received '" + message + "'");
				// channel.basicAck(envelope.getDeliveryTag(), false);
			}
		};
		channel.basicConsume(queueName, true, consumer);

	}
}
