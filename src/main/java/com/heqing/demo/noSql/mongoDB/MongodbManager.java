package com.heqing.demo.noSql.mongoDB;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

/**
 * Created by heqing on 2017/9/14.
 *
 * MongoDB工具类 Mongo实例代表了一个数据库连接池，即使在多线程的环境中，一个Mongo实例对我们来说已经足够了<br>
 * 注意Mongo已经实现了连接池，并且是线程安全的。 <br>
 * 设计为单例模式， 因 MongoDB的Java驱动是线程安全的，对于一般的应用，只要一个Mongo实例即可，<br>
 * Mongo有个内置的连接池（默认为10个） 对于有大量写和读的环境中，为了确保在一个Session中使用同一个DB时，<br>
 * DB和DBCollection是绝对线程安全的<br>
 */
public class MongodbManager {

    private static final Logger logger = LoggerFactory.getLogger(MongodbManager.class);

    private static MongoClient mongoClient = null;

    //mongodb服务器IP
    private static String mongodbIp = "";
    //mongodb的端口号
    private static int mongodbPort = 0;
    //安全认证模式 --> MongoDB启动的时候添加--auth参数
    //安全验证模式下的用户名
    private static String userName = "";
    //安全验证模式下的密码
    private static String password = "";
    //连接池设置为300个连接,默认int为100
    private static int perHost = 0;
    //连接超时，推荐>3000毫秒
    private static int timeout = 0;
    //最长等待时间
    private static int maxWaitTime = 0;
    //套接字超时时间，0无限制
    private static int socketTimeout = 0;
    // 线程队列数，如果连接线程排满了队列就会抛出“Out of semaphores to get db”错误。
    private static int allowedToBlockForConnection = 0;

    public MongodbManager() {}

    static {
        ResourceBundle budleEnv = ResourceBundle.getBundle("mongodb_config");

        mongodbIp   = budleEnv.getString("mongodbIp");
        mongodbPort = Integer.parseInt(budleEnv.getString("mongodbPort"));
        userName    = budleEnv.getString("userName");
        password    = budleEnv.getString("password");
        perHost     = Integer.parseInt(budleEnv.getString("perHost"));
        timeout     = Integer.parseInt(budleEnv.getString("timeout"));
        maxWaitTime = Integer.parseInt(budleEnv.getString("maxWaitTime"));
        socketTimeout = Integer.parseInt(budleEnv.getString("socketTimeout"));
        allowedToBlockForConnection = Integer.parseInt(budleEnv.getString("allowedToBlockForConnection"));

        mongoClient = new MongoClient(mongodbIp, mongodbPort);
        // 多个mongodb服务器连接如下
//         List<ServerAddress> listHost = Arrays.asList(new ServerAddress("localhost", 27017),new ServerAddress("localhost", 27018));
//         mongoClient = new MongoClient(listHost);

        MongoClientOptions.Builder options = new MongoClientOptions.Builder();
        options.connectionsPerHost(perHost);
        options.connectTimeout(timeout);
        options.maxWaitTime(maxWaitTime);
        options.socketTimeout(socketTimeout);
        options.threadsAllowedToBlockForConnectionMultiplier(allowedToBlockForConnection);
        options.build();
    }

    public static MongoClient getMongoClient() {
        if(mongoClient == null) mongoClient = new MongoClient(mongodbIp, mongodbPort);
        return mongoClient;
    }
}
