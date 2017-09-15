package com.heqing.demo.noSql.mongoDB.service;

import com.mongodb.client.MongoDatabase;

import java.util.List;

/**
 * Created by heqing on 2017/9/14.
 */
public interface IDataBaseOperations {

    /**
     * 该方法用于获取一个 MongoDB 数据库实例。注意：mongodb 区分大小写
     * dataBaseName相当于关系数据库里的数据库名，mongodb中若没有该数据库名也不会报错，默认mongodb会建立这个数据库名，为空。
     * @param dbName 数据库名
     * @return 数据库实例。
     */
    public MongoDatabase getMongoDB(String dbName);

    /**
     * 该方法用于删除一个名为 databaseName 的 MongoDB 数据库实例。注意：mongodb 区分大小写
     * @param dbName 数据库名
     */
    public void dropMongoDB(String dbName);

    /**
     * 该方法用返回所有数据库的名称
     * @return 所有数据库的名称。
     */
    public List<String> getDBAllName();

    /**
     * 该方法用关闭一个 mongoDB 客户端
     */
    public void closeClient();
}
