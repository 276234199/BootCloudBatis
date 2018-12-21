package com.casit.RabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AAARabbitConfig {
	

	//直接发送到这个队列可以 不用exchange
	@Bean
	public Queue helloQueue() {
		return new Queue("hello");
	}
	
	
	// ===============以下是验证direct Exchange的队列==========
	@Bean
	public Queue userQueue() {
		Queue queue = new Queue("userQueue");
		return queue;
	}
	
	@Bean
	public DirectExchange  directExchange() {
		DirectExchange de = new  DirectExchange("directExchange");
		return de;
	}

	@Bean
	//参数userQueue 与 方法 userQueue() 名称相同 因此可以注入到 userQueue
	Binding bindingDirectExchange(Queue userQueue, DirectExchange directExchange) {
		return BindingBuilder.bind(userQueue).to(directExchange).with("xiaohubi");
	}
	
	@Bean
	//参数userQueue 与 方法 userQueue() 名称相同 因此可以注入到 userQueue
	Binding bindingDirectExchange2(Queue userQueue, DirectExchange directExchange) {
		return BindingBuilder.bind(userQueue).to(directExchange).with("dahubi");
	}


	// ===============以下是验证topic Exchange的队列==========
	@Bean
	public Queue messageQueue() {
		return new Queue("messageQueue");
	}

	@Bean
	public Queue errorQueue() {
		return new Queue("errorQueue");
	}
	// ===============以上是验证topic Exchange的队列==========

	// ===============以下是验证Fanout Exchange的队列==========
	@Bean
	public Queue AMessage() {
		return new Queue("fanoutAQueue");
	}

	@Bean
	public Queue BMessage() {
		return new Queue("fanoutBQueue");
	}

	@Bean
	public Queue CMessage() {
		return new Queue("fanoutCQueue");
	}
	// ===============以上是验证Fanout Exchange的队列==========

	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange("topicExchange");
	}
	
	/**
	 * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
	 * 
	 * @param queueMessage
	 * @param exchange
	 * @return
	 */
	@Bean
	Binding bindingExchangeMessage(Queue messageQueue, TopicExchange exchange) {
		return BindingBuilder.bind(messageQueue).to(exchange).with("topic.#");
	}

	/**
	 * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
	 * 
	 * @param queueMessage
	 * @param exchange
	 * @return
	 */
	@Bean
	Binding bindingExchangeMessages(Queue errorQueue, TopicExchange topicExchange) {
		return BindingBuilder.bind(errorQueue).to(topicExchange).with("topic.error");
	}
	
	//======================================================================================

	@Bean
	FanoutExchange fanoutExchange() {
		return new FanoutExchange("fanoutExchange");
	}
	
	@Bean
	Binding bindingExchangeA(Queue AMessage, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(AMessage).to(fanoutExchange);
	}

	@Bean
	Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(BMessage).to(fanoutExchange);
	}

	@Bean
	Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(CMessage).to(fanoutExchange);
	}


}
