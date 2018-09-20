package com.casit.mybatisStudy;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.casit.dao.UserMapper2;
import com.casit.entity.PO.Corp;
import com.casit.entity.PO.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 类说明:mybatis 纯使用xml方式  不在注解中写sql
 * 
 * @author zhouhai
 * @version 创建时间：2018年9月3日 下午1:45:06
 */
public class QuickStart {

	private SqlSessionFactory sqlSessionFactory;

	@Test
	public void run() throws IOException {
		String resource = "mybatis-test.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		is.close();
		
		//默认不会自动提交
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		
//		自动提交事务
//		SqlSession sqlSession = sqlSessionFactory.openSession(true);

//      批量提交支持  类似于事务  commit才会进行批量数据库操作
//		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,true);

		UserMapper2 mapper = sqlSession.getMapper(UserMapper2.class);
		System.out.println("---------------------------get mapper end , begin test-------------------------------------------------");

		User u10 = mapper.getSingleUserAndCorpByid(1);
		System.out.println(u10.toJsonByGson());
		System.out.println("u10:"+u10.getClass());
		System.out.println("------------------------------many to  1 嵌套结果 association end----------------------------------------------");
		 
		User u11 = mapper.getSingleUserAndCorpByid2(1);
		System.out.println(u11.getUsername());
		
		System.out.println("执行下面一句时 才会进行第二次查询数据库 懒加载 按需加载");
		System.out.println(u11.getCorp().getCorpName());
		//lazy + 转json 会报错
		System.out.println("u11:"+u11.getClass());
		System.out.println(u11.toJsonByJackson());
		System.out.println("------------------------------many to  1 单表查询 懒加载 association end----------------------------------------------");
		
		User u13 = mapper.getSingleUserAndShipAddByid(1);
		System.out.println(u13.getAdds());
		System.out.println(u13.toJsonByGson());
		System.out.println(u13.toJsonByJackson());
		System.out.println("------------------------------1 to many 嵌套结果 collection end----------------------------------------------");
		
		User u12 = mapper.getSingleUserAndShipAddByid2(1);
		System.out.println(u12.getUsername());
		System.out.println(u12.getAdds());
		//lazy + 转json 会报错 除非objectmapper设置一波  ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
		System.out.println(u12.toJsonByJackson());
		System.out.println("------------------------------1 to many 懒加载 collection end----------------------------------------------");
		
		User u14 = mapper.getUserWithRolesByid(1);
		System.out.println(u14.getUsername());
		System.out.println(u14.toJsonByGson());
		System.out.println(u14.toJsonByJackson());
		System.out.println("------------------------------many to many  嵌套结果 collection end----------------------------------------------");
		
		User u15 = mapper.getUserWithRolesByid2(1);
		System.out.println(u15.getUsername());
		System.out.println(u15.getRoles());
		System.out.println(u15.toJsonByJackson());
		System.out.println("------------------------------many to many  懒加载 collection end----------------------------------------------");
		
		
//		User u = mapper.getSingleUserByid(1,0);
//		System.out.println(u.toJsonByGson());

		System.out.println("---------------------------simple get end--------------------------------------------------");
		
//		User u2 = new User();
//		u2.setPassword("aaaaaaaaaaaaaaaa");
//		u2.setUserage(82);
//		u2.setUsername("leoric");
//		
//		Corp corp2 = new Corp();
//		corp2.setCorpId(2);
//		u2.setCorp(corp2);
		
//		int count2  = mapper.insert(u2);
//		System.out.println("u2 id:"+u2.getId());
//		System.out.println("插入条数："+count2);
		System.out.println("---------------------------insert1 end--------------------------------------------------");
		
//		User u3 = new User();
//		u3.setPassword("aaaaaaaaaaaa");
//		u3.setUserage(82);
//		u3.setUsername("leoric");
//		
//		Corp corp3 = new Corp();
//		corp3.setCorpId(3);
//		u3.setCorp(corp3);
		
//		int count3  = mapper.insert2(u3);
//		System.out.println("u3id:"+u3.getId());
//		System.out.println("插入条数："+count3);
		System.out.println("--------------------------insert2 end---------------------------------------------------");
		
//		User u4 = new User();
//		u4.setId(1);
//		u4.setUserage(7654);
//		
//		Corp corp4 = new Corp();
//		corp4.setCorpId(4);
//		
//		u4.setCorp(corp4);
//		int count4  = mapper.updateUser(u4);
//		System.out.println("update条数："+count4);
		System.out.println("--------------------------update end---------------------------------------------------");
		
//		List<Corp> corps = new ArrayList<>();
//		corps.add(corp2);
//		corps.add(corp3);
//		List<User> users = mapper.getUsersByCorps(corps);
//		System.out.println(users.size());
		System.out.println("--------------------------foreach xml end---------------------------------------------------");
		//增删改操作时，要执行commit操作。。。test没有声明事务 敌军也要要求事务。。。。。 手动提交tmd。。。增删改操作时，要执行commit操作
		sqlSession.commit();
		sqlSession.close();
		
		
	}

}
