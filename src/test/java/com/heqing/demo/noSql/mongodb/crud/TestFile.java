package com.heqing.demo.noSql.mongodb.crud;

import com.heqing.demo.noSql.mongoDB.MongoDBUtil;
import org.junit.Test;

public class TestFile {

	String dbName = "Demo_NoSql";
	
	@Test
	public void readFile() {
		MongoDBUtil.getFile(dbName).write("E:\\test", "Lighthouse.jpg");
	}
	
	@Test
	public void writeFile() {
		MongoDBUtil.getFile(dbName).read("E:\\test", "Lighthouse.jpg");
	}
	
	@Test 
	public void removeFile() {
		MongoDBUtil.getFile(dbName).deleteByFileName("Lighthouse.jpg");
	}
}
