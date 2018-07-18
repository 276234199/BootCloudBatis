package com.casit.RabbitMQ;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitSender implements RabbitTemplate.ReturnCallback , RabbitTemplate.ConfirmCallback{

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

	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		System.out.println("sender return success" + message.toString() + "===" + replyText + "===" + exchange + "==="
				+ routingKey);
	}

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (ack) {
			System.out.println("消息发送成功 ");
		} else {
			System.out.println("消息发送失败" + cause + correlationData.toString());
		}
	}

}
