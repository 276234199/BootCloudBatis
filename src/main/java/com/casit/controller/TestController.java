package com.casit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casit.activemq.ActivemqProducer;
import com.casit.entity.PO.User;
import com.casit.service.RabbitmqService;
import com.casit.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/hello")
@Api(value = "测试", tags = "测试")
public class TestController {

	private static Logger logger = LogManager.getLogger(TestController.class.getName());

	@Autowired
	public UserService userService;

	@Autowired
	public RabbitmqService rabbitmqService;
	
	@ExceptionHandler
	public String exception(Exception e) throws IOException {
		logger.error(e.getClass().getName() + ": " + e.getLocalizedMessage(),e);
		return e.getLocalizedMessage();
	}
	

	// http://localhost:8080/hello/hello.do
	@RequestMapping(value = "/hello.do")
	@ApiOperation(httpMethod = "GET", value = "hello")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", dataType = "String", name = "username", value = "用户名", required = true),
			@ApiImplicitParam(paramType = "query", dataType = "String", name = "password", value = "密码", required = true) })
	public String hello(String username, String password) throws Exception {
		String result = userService.login("QAREW", "123");
		userService.createUser("daboluo", "123");
		return "LOGIN:" + result;
	}

	// http://localhost:8080/hello/create.do
	@RequestMapping(value = "/create.do")
	@ApiOperation(httpMethod = "GET", value = "create")
	public String createUser() throws Exception {
		User u = userService.createUser("rua", "ruarurua");
		return u.getId().toString();
	}

	// http://localhost:8080/hello/delete.do
	@RequestMapping(value = "/delete.do")
	@ApiOperation(httpMethod = "GET", value = "delete")
	public String deleteUser(Integer id) throws Exception {
		userService.delUser(id);
		return "success";
	}

	// http://localhost:8080/hello/get.do
	@RequestMapping(value = "/get.do")
	@ApiOperation(httpMethod = "GET", value = "getUser")
	public String getUser(Integer id) throws Exception {
		return userService.getUser(id).toJsonByGson();
	}

	// http://localhost:8080/hello/update.do
	@RequestMapping(value = "/update.do")
	@ApiIgnore
	public String updateUser(User user) throws Exception {
		return userService.updateUser(user).toJsonByGson();
	}

	// http://localhost:8080/hello/sendToQueue.do?content=qqqqqq
	@RequestMapping(value = "/sendToQueue.do")
	@ApiOperation(httpMethod = "GET", value = "snedToQueue")
	public String sendToQueue(String content) throws Exception {
		rabbitmqService.snedToQueue(content);
		return "";
	}
	
	@Autowired
	ActivemqProducer producer;
	
	@RequestMapping(value = "/testActivemq")
	public String testActivemq(String msg) throws Exception {
		producer.sendMsg(msg);
		return "done";
	}

	
	// http://localhost:8080/hello/ruaruarua.do
	@RequestMapping(value = "/ruaruarua.do")
	@ApiOperation(httpMethod = "GET", value = "snedToQueue")
	public String test(String content) throws Exception {
		for (int i = 0; i < 50000; i++) {
			logger.error("error level");
		}
		if (content == null) {
			Exception exception = new RuntimeException("mmmmpp");
			logger.error(exception.getMessage() + "--" + exception.toString());
			throw exception;
		}

		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}
	
	@RequestMapping(value = "/getUsers.do")
	@ApiOperation(httpMethod = "GET", value = "getUsers")
	public String getUsers(int page ,int pagesize) throws Exception {
		
		PageInfo<User> list = userService.getUsersWithPageHelper(page, pagesize);

		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}
	//使用异步调用副线程 可以提高tomcat并发量
	@RequestMapping(value = "/testAsync.do")
	public String testAsync(HttpServletResponse resp,HttpServletRequest req) throws Exception {
		
		System.out.println(req.getSession().getId());
		

		
		System.out.println(Thread.currentThread() + "———main主线程—————begin");
		System.out.println(Thread.currentThread() + "———main主线程—————end");

		return userService.testAsync().get();
		
	}
	
	
}
