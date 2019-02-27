package com.casit;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.casit.service.PlayerMongoService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class test {
	
	@Autowired
	PlayerMongoService mongo;

	
	@Test
	public void ddd() {
		System.out.println("----------------------------------------");
		System.out.println(mongo.processPlayer());
		System.out.println("----------------------------------------");
	}

}
