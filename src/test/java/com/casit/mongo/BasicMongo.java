package com.casit.mongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

/**
 * 类说明:mongoclient
 * 
 * @author zhouhai
 * @version 创建时间：2019年2月14日 上午9:22:08
 */
public class BasicMongo {
	public static void main(String args[]) {

		try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {

			// 连接到 mongodb 服务
			// 连接到数据库
			MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
			System.out.println("Connect to database successfully");

			// mongoDatabase.createCollection("cool");

			MongoCollection<Document> collection = mongoDatabase.getCollection("cool");

			System.out.println("集合 cool 选择成功");

			//新增
			Document document = new Document("name", "xiaoliubi").append("count", new Random().nextInt(999));

			List<Document> documents = new ArrayList<Document>();
			documents.add(document);

			collection.insertMany(documents);
			
			System.out.println("key:"+document.getObjectId("_id"));
			System.out.println(document);
			// collection.insertOne(document);
			System.out.println("文档插入成功");

			//查询
			FindIterable<Document> findRes = collection.find(Filters.gte("count", 0));

			MongoCursor<Document> mongoCursor = findRes.iterator();

			while (mongoCursor.hasNext()) {
				System.out.println(mongoCursor.next());
			}

			//修改
			collection.updateMany(Filters.gte("count", 50),
					new Document("$set", new Document("count", new Random().nextInt(999))));
			
			System.out.println("文档update成功");

			FindIterable<Document> findRes2 = collection.find(Filters.gte("count", 0));

			MongoCursor<Document> mongoCursor2 = findRes2.iterator();

			while (mongoCursor2.hasNext()) {
				System.out.println(mongoCursor2.next());
			}
			//删除
			collection.deleteOne(Filters.eq("count", 200));  
			System.out.println("文档删除成功");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}
