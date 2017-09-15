package com.heqing.demo.noSql.mongodb.crud;

import java.util.List;

import com.heqing.demo.noSql.mongoDB.MongoDBConstants;
import com.heqing.demo.noSql.mongoDB.MongoDBUtil;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;

import com.mongodb.client.model.Filters;

public class TestFind {
	
	String dbName = "Demo_NoSql";
	String collName = "hqTest";
	
	@Test
	public void findAllDBNames() {
		List<String> names = MongoDBUtil.getDocument().getDBAllName();
		for(String name : names) {
			System.out.println("---->"+name);
		}
	}
	
	@Test
	public void findAllCollections() {
		List<String> colls = MongoDBUtil.getDocument().getAllCollections("Demo_NoSql");
		for(String coll : colls) {
			System.out.println("---->"+coll);
		}
	}
	
	@Test
	public void findDocumentById() {
		String id = "59bb8def9cdbdc3b80f38aaf";
		Document doc = MongoDBUtil.getDocument().findDocumentById(dbName, collName, id);
		System.out.println("---->"+doc);
	}
	
	@Test
	public void findDocument() {
		Bson filter = Filters.eq("age", 27);
		List<Document> docs = MongoDBUtil.getDocument().findDocument(dbName, collName, filter);
		System.out.println("age=27的数据，"+docs.size());
		for(Document doc: docs) {
			System.out.println("---->"+doc);
		}
		
		filter = Filters.lt("age", 28);
		docs = MongoDBUtil.getDocument().findDocument(dbName, collName, filter);
		System.out.println("age<28的数据，"+docs.size());
		for(Document doc: docs) {
			System.out.println("---->"+doc);
		}
		
		Bson filter1 = Filters.eq("name", "heqing");
		Bson filter2 = Filters.eq("age", 27);
		filter = Filters.and(filter1, filter2);
		docs = MongoDBUtil.getDocument().findDocument(dbName, collName, filter);
		System.out.println("name=heqing 且  age=26 的数据，"+docs.size());
		for(Document doc: docs) {
			System.out.println("---->"+doc);
		}
		filter1 = Filters.eq("age", 26);
		filter2 = Filters.eq("age", 27);
		filter = Filters.or(filter1, filter2);
		Bson filter3 = Filters.eq("name", "heqing");
		filter = Filters.and(filter3);
		docs = MongoDBUtil.getDocument().findDocument(dbName, collName, filter);
		System.out.println("name=heqing 且  (age=26 或 age=27) 的数据，"+docs.size());
		for(Document doc: docs) {
			System.out.println("---->"+doc);
		}
	}
	
	@Test
	public void findDocumentByPage() {
		Bson filter = Filters.eq("e_mail", "975656343@qq.com");
		//sort()方法可以通过参数指定排序的字段，并使用 1 和 -1 来指定排序的方式，其中 1 为升序排列，而-1是用于降序排列。
		Bson orderBy = Filters.eq("age", 1);
		int pageNo = 2;
		int pageSize = 2;
		List<Document> docs = MongoDBUtil.getDocument().findDocumentByPage(dbName, collName, filter, orderBy, pageNo, pageSize);
		System.out.println("e_mail=975656343的数据，"+docs.size());
		for(Document doc: docs) {
			System.out.println("---->"+doc);
		}
	}

	@Test
	public void findBigDocument() {
		Bson filter1 = MongoDBUtil.comparison("name", "AB", MongoDBConstants.comparison.END);
		Bson filter2 = MongoDBUtil.comparisonDigital("age", 18d, MongoDBConstants.comparisonDigital.GT);
		Bson filter = Filters.and(filter1, filter2);
		List<Document> docs = MongoDBUtil.getDocument().findDocument(dbName, collName, filter);
		System.out.println("以 AB 结尾，"+docs.size());
		for(Document doc: docs) {
			System.out.println("---->"+doc.get("name")+" : "+doc.get("age"));
		}
	}
}
