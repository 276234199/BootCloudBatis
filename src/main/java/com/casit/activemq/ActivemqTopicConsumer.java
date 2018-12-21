package com.casit.activemq;
/**
* 类说明:
* @author zhouhai
* @version 创建时间：2018年12月17日 下午4:10:00
*/

import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActivemqTopicConsumer {

	
	@JmsListener(destination="${activemq.study.topic}",containerFactory="myJmsListenerContainerFactory")
	public void receiveFromTopic(String msg) {
		System.out.println("consumer1 topic :" +msg);
	}


}
