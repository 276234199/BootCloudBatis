package com.casit;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.casit.RabbitMQ.RabbitSender;
import com.casit.dao.mongo.PlayeMongoDao;
import com.casit.entity.PO.User;
import com.casit.entity.mongo.Player;
import com.casit.service.UserService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
@EnableSwagger2
@SpringBootApplication
@EnableTransactionManagement // 事务
@EnableCaching // redis
@EnableAsync // 异步请求
@MapperScan("com.casit.dao")  //mybatis mapper
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootCloudBatisApplication.class)
public class BootCloudBatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootCloudBatisApplication.class, args);
	}
	
	@Autowired
	public UserService userService;
	

	
	@Autowired
    private AmqpTemplate rabbitTemplate;
	
	@Autowired
	RabbitSender rabbitSender;	
	
	@Autowired
	PlayeMongoDao playeMongoDao;
	

	
	@Test
	public void testSth() throws Exception {
//		User u = userService.createUser("rua", "ruarurua");
//		System.out.println(u.getId()+"----------------------------");
//		rabbitTemplate.convertAndSend("queque", "???????");
		System.out.println("-------------????????????????????---------------");
		Player p = new Player();
		p.setCount(333338);
		p.setName("sdfjhsdkhfsdf");
		playeMongoDao.save(p);
		System.out.println(p.getPlayerId());
		Player p2 = new Player();
		p2.setCount(9090950);
		p2.setName("liuqiangdogn");
//		playeMongoDao2.save(p2);
	}

	

}
