package com.casit.activemq;
/**
* 类说明:
* @author zhouhai
* @version 创建时间：2018年12月17日 下午4:10:00
*/

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class ActivemqQueueConsumer {


	
	@Autowired
	JmsTemplate jmsTemplate;

	
	@JmsListener(destination="${activemq.study.queue}")
//	@SendTo("${activemq.study.replyQueue}")
//	消息消费完毕，回复生产者信息可以用这种方式，同时返回值不用void 用String····

	//如果是客户端确认消费 没有抛出异常
	//最终会调用AbstractMessageListenerContainer类的commitIfNecessary方法中的message.acknowledge();
	//因此手动不会生效
	//本Consumer 效果等价于 自动自动ack
	public void receiveFromQueue(Message message,Session session){
		try {
			TextMessage textMessage = (TextMessage) message;
			System.out.println("consumer1 consume :" +textMessage.getText());
			reply(textMessage);
		} catch (JmsException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void reply(TextMessage receivedMessage) throws JmsException, JMSException {
		if(receivedMessage.getJMSReplyTo() == null) {
			return;
		}
		//receivedMessage.getJMSReplyTo() 也可以注入@Qualifier("studyReplyQueue")，消费端和生产端 协商好
		jmsTemplate.send(receivedMessage.getJMSReplyTo(), new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage respMessage = session.createTextMessage(receivedMessage.getJMSCorrelationID()+"消费完毕");
				respMessage.setJMSCorrelationID(receivedMessage.getJMSCorrelationID());
				return respMessage;
			}
		});
	}



}
