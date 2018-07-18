package com.casit.service;

import com.casit.entity.PO.User;

public interface UserService {
	
	String login(String username,String password) ;

	User createUser(String username, String password) ;

	void delUser(Integer id);

	User getUser(Integer id);

	User updateUser(User user);

}
