package com.casit.RabbitMQ;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
@RabbitListener(queues = "hello")
public class HelloQueueConsumer {
	
	@RabbitHandler
    public void process(String hello,Channel channel, Message message) throws IOException {
        System.out.println("queue:hello|||Receive content : " + hello);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);  
    }

}
