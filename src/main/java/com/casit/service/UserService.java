package com.casit.service;

import java.util.List;
import java.util.concurrent.Future;

import com.casit.entity.PO.User;
import com.github.pagehelper.PageInfo;

public interface UserService {
	
	String login(String username,String password) ;

	User createUser(String username, String password) throws  Exception ;

	void delUser(Integer id);

	User getUser(Integer id);

	User updateUser(User user);


	PageInfo<User> getUsersWithPageHelper(int page, int pagesize);

	Future<String> testAsync() throws InterruptedException;

	void testKafka(String test);

}
