package com.casit.RabbitMQ;

import java.util.HashMap;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
//队列持久化后，路由绑定也是持久化的。所以修改的时候 注意在控制台清空之前的路由绑定
public class AAARabbitConfig {
	//队列持久化后，路由绑定也是持久化的。所以修改的时候 注意在控制台清空之前的路由绑定
	//队列持久化后，路由绑定也是持久化的。所以修改的时候 注意在控制台清空之前的路由绑定
	//队列持久化后，路由绑定也是持久化的。所以修改的时候 注意在控制台清空之前的路由绑定
	//队列持久化后，路由绑定也是持久化的。所以修改的时候 注意在控制台清空之前的路由绑定
	//队列持久化后，路由绑定也是持久化的。所以修改的时候 注意在控制台清空之前的路由绑定
	
//	@Bean
//   不启用自定义的rabbitTemplate
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rb = new RabbitTemplate(connectionFactory);
		//开启消息返回
		rb.setMandatory(true);
		rb.setReturnCallback(returnCallback());
		rb.setConfirmCallback(confirmCallBack());
		return rb;
	}
	
//	@Bean
//  不启用
	public RabbitTemplate.ConfirmCallback confirmCallBack(){
		return new RabbitTemplate.ConfirmCallback() {
			
			@Override
			public void confirm(CorrelationData correlationData, boolean ack, String cause) {
				if(ack) {
					System.out.println("发送给mq成功");
				}else {
					System.out.println("发送给mq失败");
				}
			}
		};
		
	}
	
//	@Bean
//  不启用
	public RabbitTemplate.ReturnCallback returnCallback(){
		return new RabbitTemplate.ReturnCallback() {

			@Override
			public void returnedMessage(Message message, int replyCode, String replyText, String exchange,
					String routingKey) {
				System.out.println("消息被mq退回");
			}
			
		
		};
		
	}

	//直接发送到这个队列可以 不用exchange
	@Bean
	public Queue helloQueue() {
		return new Queue("hello");
	}
	
//	===============以上是自定义rabbitTemplate==========
			
			
	// ===============以下是验证direct Exchange的队列==========
	@Bean
	public Queue userQueue() {
//		Queue queue = new Queue("userQueue");
		Queue queue = new Queue("userQueue",true,false,false,new HashMap<String,Object>());
		return queue;
	}
	
	@Bean
	public DirectExchange  directExchange() {
//		DirectExchange de = new  DirectExchange("directExchange");
		DirectExchange de = new  DirectExchange("directExchange",true,false,new HashMap<String,Object>());
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
	public Queue messageQueue2() {
		return new Queue("messageQueue2");
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
	
	// # 和  * 的区别
	// # 可以表示多级 topic.#  
	// * 表示一级        topic.*   
	/**
	 * 将队列topic.message与exchange绑定，binding_key为topic.#,就是模糊匹配
	 */
	@Bean
	Binding bindingExchangeMessage(Queue messageQueue, TopicExchange topicExchange) {
		return BindingBuilder.bind(messageQueue()).to(topicExchange()).with("topic.#");
	}
	
	@Bean
	Binding bindingExchangeMessage2(Queue messageQueue, TopicExchange topicExchange) {
		return BindingBuilder.bind(messageQueue2()).to(topicExchange()).with("topic.*");
	}

	/**
	 * 将队列topic.messages与exchange绑定，binding_key为topic.error,完全匹配
	 */
	@Bean
	Binding bindingExchangeError(Queue errorQueue, TopicExchange topicExchange) {
		return BindingBuilder.bind(errorQueue()).to(topicExchange()).with("topic.error");
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
