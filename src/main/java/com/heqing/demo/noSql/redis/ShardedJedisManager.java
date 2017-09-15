package com.heqing.demo.noSql.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heqing on 2017/9/11.
 */
public class ShardedJedisManager extends  BaseManager {

    private static final Logger logger = LoggerFactory.getLogger(ShardedJedisManager.class);

    private static ShardedJedisPool shardedJedisPool = null;

    public ShardedJedisManager() { }

    static {
        try {
            List<JedisShardInfo> jdsInfoList =new ArrayList<JedisShardInfo>();
            JedisShardInfo infoA = new JedisShardInfo(redisIp, redisPort);
            jdsInfoList.add(infoA);
//            if("null".endsWith(redisPassword)) {
//                infoA.setPassword(redisPassword);    //有密码加入AUTH
//            }
            shardedJedisPool = new ShardedJedisPool(poolConfig, jdsInfoList);

        } catch (Exception e) {
            logger.error("初始化 ShardedJedis 服务器失败>>>" + e.getMessage(), e);
        }
    }

    public static ShardedJedisPool getShardedJedisPool() {
        return shardedJedisPool;
    }

    public static void closeShardedJedisPool() {
        shardedJedisPool.close();
    }

    public static void destroyShardedJedisPool() {
        shardedJedisPool.destroy();
    }

}
