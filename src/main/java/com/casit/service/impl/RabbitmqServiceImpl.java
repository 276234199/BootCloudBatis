package com.casit.service.impl;

import java.util.UUID;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casit.RabbitMQ.RabbitSender;
import com.casit.service.RabbitmqService;
import com.casit.service.UserService;
@Service("rabbitService")
public class RabbitmqServiceImpl implements RabbitmqService{
	
	@Autowired
    private RabbitTemplate rabbitTemplate;
	
	@Autowired
	RabbitSender rabbitSender;

	@Override
	public void snedToQueue(String content) {

		//配置文件中application.yml    publisherConfirms: true  |publisherReturns: true 已经把会自动把rabbitTemplate的mandatory属性设为true
		//被MQ退回的消息（没有queue接受等原因）
		rabbitTemplate.setReturnCallback(rabbitSender);
		
		//发布确认 当消息可以路由时  MQ收到了消息，把消息路由到queue后，MQ会通知生产者确认  true
		//消息 不可路由 也会通知生产者确认  true
		//与消费者无关
		//rabbitmq内部错误会返回false
		rabbitTemplate.setConfirmCallback(rabbitSender);
		

		//直接发送到hello这个queue
		rabbitTemplate.convertAndSend("hello", content+"直接就到queue");

		//directExchange
		rabbitTemplate.convertAndSend("directExchange", "lalala", content+111111111+"--");
		rabbitTemplate.convertAndSend("directExchange", "dahubi", content+22222222+"--");
		rabbitTemplate.convertAndSend("directExchange", "xiaohubi", content+333333+"--");
		rabbitTemplate.convertAndSend("directExchange", "xiaohubibibibib", content+"-?????????????-");
		
		MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
			
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				message.getMessageProperties().getHeaders().put("rua", "rua");
				return message;
			}
		};
		//多参数
		rabbitTemplate.convertAndSend("directExchange", "xiaohubi", content+"messagePostProcessor多参数--", messagePostProcessor );
		
		//广播
		rabbitTemplate.convertAndSend("fanoutExchange", "asdkjsdfkl", content+"-999999---- fanout");
		
		
		//topic
		rabbitTemplate.convertAndSend("topicExchange", "topic.error", content+4444444+"--");
		rabbitTemplate.convertAndSend("topicExchange", "topic.sdfdsfdsfdsf", content+55555555+"--");
		rabbitTemplate.convertAndSend("topicExchange", "topic.kkkkkkk.xxxxxxx", content+6666666+"--");
		

		
	}

}
