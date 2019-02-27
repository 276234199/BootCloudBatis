package com.casit.mongo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

/**
 * 类说明:mongoclient   --基本用法   非pojo用法
 * 
 * @author zhouhai
 * @version 创建时间：2019年2月14日 上午9:22:08
 */
public class BasicDocumentMongo {
	public static void main(String args[]) {

		// 可以复用 项目中 不需要关闭 spring中注册成个bean完事
		try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {

			// 连接到 mongodb 服务
			// 连接到数据库
			MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
			System.out.println("Connect to database successfully");

			// mongoDatabase.createCollection("cool");

			MongoCollection<Document> collection = mongoDatabase.getCollection("cool");

			System.out.println("集合 cool 选择成功");

			// 新增
			Document document = new Document().append("name", "xiaoliubi").append("count", new Random().nextInt(999));

			HashMap<String, String> favourites = new HashMap<>();
			favourites.put("game", "炉石传说");
			favourites.put("city", "北京");

			document.append("favourites", favourites);

			document.append("intrests", Arrays.asList("游戏", "打球", "忽悠", "装逼"));

			List<Document> documents = new ArrayList<Document>();
			documents.add(document);

			System.out.println("key:" + document.getObjectId("_id"));
			System.out.println(document);

			collection.insertMany(documents);
			
			System.out.println("文档插入成功");

			System.out.println("key:" + document.getObjectId("_id"));
			System.out.println(document);
			// collection.insertOne(document);
			

			// 查询
			Bson regex = Filters.regex("name", ".*xiao.*");

			Bson gte = Filters.gte("count", 0);
			
			FindIterable<Document> findRes = collection.find(Filters.and(regex, gte));

			MongoCursor<Document> mongoCursor = findRes.iterator();

			while (mongoCursor.hasNext()) {
				System.out.println(mongoCursor.next());
			}
			System.out.println("文档查询成功");

			// 修改
//			collection.updateMany(Filters.gte("count", 50), new Document("$set", new Document("count", new Random().nextInt(999))));
			//等同于上面的写法
			UpdateResult updateManyResu = collection.updateMany(Filters.gte("count", 50), Updates.set("count", new Random().nextInt(999)));

			System.out.println("文档更新成功"+updateManyResu);

			FindIterable<Document> findRes2 = collection.find(Filters.and(regex, gte));

			MongoCursor<Document> mongoCursor2 = findRes2.iterator();

			while (mongoCursor2.hasNext()) {
				System.out.println(mongoCursor2.next());
			}
			// 删除
			DeleteResult deleteResult = collection.deleteOne(Filters.eq("count", 200));
			System.out.println("文档删除成功" + deleteResult);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}
