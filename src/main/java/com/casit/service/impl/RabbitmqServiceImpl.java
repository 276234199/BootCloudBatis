package com.casit.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casit.RabbitMQ.RabbitSender;
import com.casit.service.RabbitmqService;
import com.casit.service.UserService;
@Service("rabbitService")
public class RabbitmqServiceImpl implements RabbitmqService{
	
	@Autowired
    private AmqpTemplate rabbitTemplate;
	
	@Autowired
	RabbitSender rabbitSender;

	@Override
	public void snedToQueue(String content) {

//		rabbitSender.send("directExchange", "userQueueBindKey", content);
		
		rabbitTemplate.convertAndSend("directExchange", "lalala", content+111111111+"--");
		rabbitTemplate.convertAndSend("directExchange", "dahubi", content+22222222+"--");
		rabbitTemplate.convertAndSend("directExchange", "xiaohubi", content+333333+"--");
		
		//广播
		rabbitTemplate.convertAndSend("fanoutExchange", "asdkjsdfkl", content+"-6666---- fanout");
		
		//直接发送到hello这个queue
		rabbitTemplate.convertAndSend("hello", content+"直接就到queue");
		
		rabbitTemplate.convertAndSend("topicExchange", "topic.error", content+4444444+"--");
		rabbitTemplate.convertAndSend("topicExchange", "topic.sdfdsfdsfdsf", content+55555555+"--");
		
		
		
	}

}
