package com.heqing.demo.noSql.mongoDB.service.impl;

import com.heqing.demo.noSql.mongoDB.service.ICollectionOperations;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heqing on 2017/9/14.
 */
public class CollectionOperations extends DataBaseOperations implements ICollectionOperations {

    private static final Logger logger = LoggerFactory.getLogger(CollectionOperations.class);

    public List<String> getAllCollections(String dbName) {
        MongoIterable<String> colls = getMongoDB(dbName).listCollectionNames();
        List<String> list = new ArrayList<String>();
        for (String s : colls) {
            list.add(s);
        }
        return list;
    }

    public List<String> getAllCollectionsByDB(String dbName) {
        MongoDatabase db = getMongoDB(dbName);
        MongoIterable<String> colls = db.listCollectionNames();
        List<String> collsList = new ArrayList<String>();
        for (String s : colls) {
            collsList.add(s);
        }
        return collsList;
    }

    public void createCollection(String dbName, String collectionName) {
        MongoDatabase db = getMongoDB(dbName);
        db.createCollection(collectionName);
    }

    public void dropCollection(String dbName, String collectionName) {
        MongoDatabase db = getMongoDB(dbName);
        MongoCollection<Document> collection = db.getCollection(collectionName);
        if(collection != null) {
            collection.drop();
        }
    }
}
