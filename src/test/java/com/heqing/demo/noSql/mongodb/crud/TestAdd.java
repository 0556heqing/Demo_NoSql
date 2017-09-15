package com.heqing.demo.noSql.mongodb.crud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.heqing.demo.noSql.mongoDB.MongoDBUtil;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
import org.junit.Test;

public class TestAdd {
	
	String dbName = "Demo_NoSql";
	String collName = "hqTest";
	
	@Test
	public void addCollection() {
		MongoDBUtil.getCollection().createCollection(dbName, collName);
	}

	@Test
	public void addDocument() {
		Document document = new Document("name", "heqing").  
		         append("age", 27).  
		         append("e_mail", "975656343@qq.com").  
		         append("address", "安徽/安庆");
		MongoDBUtil.getDocument().addDocument(dbName, collName, document);
		
		List<Document> documents = new ArrayList<>();
		Document document1 = new Document("name", "heqing1").  
		         append("age", 26).  
		         append("e_mail", "975656343@qq.com").  
		         append("address", "安徽/安庆");
		Document document2 = new Document("name", "heqing2").  
		         append("age", 27).  
		         append("e_mail", "975656343@qq.com").  
		         append("address", "安徽/安庆");
		documents.add(document1);documents.add(document2);
		MongoCollection<Document> colls = MongoDBUtil.getDocument().findCollection(dbName, collName);
		MongoDBUtil.getDocument().addDocument(colls, documents);
	}

	@Test
	public void addBigDocument() {
		List<Document> documents = new ArrayList<>();

		char cha[]={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		for(int i=0; i<10000; i++) {
			String name = "";
			for(int j=0;j<5;j++) {
				int index;
				index=(int)(Math.random()*(cha.length));
				name=name+(char)cha[index];
			}
			Document document = new Document("name", name).
					append("age", Math.random()*100);
			documents.add(document);
		}
		MongoCollection<Document> colls = MongoDBUtil.getDocument().findCollection(dbName, collName);
		MongoDBUtil.getDocument().addDocument(colls, documents);
	}
}
