package com.casit.activemq;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTempTopic;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;

/**
 * 类说明:Activemq配置文件
 * 
 * @author zhouhai
 * @version 创建时间：2018年12月17日 下午3:48:24
 */
@Configuration
@EnableJms
public class ActivemqConfig {

	@Value("${activemq.study.queue}")
	String queueName;

	@Value("${activemq.study.replyQueue}")
	String replyQueueName;

	@Value("${activemq.study.topic}")
	String topicName;

	@Value("${activemq.study.durableTopic}")
	String durableTopicName;
	
	//消息重发配置
	@Bean
	public RedeliveryPolicy redeliveryPolicy(){
	        RedeliveryPolicy  redeliveryPolicy=   new RedeliveryPolicy();
	        //是否在每次尝试重新发送失败后,增长这个等待时间  启用指数倍数递增的方式增加延迟时间
//	        redeliveryPolicy.setUseExponentialBackOff(true);
	        //第一次失败后重新发送之前等待500毫秒,第二次失败再等待500 * 2毫秒,这里的2就是value <2倍>  再之后就是1000*2
//	        redeliveryPolicy.setBackOffMultiplier(2);
	        //设置重发最大拖延时间-1 表示没有拖延只有UseExponentialBackOff(true)为true时生效     最大时间间隔
//	        redeliveryPolicy.setMaximumRedeliveryDelay(-1);
	        
	        
	        //重发次数,默认为6次   这里设置为10次
	        redeliveryPolicy.setMaximumRedeliveries(10);
	        //重发时间间隔,默认为1秒
	        redeliveryPolicy.setInitialRedeliveryDelay(1);
	        
	        return redeliveryPolicy;
	}



	@Bean("studyQueue")
	public Queue studyQueue() {
		return new ActiveMQQueue(queueName);
	}

	@Bean("studyReplyQueue")
	public Queue studyReplyQueue() {
		return new ActiveMQQueue(replyQueueName);
	}

	@Bean("studyTopic")
	public Topic studyTopic() {
		return new ActiveMQTopic(topicName);
	}

	@Bean("studyDurableTopic")
	public Topic durableTopic() {
		return new ActiveMQTopic(durableTopicName);
	}

	
	// 消费者手动确认ack需要额外配置此
	@Bean("ackJmsTemplate")
	public ClientAckJmsTemplate ackJmsTemplate(ConnectionFactory connectionFactory ) {
		ClientAckJmsTemplate jmsTemplate = new ClientAckJmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory);
		//如果是原生的amq创建的session,将session设置为true时候,ack会固定被设置为AUTO_ACKNOWLEDGE
       //所以想要手动确认,那么session的事物必须设置为false,并且ack设置为CLIENT_ACKNOWLEDGE
		jmsTemplate.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
		jmsTemplate.setSessionTransacted(false);
		jmsTemplate.setAutoAcknowledge(false);
		return jmsTemplate;
	}
	
	@Bean("jmsTemplate")
	public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory ) {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setSessionTransacted(true);
		return jmsTemplate;
	}
	
	@Bean("jmsMessagingTemplate")
	public JmsMessagingTemplate jmsMessagingTemplate(ConnectionFactory connectionFactory ) {
		JmsMessagingTemplate template = new JmsMessagingTemplate(connectionFactory);
		template.setJmsTemplate(jmsTemplate(connectionFactory));
		return template;
	}
	

	// 监听topic需要额外配置此工厂
	@Bean("myJmsListenerContainerFactory")
	public JmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory) {
		DefaultJmsListenerContainerFactory jmsFactory = new DefaultJmsListenerContainerFactory();
		//默认的连接工厂
		jmsFactory.setConnectionFactory(connectionFactory);
		jmsFactory.setPubSubDomain(true);
		return jmsFactory;
	}

	// 监听持久化topic需要额外配置此工厂
	@Bean("myDurableJmsListenerContainerFactory")
	public JmsListenerContainerFactory jmsDuarbleTopicListenerContainerFactory() {
		DefaultJmsListenerContainerFactory jmsFactory = new DefaultJmsListenerContainerFactory();
		//额外的连接工厂
		jmsFactory.setConnectionFactory(getConnectionFactoryForDuarbleTopic());
		jmsFactory.setPubSubDomain(true);
		jmsFactory.setSubscriptionDurable(true);
		jmsFactory.setClientId("durableClient001");
		return jmsFactory;
	}
	
	@Value("${spring.activemq.user}")
    private String usrName;

    @Value("${spring.activemq.password}")
    private String password;

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;
	
	//为持久化topic 专门搞一个连接工厂;
    //没有注册为bean
    //仅仅为  监听持久化topic 一个地方使用
	private ConnectionFactory getConnectionFactoryForDuarbleTopic() {
		
	        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
	        connectionFactory.setBrokerURL(brokerUrl);
	        connectionFactory.setUserName(usrName);
	        connectionFactory.setPassword(password);
	        return connectionFactory;
	    
	}
	

}
