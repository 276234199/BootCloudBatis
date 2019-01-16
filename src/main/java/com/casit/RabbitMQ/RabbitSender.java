package com.casit.RabbitMQ;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author zhouhai
 * 带回调和通知的-------sender
 *
 */
@Component
public class RabbitSender implements RabbitTemplate.ReturnCallback , RabbitTemplate.ConfirmCallback {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public  void send(String exchange, String routingKey, String message) {
		this.rabbitTemplate.setReturnCallback(this);
		this.rabbitTemplate.setConfirmCallback(this);
//		this.rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
//			if (!ack) {
//				System.out.println(exchange+"消息发送失败" + cause + correlationData.toString());
//			} else {
//				System.out.println(exchange+"消息发送成功 ");
//			}
//		});
		this.rabbitTemplate.convertAndSend(exchange, routingKey, message);
	}

	public  void sendObj(String exchange, String routingKey, Object message) {
		this.rabbitTemplate.setReturnCallback(this);
		this.rabbitTemplate.setConfirmCallback(this);
		this.rabbitTemplate.convertAndSend(exchange, routingKey, message);
	}

	//配置文件中application.yml    publisherConfirms: true  |publisherReturns: true 已经把会自动把rabbitTemplate的mandatory属性设为true
	//被MQ退回的消息（没有;路由到queue接受等原因）
	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		System.out.println("发布返回sender return " +"replyCode:"+replyCode+ "===" + replyText + "===" + exchange + "==="
				+ routingKey + message.toString());
	}

	//发布确认 当消息可以路由时  MQ收到了消息，把消息路由到queue后，MQ会通知生产者确认  true
	//消息 不可路由 也会通知生产者确认  true
	//与消费者无关
	//rabbitmq内部错误会返回false
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (ack) {
			String id = correlationData==null?"":correlationData.getId();
			System.out.println("发布确认  消息已经confirm成功 "+id);
		} else {
			System.out.println("发布确认  消息已经confirm失败" + cause + correlationData.toString());
		}
	}

}
