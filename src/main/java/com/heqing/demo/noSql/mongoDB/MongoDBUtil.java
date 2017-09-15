package com.heqing.demo.noSql.mongoDB;

import com.heqing.demo.noSql.mongoDB.service.ICollectionOperations;
import com.heqing.demo.noSql.mongoDB.service.IDataBaseOperations;
import com.heqing.demo.noSql.mongoDB.service.IDocumentOperations;
import com.heqing.demo.noSql.mongoDB.service.IFileOperations;
import com.heqing.demo.noSql.mongoDB.service.impl.CollectionOperations;
import com.heqing.demo.noSql.mongoDB.service.impl.DataBaseOperations;
import com.heqing.demo.noSql.mongoDB.service.impl.DocumentOperations;
import com.heqing.demo.noSql.mongoDB.service.impl.FileOperations;
import com.mongodb.BasicDBObject;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

/**
 * Created by heqing on 2017/9/14.
 */
public class MongoDBUtil {

    private static final Logger logger = LoggerFactory.getLogger(MongoDBUtil.class);

    private void analysisSQL() {
        String sql = "WHERE name='heqing' OR (age>=20 AND age<=80) ORDER BY id ASC, age DESC LIMIT 1,10";
        int whereIndex = -1;
        int orderIndex = -1;
        int limitIndex = -1;
        if(sql.contains("WHERE")) whereIndex = 0;
        if(sql.contains("ORDER BY")) orderIndex = sql.indexOf("ORDER BY");
        if(sql.contains("LIMIT")) limitIndex = sql.indexOf("LIMIT");
        if(whereIndex > -1) {
            System.out.println("--------where---------");
            int start = whereIndex+6;
            int end = sql.length();
            if(orderIndex > -1) end = orderIndex;
            if(orderIndex < 0 && limitIndex > -1) end = limitIndex;
            String a = sql.substring(start, end);
            System.out.println(a);
        }
        if(orderIndex > -1) {
            System.out.println("--------orderBy---------");
            int start = orderIndex+8;
            int end = sql.length();
            if(limitIndex > -1) end = limitIndex;
            String orderSql = sql.substring(start, end);
            if(orderSql.contains(",")) {
                String[] orderList = orderSql.split(",");
                for(String order : orderList) {
                    String[] tempOrderList = order.split(" ");
                    String field = tempOrderList[1];
                    String by = tempOrderList[2];
                    System.out.println(field+":"+by);
                }
            }
        }
        if(limitIndex > -1) {
            System.out.println("--------limit---------");
            int start = limitIndex+6;
            int end = sql.length();
            String limitSql = sql.substring(start, end);
            String[] limitList = limitSql.split(",");
            System.out.println(limitList[0]+":"+limitList[1]);
        }

    }

    private static IDataBaseOperations dataBaseOperations;
    private static ICollectionOperations collectionOperations;
    private static IDocumentOperations documentOperations;
    private static IFileOperations fileOperations;

    /**
     * 该方法用于获取操作库的方法集合
     */
    public static IDataBaseOperations getDataBase() {
        if(dataBaseOperations == null) {
            dataBaseOperations = new DataBaseOperations();
        }
        return dataBaseOperations;
    }

    /**
     * 该方法用于获取操作集合的方法集合
     */
    public static ICollectionOperations getCollection() {
        if(collectionOperations == null) {
            collectionOperations = new CollectionOperations();
        }
        return collectionOperations;
    }

    /**
     * 该方法用于获取操作文档的方法集合
     */
    public static IDocumentOperations getDocument() {
        if(documentOperations == null) {
            documentOperations = new DocumentOperations();
        }
        return documentOperations;
    }

    /**
     * 该方法用于获取操作文件的方法集合
     */
    public static IFileOperations getFile(String dbName) {
        if(fileOperations == null) {
            fileOperations = new FileOperations(dbName);
        }
        return fileOperations;
    }

    /**
     * 该方法用于生成查询条件
     * @param field 字段名
     * @param order 正序/倒序
     * @return 查询条件
     */
    public static Bson order(String field, MongoDBConstants.order order) {
        Bson bson = null;
        switch(order) {
            case ASC  : bson = Filters.eq(field, 1);  break;
            case DESC : bson = Filters.eq(field, -1); break;
        }
        return bson;
    }

    /**
     * 该方法用于生成比较数组条件
     * @param field 字段名
     * @param value 比较值
     * @param condition 比较条件
     * @return 查询条件
     */
    public static Bson comparisonDigital(String field, Double value, MongoDBConstants.comparisonDigital condition) {
        Bson bson = null;
        switch(condition) {
            case LT  : bson = Filters.lt(field, value);  break;
            case LTE : bson = Filters.lte(field, value); break;
            case GT  : bson = Filters.gt(field, value);  break;
            case GTE : bson = Filters.gte(field, value); break;
            case EQ  : bson = Filters.eq(field, value);  break;
            case NE  : bson = Filters.ne(field, value);  break;
        }
        return bson;
    }

    /**
     * 该方法用于生成比较字符条件
     * @param field 字段名
     * @param value 比较值
     * @param condition 比较条件
     * @return 查询条件
     */
    public static Bson comparison(String field, String value, MongoDBConstants.comparison condition) {
        Bson bson = null;
        Pattern pattern = null;
        BasicDBObject query = new BasicDBObject();
        switch(condition) {
            case EQ    : bson = Filters.eq(field, value);  break;
            case NE    : bson = Filters.ne(field, value);  break;
            case START :
                pattern = Pattern.compile("^"+value+".*$", Pattern.CASE_INSENSITIVE);
                query.put(field, pattern);
                bson = query;
                break;
            case LIKE  :
                pattern = Pattern.compile("^.*"+value+".*$", Pattern.CASE_INSENSITIVE);
                query.put(field, pattern);
                bson = query;
                break;
            case END   :
                pattern = Pattern.compile("^.*"+value+"$", Pattern.CASE_INSENSITIVE);
                query.put(field, pattern);
                bson = query;
                break;
        }
        return bson;
    }

}
