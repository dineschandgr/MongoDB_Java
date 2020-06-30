package io.mongo.main;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import io.mongo.model.Order;


public class MongoDB {

	public static void main(String a[]) {
		
		/*
		 * Use this for localhost
		 * MongoClient client = new MongoClient("localhost",27017);
		 * 
		 * MongoCredential mongoCredential =
		 * MongoCredential.createCredential("testUser", "UserDB",
		 * "password".toCharArray());
		 */
		

		MongoClientURI uri = new MongoClientURI(
		    "mongodb+srv://<username>:<password>@mycluster-fyste.mongodb.net/<UserDB>?retryWrites=true&w=majority");
		
		MongoClient mongoClient = new MongoClient(uri);
		
		MongoDatabase db = mongoClient.getDatabase("UserDB");
		
		
		MongoCollection<Document> collection = db.getCollection("testCollection");
		collection.drop();
		db.createCollection("testCollection");
		
		new MongoDB().createSingleDocument(collection);
		new MongoDB().createMultipleDocuments(collection);
		System.out.println("after creating documents ------------------------");
		new MongoDB().retrieveDocuments(collection);
		new MongoDB().updateSingleDocument(collection);
		new MongoDB().updateMultipleDocuments(collection);
		System.out.println("after updating documents ------------------------");
		new MongoDB().retrieveDocuments(collection);
		//new MongoDB().deleteSingleDocument(collection);
		//new MongoDB().deleteMultipleDocuments(collection);
		System.out.println("after deleting documents ------------------------");
		new MongoDB().retrieveDocuments(collection);
		mongoClient.close();
		
		
	}
	
	public void createSingleDocument(MongoCollection<Document> collection) {
		
		Document document = new Document("title","MongoDB")
								.append("description", "test document")
								.append("author", "dc")
								.append("_id", 1);
		collection.insertOne(document);
		System.out.println("document created");
	}
	
	public void createMultipleDocuments(MongoCollection<Document> collection) {
		
		Document document1 = new Document("title","MongoDB")
								.append("description", "test document 1")
								.append("author", "gayathri")
								.append("_id", 2);
		
		//using pojo object. Adding an Object inside the Document
		Order order = new Order(1, 2, 100, "Product 1");	
		ObjectMapper oMapper = new ObjectMapper();
		 
		Map<String, Object> map = oMapper.convertValue(order, Map.class);		
		
		Document document2 = new Document("title","MongoDB")
				.append("description", "test document 2")
				.append("author", "dc")
				.append("_id", 3)
				.append("order", map);
		
		//using MongoDB object. Adding a list inside the document
		
		List<BasicDBObject> orderList = new ArrayList<BasicDBObject>();
		BasicDBObject order1 = new BasicDBObject();
		order1.put("id", 1);
		order1.put("quantity", 2);
		order1.put("price", 100);
		order1.put("description", "Product 1");
		orderList.add(order1);
		
		BasicDBObject order2 = new BasicDBObject();
		order2.put("id", 2);
		order2.put("quantity", 2);
		order2.put("price", 200);
		order2.put("description", "Product 2");
		orderList.add(order2);
		
		
		Document document3 = new Document("title","MongoDB")
				.append("description", "test document 3")
				.append("author", "dc")
				.append("_id", 4)
				.append("orderList", orderList);
		
		List<Document> documentList = new ArrayList<Document>();
		documentList.add(document1);
		documentList.add(document2);
		documentList.add(document3);
		collection.insertMany(documentList);
		
		System.out.println("documents created");
	}
	
	public void retrieveDocuments(MongoCollection<Document> collection) {
		
		FindIterable<Document> iterDoc = collection.find();
		int i = 1;
		Iterator it = iterDoc.iterator();
		while(it.hasNext()) {
			System.out.println("retrieveDocuments "+it.next());
		}
		
	}
	
	public void updateSingleDocument(MongoCollection<Document> collection) {
			
		collection.updateOne(Filters.eq("_id",1), Updates.set("author", "mugund"));
	}
	
	public void updateMultipleDocuments(MongoCollection<Document> collection) {
		
		collection.updateMany(Filters.eq("author","dc"), Updates.set("author", "shrisha"));
		
	}
	
	public void deleteSingleDocument(MongoCollection<Document> collection) {
		
		collection.deleteOne(Filters.eq("author","mugund"));
		
	}
	
	
	public void deleteMultipleDocuments(MongoCollection<Document> collection) {
		
		collection.deleteMany(Filters.eq("author","shrisha"));
		
	}
}



