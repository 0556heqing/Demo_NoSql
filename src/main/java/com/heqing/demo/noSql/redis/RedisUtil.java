package com.heqing.demo.noSql.redis;

import com.heqing.demo.noSql.redis.service.*;
import com.heqing.demo.noSql.redis.service.impl.*;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Created by heqing on 2017/9/6.
 */
public class RedisUtil {

    private static IJedisKeyOperations jedisKeyOperations;
    private static IJedisStringOperations jedisStringOperations;
    private static IJedisHashMapOperations jedisHashMapOperations;
    private static IJedisListOperations jedisListOperations;
    private static IJedisSetOperations jedisSetOperations;
    private static IJedisSortedSetOperations jedisSortedSetOperations;

    /**
     * 该方法用于获取 Jedis 线程池
     */
    public static JedisPool getJedisPool() {
        return RedisManager.getJedisPool();
    }

    /**
     * 该方法用于关闭 Jedis 线程池
     */
    public static void closeJedisPool() {
        RedisManager.closeJedisPool();
    }

    /**
     * 该方法用于销毁 Jedis 线程池
     */
    public static void destroyJedisPool() {
        RedisManager.destroyJedisPool();
    }

    /**
     * 该方法用于获取分布式 ShardedJedis 线程池
     */
    public static ShardedJedisPool getShardedJedisPool() {
        return ShardedJedisManager.getShardedJedisPool();
    }

    /**
     * 该方法用于关闭分布式 ShardedJedis 线程池
     */
    public static void closeShardedJedisPool() {
        ShardedJedisManager.closeShardedJedisPool();
    }

    /**
     * 该方法用于销毁分布式 ShardedJedis 线程池
     */
    public static void destroyShardedJedisPool() {
        ShardedJedisManager.destroyShardedJedisPool();
    }

     /**
     * 该方法用于获取操作 Key 的方法集合
     */
    public static IJedisKeyOperations getKey() {
        if(jedisKeyOperations == null) {
            jedisKeyOperations = new JedisKeyOperations();
        }
        return jedisKeyOperations;
    }

    /**
     * 该方法用于获取操作String 的方法集合
     */
    public static IJedisStringOperations getString() {
        if(jedisStringOperations == null) {
            jedisStringOperations = new JedisStringOperations();
        }
        return jedisStringOperations;
    }

    /**
     * 该方法用于获取操作 HashMap 的方法集合
     */
    public static IJedisHashMapOperations getHashMap() {
        if(jedisHashMapOperations == null) {
            jedisHashMapOperations = new JedisHashMapOperations();
        }
        return jedisHashMapOperations;
    }

    /**
     * 该方法用于获取操作 List 的方法集合
     */
    public static IJedisListOperations getList() {
        if(jedisListOperations == null) {
            jedisListOperations = new JedisListOperations();
        }
        return jedisListOperations;
    }

    /**
     * 该方法用于获取操作 Set(无序) 的方法集合
     */
    public static IJedisSetOperations getSet() {
        if(jedisSetOperations == null) {
            jedisSetOperations = new JedisSetOperations();
        }
        return jedisSetOperations;
    }

    /**
     * 该方法用于获取操作 SortSet(有序) 的方法集合
     */
    public static IJedisSortedSetOperations getSortedSet() {
        if (jedisSortedSetOperations == null) {
            jedisSortedSetOperations = new JedisSortedSetOperations();
        }
        return jedisSortedSetOperations;
    }
}