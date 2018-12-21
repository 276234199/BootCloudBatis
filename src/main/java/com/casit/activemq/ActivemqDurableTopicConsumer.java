package com.casit.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
* 类说明:持久化订阅topic 消费者down了之后再上来，还能收到topic广播
* 需要在配置文件里单独配置连接工厂
* @author zhouhai
* @version 创建时间：2018年12月18日 下午1:40:34
*/
@Component
public class ActivemqDurableTopicConsumer {
	
	@JmsListener(destination="${activemq.study.durableTopic}",
			containerFactory="myDurableJmsListenerContainerFactory")
	public void receiveFromTopic(String msg) {
		System.out.println("consumer durable topic :" +msg);
	}


}
