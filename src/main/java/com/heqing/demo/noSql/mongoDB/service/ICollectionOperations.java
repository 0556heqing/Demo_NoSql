package com.heqing.demo.noSql.mongoDB.service;

import java.util.List;

/**
 * Created by heqing on 2017/9/14.
 */
public interface ICollectionOperations extends IDataBaseOperations {

    /**
     * 获取某个库下，某个集合中文档的名字
     * @param dbName	库名
     * @return  集合中所有文档的名字
     */
    public List<String> getAllCollections(String dbName);

    /**
     * 该方法用于获取名为 dbName 的数据库中的所有集合名。
     * dataBaseName相当于关系数据库里的数据库名，mongodb中若没有该数据库名也不会报错，默认mongodb会建立这个数据库名，为空。
     * @param dbName 数据库名
     * @return 名为 dbName 的数据库中的所有集合名。
     */
    public List<String> getAllCollectionsByDB(String dbName);

    /**
     * 该方法用于在指定的数据库中创建一个名为 collectionName 的集合。
     * @param dbName 指定的数据库。
     * @param collectionName 集合名。
     */
    public void createCollection(String dbName, String collectionName);

    /**
     * 该方法用于在指定的数据库中删除一个名为 collectionName 的集合。
     * @param dbName 指定的数据库。
     * @param collectionName 集合名。
     */
    public void dropCollection(String dbName, String collectionName);
}
