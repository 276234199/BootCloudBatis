package com.casit.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casit.RabbitMQ.RabbitSender;
import com.casit.service.RabbitmqService;
import com.casit.service.UserService;
@Service("rabbitService")
@com.alibaba.dubbo.config.annotation.Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class RabbitmqServiceImpl implements RabbitmqService{
	
	@Autowired
    private AmqpTemplate rabbitTemplate;
	
	@Autowired
	RabbitSender rabbitSender;

	@Override
	public void snedToQueue(String content) {
		rabbitTemplate.convertAndSend("hello", content);
		rabbitTemplate.convertAndSend("exchange", "topic.message", content);
		rabbitTemplate.convertAndSend("exchange", "topic.sdfdsfdsfdsf", content+"#");
		rabbitTemplate.convertAndSend("fanoutExchange", "asdkjsdfkl", content+" fanout");
		
		rabbitSender.send("directExchange", "userQueueBindKey", content);
		
	}

}
