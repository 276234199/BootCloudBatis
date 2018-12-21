package com.casit.activemq;
/**
* 类说明:topic消息不会持久化 queue会 
* @author zhouhai
* @version 创建时间：2018年12月17日 下午4:10:00
*/

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class ActivemqProducer {
	
	@Autowired
	JmsMessagingTemplate jmsMsgTemplate;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	@Qualifier("studyQueue")
	Queue queue;
	
	@Autowired
	@Qualifier("studyReplyQueue")
	Queue replyQueue;
	
	@Autowired
	@Qualifier("studyTopic")
	Topic topic;
	
	@Autowired
	@Qualifier("studyDurableTopic")
	Topic durableTopic;
	
	//http://localhost:8080/hello/testActivemq?msg=rrrrrrrrrrrrrrrrrrrrrrruuuuuuuuuuuuuuuaaaaaaaaaaaaaaaaa
	public void sendMsg(String msg) {
		
//		jmsTemplate.convertAndSend(queue,msg+",studyQueue 客户端自动应答 不需要把处理结果推入应答队列");

		
		jmsTemplate.send(queue, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage createTextMessage = session.createTextMessage(msg+",studyQueue 需要消费端手动应答  需要把处理结果推入应答队列");
				createTextMessage.setJMSReplyTo(replyQueue);
				//随便起个id
				createTextMessage.setJMSCorrelationID(UUID.randomUUID().toString());
				return createTextMessage;
			}
		});
		
		
		jmsTemplate.send(queue,(Session session) -> {
			TextMessage createTextMessage = session.createTextMessage(msg+",延迟 10s后  从mq推到消费者");
			createTextMessage.setJMSReplyTo(replyQueue);
			//随便起个id
			createTextMessage.setJMSCorrelationID(UUID.randomUUID().toString());
			createTextMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 10000);
			return createTextMessage;
			}
		);
		
		//自动应答
//		jmsTemplate.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
//		jmsMsgTemplate.convertAndSend(topic,msg+",studyTopic msg1");
//		jmsTemplate.send(topic,new MessageCreator() {
//			@Override
//			public Message createMessage(Session session) throws JMSException {
//				TextMessage createTextMessage = session.createTextMessage(msg+",studyTopic msg2");
//				return createTextMessage;
//			}
//		});
//		
//		jmsMsgTemplate.convertAndSend(durableTopic,msg+",durableTopic durable msg");

	}
	
	//监听消费者的响应队列
	@JmsListener(destination="${activemq.study.replyQueue}")
	public void onReply(Message message) throws JMSException {
		TextMessage textMessage = (TextMessage) message;
		System.out.println("get reply from consumer : "+textMessage.getJMSCorrelationID()+",msg:" +textMessage.getText());
	}

}
