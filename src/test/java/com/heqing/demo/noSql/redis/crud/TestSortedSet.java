package com.heqing.demo.noSql.redis.crud;

import com.heqing.demo.noSql.redis.RedisUtil;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class TestSortedSet {

/** 
 * jedis操作SortedSet   有序集合
 */ 
private Jedis jedis; // 获取数据库的连接,非切片客户端连接
    
    @Before
    public void setup() {
        jedis = RedisUtil.getJedisPool().getResource();
    }
    
    @Test
    public void testAdd() {
        System.out.println("=============增=============");
        System.out.println("user中添加元素name："+jedis.zadd("user", 2.0, "name")); 
        System.out.println("user中添加元素age："+jedis.zadd("user", 8.0, "age")); 
        System.out.println("user中添加元素qq："+jedis.zadd("user", 5.0, "qq")); 
        System.out.println("user集合中的所有元素："+jedis.zrange("user", 0, -1));//按照权重值排序  由小到大
    }
    
    @Test
    public void testFind() {
        System.out.println("=============查=============");
        System.out.println("user集合中的所有元素："+jedis.zrange("user", 0, -1));//按照权重值排序  由小到大
        System.out.println("统计zset集合中的元素中个数："+jedis.zcard("user"));
        System.out.println("统计zset集合中权重某个范围内（1.0——5.0），元素的个数："+jedis.zcount("user", 1.0, 5.0));
        System.out.println("查看zset集合中user的权重："+jedis.zscore("user", "qq"));
        System.out.println("查看下标1到2范围内的元素值："+jedis.zrange("user", 1, 2));
    }
 
    @Test  
    public void testSortedSet(){ 
        System.out.println("=============删=============");
        System.out.println("user中删除元素age："+jedis.zrem("user", "age"));
        System.out.println("user集合中的所有元素："+jedis.zrange("user", 0, -1));
        // 删除key 
        System.out.println("删除user键值对："+jedis.del("user")); 
    }
}
