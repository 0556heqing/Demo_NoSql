package com.heqing.demo.noSql.mongoDB.service.impl;

import com.heqing.demo.noSql.mongoDB.MongodbManager;
import com.heqing.demo.noSql.mongoDB.service.IDataBaseOperations;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by heqing on 2017/9/14.
 */
public class DataBaseOperations implements IDataBaseOperations {

    private static final Logger logger = LoggerFactory.getLogger(DataBaseOperations.class);

    private MongoClient mongoClient = null;

    public MongoClient getMongoClient() {
        if(mongoClient == null) {
            mongoClient = MongodbManager.getMongoClient();
        }
        return mongoClient;
    }

    public MongoDatabase getMongoDB(String dbName) {
        if (dbName != null && !"".equals(dbName)) {
            MongoDatabase database = getMongoClient().getDatabase(dbName);
            return database;
        }
        return null;
    }

    public void dropMongoDB(String dbName) {
        getMongoClient().dropDatabase(dbName);
    }

    public List<String> getDBAllName() {
        return getMongoClient().getDatabaseNames();
    }

    public void closeClient() {
        mongoClient = getMongoClient();
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }
}
