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
	

	@Bean
	public Queue helloQueue() {
		return new Queue("hello");
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
	TopicExchange exchange() {
		return new TopicExchange("exchange");
	}

	@Bean
	FanoutExchange fanoutExchange() {
		return new FanoutExchange("fanoutExchange");
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
		return BindingBuilder.bind(messageQueue).to(exchange).with("topic.message");
	}

	/**
	 * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
	 * 
	 * @param queueMessage
	 * @param exchange
	 * @return
	 */
	@Bean
	Binding bindingExchangeMessages(Queue errorQueue, TopicExchange exchange) {
		return BindingBuilder.bind(errorQueue).to(exchange).with("topic.#");
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
	
	// ===============以下是验证direct Exchange的队列==========
	@Bean
	public Queue userQueue() {
		return new Queue("userQueue");
	}
	
	@Bean
	public DirectExchange  directExchange() {
		return new  DirectExchange("directExchange");
	}
	
	@Bean
	Binding bindingDirectExchange(Queue userQueue, DirectExchange directExchange) {
		return BindingBuilder.bind(userQueue).to(directExchange).with("userQueueBindKey");
	}

}
