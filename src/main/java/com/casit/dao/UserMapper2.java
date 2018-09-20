package com.casit.dao;

import com.casit.entity.PO.Corp;
import com.casit.entity.PO.User;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper2 {
	User login(@Param("username") String username, @Param("password") String password);

	@Select("SELECT * FROM t_user WHERE id = #{id}")
	User getSingleUserByid2(Integer id);

	User getSingleUserByid(@Param("id") Integer id,@Param("age")Integer age);
	
	//嵌套结果 many to 1
	User getSingleUserAndCorpByid(@Param("id") Integer id);
	
	//单表查询懒加载 many to 1
	User getSingleUserAndCorpByid2(@Param("id") Integer id);
	
	//单表查询懒加载 1 to many
	User getSingleUserAndShipAddByid2(@Param("id") Integer id);
	
	//嵌套结果 1 to many
	User getSingleUserAndShipAddByid(@Param("id") Integer id);
	
	//嵌套结果 many to many
	User getUserWithRolesByid(@Param("id") Integer id);
	
	//单表查询lazy many to many
	User getUserWithRolesByid2(@Param("id") Integer id);

	User getUserByUsername(@Param("username") String username);

	int insert(User record);
	
	int insert2(User record);

	void deleteUserByID(@Param("id") Integer id);

	List<User> getUsersWithPageHelper();

	List<User> getUsersWithPageNesting(@Param("offset") int offset, @Param("limit") int limit);

	Integer getUsersCountWithNesting();

	int updateUser(User user);
	
	List<User> getUsersByCorps(List<Corp> list);

	


}