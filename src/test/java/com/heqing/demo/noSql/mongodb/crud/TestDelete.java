package com.heqing.demo.noSql.mongodb.crud;

import com.heqing.demo.noSql.mongoDB.MongoDBUtil;
import org.bson.conversions.Bson;
import org.junit.Test;

import com.mongodb.client.model.Filters;

public class TestDelete {

	String dbName = "Demo_NoSql";
	String collName = "hqTest";
	
	@Test
	public void closeDB() {
		MongoDBUtil.getDocument().closeClient();
	}
	
	@Test
	public void deleteDB() {
		MongoDBUtil.getDocument().dropMongoDB(dbName);
	}
	
	@Test
	public void deleteCollection() {
		MongoDBUtil.getDocument().dropCollection(dbName, collName);
	}
	
	@Test
	public void deleteDocument() {
		String id = "59bb8def9cdbdc3b80f38aaf";
		MongoDBUtil.getDocument().deleteDocumentById(dbName, collName, id);
		
		Bson filter = Filters.eq("name", "heqing");
		MongoDBUtil.getDocument().deleteDocument(id, id, filter);
	}
}
