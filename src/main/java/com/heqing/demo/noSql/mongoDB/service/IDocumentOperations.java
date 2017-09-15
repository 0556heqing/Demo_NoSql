package com.heqing.demo.noSql.mongoDB.service;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

/**
 * Created by heqing on 2017/9/15.
 */
public interface IDocumentOperations extends ICollectionOperations {



    /**
     * 获取某个库下，某个集合中文档
     * @param dbName	库名
     * @param collName	集合名
     * @return  集合中所有文档
     */
    public MongoCollection<Document> findCollection(String dbName, String collName);

    /**
     * 在数据库集合中添加文档
     * @param dbName 数据库名
     * @param collName 集合名
     * @param document 新文档名
     */
    public void addDocument(String dbName, String collName, Document document);

    /**
     * 在数据库集合中添加文档
     * @param dbName 数据库名
     * @param collName 集合名
     * @param documents 多个文档名
     */
    public void addDocument(String dbName, String collName, List<Document> documents);

    /**
     * 在数据库集合中添加文档
     * @param coll 集合
     * @param document 新文档名
     */
    public void addDocument(MongoCollection<Document> coll, Document document);

    /**
     * 在数据库集合中添加文档
     * @param coll 集合
     * @param documents 多个文档名
     */
    public void addDocument(MongoCollection<Document> coll, List<Document> documents);

    /**
     * 根据文档 Id 修改数据库集合中文档
     * @param dbName	库名
     * @param collName	集合名
     * @param id		id
     * @param newdoc	新文档
     * @return 修改后的文档
     */
    public Document updateDocumentById(String dbName, String collName, String id, Document newdoc);

    /**
     * 根据文档 Id 修改数据库集合中文档
     * @param coll		集合文档
     * @param id		id
     * @param newdoc	新文档
     * @return 修改后的文档
     */
    public Document updateDocumentById(MongoCollection<Document> coll, String id, Document newdoc);

    /**
     * 根据文档 Id 修改数据库集合中文档
     * @param dbName	库名
     * @param collName	集合名
     * @param filter	查询条件
     * @param newdoc	新文档
     * @return 修改后的文档
     */
    public Document updateDocument(String dbName, String collName, Bson filter, Document newdoc);

    /**
     * 根据文档 Id 修改数据库集合中文档
     * @param coll		集合文档
     * @param filter	查询条件
     * @param newdoc	新文档
     * @return 修改后的文档
     */
    public Document updateDocument(MongoCollection<Document> coll, Bson filter, Document newdoc);

    /**
     * 通过 id 删除数据库集合中文档
     * @param dbName	库名
     * @param collName	集合名
     * @param id		id
     * @return
     */
    public int deleteDocumentById(String dbName, String collName, String id);

    /**
     * 通过 id 删除数据库集合中文档
     * @param coll	集合
     * @param id	id
     * @return
     */
    public int deleteDocumentById(MongoCollection<Document> coll, String id);

    /**
     * 通过 id 删除数据库集合中文档
     * @param dbName	库名
     * @param collName	集合名
     * @param filter	查询条件
     * @return
     */
    public int deleteDocument(String dbName, String collName, Bson filter);

    /**
     * 通过 id 删除数据库集合中文档
     * @param coll		集合
     * @param filter	查询条件
     * @return
     */
    public int deleteDocument(MongoCollection<Document> coll, Bson filter);

    /**
     * 通过 id 查找数据库集合中文档
     * @param dbName	库名
     * @param collName	集合名
     * @param id        文档id
     * @return 查找到文档
     */
    public Document findDocumentById(String dbName, String collName, String id);

    /**
     * 通过 id 查找数据库集合中文档
     * @param coll  集合
     * @param id    文档id
     * @return 查找到文档
     */
    public Document findDocumentById(MongoCollection<Document> coll, String id);


    /**
     * 查找数据库集合中所有文档
     * @param dbName	库名
     * @param collName	集合名
     * @return  查找到文档
     */
    public List<Document> findAllDocument(String dbName, String collName);

    /**
     * 查找数据库集合中所有文档
     * @param coll  集合文档
     * @return  查找到文档
     */
    public List<Document> findAllDocument(MongoCollection<Document> coll);

    /**
     * 查找数据库集合中所有文档
     * @param dbName	库名
     * @param collName	集合名
     * @param orderBy	排序条件
     * @return 查找到文档
     */
    public List<Document> findAllDocument(String dbName, String collName, Bson orderBy);

    /**
     * 查找数据库集合中所有文档
     * @param coll		集合文档
     * @param orderBy	排序条件
     * @return  查找到文档
     */
    public List<Document> findAllDocument(MongoCollection<Document> coll, Bson orderBy);

    /**
     * 根据条件查找文档
     * @param dbName	库名
     * @param collName	集合名
     * @param filter	查询条件
     * @return 查找到文档
     */
    public List<Document> findDocument(String dbName, String collName, Bson filter);

    /**
     * 根据条件查找文档
     * @param coll		集合文档
     * @param filter	查询条件
     * @return 查找到文档
     */
    public List<Document> findDocument(MongoCollection<Document> coll, Bson filter);

    /**
     * 根据条件查找文档,并且根据排序条件排序
     * @param dbName	库名
     * @param collName	集合名
     * @param filter	查询条件
     * @param orderBy	排序条件
     * @return 查找到文档
     */
    public List<Document> findDocument(String dbName, String collName, Bson filter, Bson orderBy);

    /**
     * 根据条件查找文档,并且根据排序条件排序
     * @param coll		集合文档
     * @param filter	查询条件
     * @param orderBy	排序条件
     * @return 查找到文档
     */
    public List<Document> findDocument(MongoCollection<Document> coll, Bson filter, Bson orderBy);

    /**
     * 根据条件查找文档,并且根据排序条件排序,并且根据条件分页
     * @param dbName	库名
     * @param collName	集合名
     * @param filter	条件查询
     * @param orderBy	排序查询
     * @param pageNo	页数
     * @param pageSize	每页数量
     * @return 查找到文档
     */
    public List<Document> findDocumentByPage(String dbName, String collName, Bson filter, Bson orderBy, int pageNo, int pageSize);

    /**
     * 根据条件查找文档,并且根据排序条件排序,并且根据条件分页
     * @param coll		集合文档
     * @param filter	条件查询
     * @param orderBy	排序查询
     * @param pageNo	页数
     * @param pageSize	每页数量
     * @return  查找到文档
     */
    public List<Document> findDocumentByPage(MongoCollection<Document> coll, Bson filter, Bson orderBy, int pageNo, int pageSize);
}
