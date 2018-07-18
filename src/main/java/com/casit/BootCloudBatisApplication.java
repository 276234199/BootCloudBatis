package com.casit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.casit.entity.PO.User;
import com.casit.service.UserService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
@EnableSwagger2
@SpringBootApplication
@EnableTransactionManagement // 事务
@EnableCaching // redis
@MapperScan("com.casit.dao")  //mybatis mapper
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootCloudBatisApplication.class)
public class BootCloudBatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootCloudBatisApplication.class, args);
	}
	
	@Autowired
	public UserService userService;
	
	@Test
	public void testSth() {
		User u = userService.createUser("rua", "ruarurua");
		System.out.println(u.getId()+"----------------------------");
	}

	

}
