package com.casit.service.impl;

import java.util.Arrays;
import java.util.List;

import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.casit.entity.mongo.Movie;
import com.casit.entity.mongo.Player;
import com.casit.service.PlayerMongoService;
import com.mongodb.client.model.Filters;

/**
* 类说明:
* @author zhouhai
* @version 创建时间：2019年2月22日 上午10:11:27
*/
@Service
public class PlayerMongoServiceImpl implements PlayerMongoService {
	
	@Autowired
    private MongoTemplate mongoTemplate;

	@Override
	public Object processPlayer() {
		
		Player p = new Player();
		p.setCount(9090);
		p.setName("dahubi");
		p.setFavouriteCities(Arrays.asList("北京","东京","大理"));
		p.setFavouriteMovies(Arrays.asList(new Movie(1,"速度与激情"),new Movie(2,"妇联"),new Movie(3,"超人"),new Movie(4,"钢铁侠")));
		
		mongoTemplate.insert(p);
		
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is("dahubi"));
		
		List<Player> find = mongoTemplate.find(query,Player.class);
		return find;
		
	}

}
