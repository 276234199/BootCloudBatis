package com.casit.activemq;
/**
* 类说明:
* @author zhouhai
* @version 创建时间：2018年12月17日 下午4:10:00
*/

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

//@Component
//手动回复 如果没有回复 抛出异常也会出问题
public class ActivemqQueueConsumer2 {
	
	@Autowired
    private ClientAckJmsTemplate clientAckJmsTemplate;

	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	@Qualifier("studyQueue")
	Queue queue;
	
	@PostConstruct
	public void startListen() throws JMSException {
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					receive();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}
	
    public void receive() throws JMSException {
    	Message message;
        while ((message = clientAckJmsTemplate.receive(queue))!=null){
        		try {
					TextMessage textMessage = (TextMessage)message;
					System.out.println("consumer2 consume :" +textMessage.getText());
					reply(textMessage);
					//手动确认
					int i = 1/0;
				} catch (JmsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					//手动确认
					clientAckJmsTemplate.ackAndcloseSession(message);
				}
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
