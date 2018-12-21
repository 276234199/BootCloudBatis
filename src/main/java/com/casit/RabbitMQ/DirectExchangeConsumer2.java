package com.casit.RabbitMQ;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
@RabbitListener(queues = "userQueue")
public class DirectExchangeConsumer2 {
	@RabbitHandler
    public void process(String hello,Channel channel, Message message) throws IOException {
        System.out.println("DirectExchangeConsumer2  userQueue|||Receive content : " + hello 
        		+"  |!bindingkey:"+message.getMessageProperties().getReceivedRoutingKey()
        		+"  |exchange:"+message.getMessageProperties().getReceivedExchange()
        		+"   |queue:"+message.getMessageProperties().getConsumerQueue());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);  
    }
}
