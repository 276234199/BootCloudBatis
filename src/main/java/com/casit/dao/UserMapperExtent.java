package com.casit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;

import com.casit.entity.PO.User;

public interface UserMapperExtent {
	
	
	
	User login(@Param("username")String username,@Param("password")String password);
	
	@Select("SELECT * FROM t_user WHERE id = #{id}")
	User getSingleUserByid2(Integer id);
	
	User getSingleUserByid(@Param("id")Integer id);
	
	User getUserByUsername(@Param("username")String username);
	
	int insert(User record);

	void deleteUserByID(@Param("id")Integer id);
	
	
	
	List<User> getUsersWithPageHelper();
	
	List<User> getUsersWithPageNesting(@Param("offset")int offset,@Param("limit")int limit);
	
	Integer getUsersCountWithNesting();

	int updateByid(User user);

	
}
