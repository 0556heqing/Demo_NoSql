package com.heqing.demo.noSql.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * Created by heqing on 2017/9/13.
 */
public abstract class BaseManager {

    protected static JedisPoolConfig poolConfig = new JedisPoolConfig();

    //服务器IP
    protected static String redisIp = "";
    //端口号
    protected static Integer redisPort = 0;
    //访问密码
    protected static String redisPassword = "";
    //数据库下标
    protected static Integer redisDb_index = 0;
    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    protected static Integer maxMotal = 0;
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    protected static Long maxWait = 0l;
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    protected static Integer maxIdle = 0;
    //控制一个pool最少有多少个状态为idle(空闲的)的jedis实例。
    protected static Integer minIdle = 0;
    //响应超时（默认2000ms）
    protected static Integer timeOut = 0;
    // 空闲连接多长时间后会被收回
    protected static Long minEvictableIdle = 0l;
    // 每30秒运行一次空闲连接回收器
    protected static Long timeBetweenEvictionRuns = 0l;

    static {
        ResourceBundle budleEnv = ResourceBundle.getBundle("redis_config");

        redisIp = budleEnv.getString("redisIp");
        redisPort = Integer.parseInt(budleEnv.getString("redisPort"));
        redisPassword = budleEnv.getString("redisPassword");
        redisDb_index = Integer.parseInt(budleEnv.getString("redisDb_index"));
        maxMotal = Integer.parseInt(budleEnv.getString("maxMotal"));
        maxWait = Long.parseLong(budleEnv.getString("maxWait"));
        maxIdle = Integer.parseInt(budleEnv.getString("maxIdle"));
        minIdle = Integer.parseInt(budleEnv.getString("minIdle"));
        timeOut = Integer.parseInt(budleEnv.getString("timeOut"));
        minEvictableIdle = Long.parseLong(budleEnv.getString("minEvictableIdle"));
        timeBetweenEvictionRuns = Long.parseLong(budleEnv.getString("timeBetweenEvictionRuns"));

        poolConfig.setMaxTotal(maxMotal);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxWaitMillis(maxWait);
        poolConfig.setTestOnBorrow(false);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(minEvictableIdle);
        poolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRuns);
        poolConfig.setNumTestsPerEvictionRun(-1);
    }

}
