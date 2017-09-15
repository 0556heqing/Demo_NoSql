package com.heqing.demo.noSql.mongoDB.service.impl;

import com.heqing.demo.noSql.mongoDB.service.IDocumentOperations;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heqing on 2017/9/15.
 */
public class DocumentOperations extends CollectionOperations implements IDocumentOperations {

    private static final Logger logger = LoggerFactory.getLogger(DocumentOperations.class);



    public MongoCollection<Document> findCollection(String dbName, String collName) {
        if (null == dbName || "".equals(dbName))  	 return null;
        if (null == collName || "".equals(collName)) return null;
        MongoCollection<Document> collection = getMongoDB(dbName).getCollection(collName);
        return collection;
    }

    public void addDocument(String dbName, String collName, Document document) {
        addDocument(findCollection(dbName, collName), document);
    }

    public void addDocument(String dbName, String collName, List<Document> documents) {
        addDocument(findCollection(dbName, collName), documents);
    }

    public void addDocument(MongoCollection<Document> coll, Document document) {
        List<Document> documents = new ArrayList<Document>();
        documents.add(document);
        addDocument(coll, documents);
    }

    public void addDocument(MongoCollection<Document> coll, List<Document> documents) {
        coll.insertMany(documents);
    }

    public Document updateDocumentById(String dbName, String collName, String id, Document newdoc) {
        return updateDocumentById(findCollection(dbName, collName), id, newdoc);
    }

    public Document updateDocumentById(MongoCollection<Document> coll, String id, Document newdoc) {
        ObjectId _idobj = null;
        try {
            _idobj = new ObjectId(id);
        } catch (Exception e) {
            return null;
        }
        Bson filter = Filters.eq("_id", _idobj);
        return updateDocument(coll, filter, newdoc);
    }

    public Document updateDocument(String dbName, String collName, Bson filter, Document newdoc) {
        return updateDocument(findCollection(dbName, collName), filter, newdoc);
    }

    public Document updateDocument(MongoCollection<Document> coll, Bson filter, Document newdoc) {
        // coll.replaceOne(filter, newdoc); // 完全替代
        coll.updateMany(filter, new Document("$set", newdoc));
        return newdoc;
    }

    public int deleteDocumentById(String dbName, String collName, String id) {
        return deleteDocumentById(findCollection(dbName, collName), id);
    }

    public int deleteDocumentById(MongoCollection<Document> coll, String id) {
        ObjectId _id = null;
        try {
            _id = new ObjectId(id);
        } catch (Exception e) {
            return 0;
        }
        Bson filter = Filters.eq("_id", _id);
        return deleteDocument(coll, filter);
    }

    public int deleteDocument(String dbName, String collName, Bson filter) {
        return deleteDocument(findCollection(dbName, collName), filter);
    }

    public int deleteDocument(MongoCollection<Document> coll, Bson filter) {
        int count = 0;
        DeleteResult deleteResult = coll.deleteOne(filter);
        count = (int) deleteResult.getDeletedCount();
        return count;
    }

    public Document findDocumentById(String dbName, String collName, String id) {
        return findDocumentById(findCollection(dbName, collName), id);
    }

    public Document findDocumentById(MongoCollection<Document> coll, String id) {
        ObjectId _idobj = null;
        try {
            _idobj = new ObjectId(id);
        } catch (Exception e) {
            return null;
        }
        Document myDoc = coll.find(Filters.eq("_id", _idobj)).first();
        return myDoc;
    }

    public List<Document> findAllDocument(String dbName, String collName) {
        return findAllDocument(findCollection(dbName, collName));
    }

    public List<Document> findAllDocument(MongoCollection<Document> coll) {
        Bson orderBy = new BasicDBObject("_id", -1);
        MongoCursor<Document> mongoCursor = coll.find().sort(orderBy).iterator();
        List<Document> result = new ArrayList<Document>();
        while(mongoCursor.hasNext()){
            result.add(mongoCursor.next());
        }
        return result;
    }

    public List<Document> findAllDocument(String dbName, String collName, Bson orderBy) {
        return findAllDocument(findCollection(dbName, collName),orderBy);
    }

    public List<Document> findAllDocument(MongoCollection<Document> coll, Bson orderBy) {
        MongoCursor<Document> mongoCursor = coll.find().sort(orderBy).iterator();
        List<Document> result = new ArrayList<Document>();
        while(mongoCursor.hasNext()){
            result.add(mongoCursor.next());
        }
        return result;
    }

    public List<Document> findDocument(String dbName, String collName, Bson filter) {
        return findDocument(findCollection(dbName, collName), filter);
    }

    public List<Document> findDocument(MongoCollection<Document> coll, Bson filter) {
        Bson orderBy = new BasicDBObject("_id", -1);
        MongoCursor<Document> mongoCursor = coll.find(filter).sort(orderBy).iterator();
        List<Document> result = new ArrayList<Document>();
        while(mongoCursor.hasNext()){
            result.add(mongoCursor.next());
        }
        return result;
    }

    public List<Document> findDocument(String dbName, String collName, Bson filter, Bson orderBy) {
        return findDocument(findCollection(dbName, collName), filter, orderBy);
    }

    public List<Document> findDocument(MongoCollection<Document> coll, Bson filter, Bson orderBy) {
        MongoCursor<Document> mongoCursor = coll.find(filter).sort(orderBy).iterator();
        List<Document> result = new ArrayList<Document>();
        while(mongoCursor.hasNext()){
            result.add(mongoCursor.next());
        }
        return result;
    }

    public List<Document> findDocumentByPage(String dbName, String collName, Bson filter, Bson orderBy, int pageNo, int pageSize) {
        return findDocumentByPage(findCollection(dbName, collName), filter, orderBy, pageNo, pageSize);
    }

    public List<Document> findDocumentByPage(MongoCollection<Document> coll, Bson filter, Bson orderBy, int pageNo, int pageSize) {
        if(orderBy == null) orderBy = new BasicDBObject("_id", -1);
        MongoCursor<Document> mongoCursor = coll.find(filter).sort(orderBy).skip((pageNo - 1) * pageSize).limit(pageSize).iterator();
        List<Document> result = new ArrayList<Document>();
        while(mongoCursor.hasNext()){
            result.add(mongoCursor.next());
        }
        return result;
    }
}
