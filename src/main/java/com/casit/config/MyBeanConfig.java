package com.casit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.casit.entity.PO.User;

/**
* 类说明:
* @author zhouhai
* @version 创建时间：2018年8月11日 上午1:30:02
*/
@Configuration
public class MyBeanConfig {
	

	public MyBeanConfig() {

		System.out.println("MyConfigLoaded");
	}

	//不指定就是方法名，指定就是Bean中的
//	@Bean("aaaUser")
	@Bean
	public User testUser() {
		User u = new User();
		u.setId(777777);
		u.setUsername("aaaa");
		u.setPassword("qqqqqqq");
		u.setUserage(35);

		System.out.println("testUserInitialized");
		return u;
	}
}
