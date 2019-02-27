package com.casit.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.casit.entity.mongo.Player;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
* 类说明: POJO操作mongoDB   spring就是用的这种方式  但是 灵活性降低了
* @author zhouhai
* @version 创建时间：2019年2月14日 下午4:47:57
*/
public class BasicPojoMongo {
	
	public static void main(String[] args) {
		List<CodecRegistry> codecRegistries = new ArrayList<>();
		//添加默认的编解码器
		codecRegistries.add(MongoClient.getDefaultCodecRegistry());
		//pojo编解码器
		CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
		codecRegistries.add(pojoCodecRegistry);
		
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(codecRegistries);
		
		MongoClientOptions option =  MongoClientOptions.builder().codecRegistry(codecRegistry).build();
		
		ServerAddress addr = new ServerAddress("localhost", 27017);
		
		MongoClient client = new MongoClient(addr,option);
		MongoDatabase database = client.getDatabase("test");
		//pojo方式
		MongoCollection<Player> collection = database.getCollection("cool",Player.class);
	}

}
