package com.casit.service.impl;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.casit.dao.ShipAddressMapper;
import com.casit.dao.ShipAddressMapperExtent;
import com.casit.dao.UserMapper;
import com.casit.dao.UserMapperExtent;
import com.casit.entity.PO.ShipAddress;
import com.casit.entity.PO.User;
import com.casit.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("userService")
@com.alibaba.dubbo.config.annotation.Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class UserServiceImpl implements UserService {
	
	@Autowired
	public UserMapperExtent userMapperExtent;
	
	@Autowired
	public UserMapper userMapper;
	
	@Autowired
	public ShipAddressMapper shipAddressMapper;
	
	@Autowired
	public ShipAddressMapperExtent shipAddressMapperExtent;
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
    private RedisTemplate redisTemplate;
	

	@SuppressWarnings("unchecked")
	@Override
	
	@Caching(
		    cacheable = {
		       @Cacheable(value = "login2", key = "'user_'+#username+#password"),
		       @Cacheable(value="login",key="'user_'+#username+#password")
		    }
		)
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=RuntimeException.class)
	public String login(String username, String password){
//		return userMapperExtent.getByid("1").getId();
//		return userMapperExtent.selectByPrimaryKey2("1").getId();
		User u = userMapperExtent.login(username, password);

		User u2 = userMapperExtent.getUserByUsername(username);
		
//		PageHelper.startPage(1, 10,true);
//		PageHelper.orderBy("username");
//		List<User> users1 = userMapperExtent.getUsersWithPageHelper();
//		List<User> users2 = userMapperExtent.getUsersWithPageNesting(888888, 100);
//		Integer userCount = userMapperExtent.getUsersCountWithNesting();

	

//		userMapperExtent.insert( new User());
		
		ShipAddress add = shipAddressMapperExtent.getShipAddressWihtUserByID(1);
		
		
		
		stringRedisTemplate.opsForValue().set("name","大虎逼",60,TimeUnit.SECONDS);
		
		redisTemplate.opsForSet().add("cool", add);
		redisTemplate.opsForSet().add("cool", u);
		redisTemplate.opsForSet().add("cool", u2);
		
		String name = stringRedisTemplate.opsForValue().get("name");
		
		String result =  u.getPassword() +"|||||"+u2.getPassword()+"||"+u2.getAdds().size()+"||"+u2.getRoles().size()+"|加哈|"+add.getAddress()+name;
		

		return result;
	}
	
	@Override
	public PageInfo<User> getUsersWithPageHelper(int page ,int pagesize){
		PageHelper.startPage(page,pagesize);
		List<User> users1 = userMapperExtent.getUsersWithPageHelper();
		PageInfo<User> pageinfo = new PageInfo<User>(users1);
		return pageinfo;
	}

	
	@Override
	@Caching(
		    put = {
		       @CachePut(value = "users", key = "#result.id", condition = "#result != null")
		    }
		)
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=RuntimeException.class)
	public User createUser(String username, String password)  {

		User user =  new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setUserage(30);
        userMapperExtent.insert( user);

        //mybatis会自动将id赋到user对象上
		return user;
	}
	
	@Override
	@Caching(
		    evict= {
		    		@CacheEvict(value = "users", key = "#id", condition = "#id != null")
		    }
		)
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=RuntimeException.class)
	public void delUser(Integer id) {
        userMapperExtent.deleteUserByID( id);
	}


	@Override
	@Cacheable(value="users",key="#id")
	public User getUser(Integer id) {
		return userMapperExtent.getSingleUserByid(id);
	}
	
	@Override
	@CachePut(value = "users", key = "#result.id", condition = "#result != null")
	public User updateUser(User user) {
		userMapperExtent.updateByid(user);
		return userMapperExtent.getSingleUserByid(user.getId());
	}
	

}
