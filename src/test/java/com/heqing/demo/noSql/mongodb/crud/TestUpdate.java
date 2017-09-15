package com.heqing.demo.noSql.mongodb.crud;

import com.heqing.demo.noSql.mongoDB.MongoDBUtil;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;

import com.mongodb.client.model.Filters;

public class TestUpdate {
	
	String dbName = "Demo_NoSql";
	String collName = "hqTest";
	
	@Test
	public void updateDocById() {
		String id = "59bb8def9cdbdc3b80f38aaf";
		Document document = new Document("name", "heqing1_new").  
		         append("age", 27).  
		         append("e_mail", "975656343@qq.com").  
		         append("address", "安徽/安庆");
		MongoDBUtil.getDocument().updateDocumentById(dbName, collName, id, document);
	}
	
	@Test
	public void updateDoc() {
		Bson filter = Filters.eq("name", "heqing2");
		Document document = new Document("name", "heqing2_new").  
		         append("age", 27).  
		         append("e_mail", "975656343@qq.com").  
		         append("address", "安徽/安庆");
		MongoDBUtil.getDocument().updateDocument(dbName, collName, filter, document);
	}
}
