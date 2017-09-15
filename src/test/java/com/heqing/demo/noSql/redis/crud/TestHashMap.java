package com.heqing.demo.noSql.redis.crud;

import com.heqing.demo.noSql.redis.RedisUtil;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

/** 
 * jedis操作HashMap 
 */ 
public class TestHashMap {

	private Jedis jedis; // 获取数据库的连接,非切片客户端连接
    
    @Before
    public void setup() {
      jedis = RedisUtil.getJedisPool().getResource();
    }
    
    @Test
    public void testAdd() {
        System.out.println("=============增=============");
        System.out.println("user中添加name和heqing键值对："+jedis.hset("user", "name", "heqing")); 
        System.out.println("user中添加qq和975656343键值对："+jedis.hset("user", "qq", "975656343"));
        System.out.println("新增age和26的整型键值对："+jedis.hincrBy("user", "age", 26));
        System.out.println("user中的所有值："+jedis.hvals("user"));
    }
    
    @Test
    public void testUpdate() {
        System.out.println("=============改=============");
        System.out.println("修改前user中的所有值："+jedis.hvals("user"));
        System.out.println("age整型键值的值增加100："+jedis.hincrBy("user", "age", 100l));
        System.out.println("修改后user中的所有值："+jedis.hvals("user"));
    }
    
    @Test
    public void testFind() {
        System.out.println("=============查=============");
        System.out.println("判断name是否存在："+jedis.hexists("user", "name"));
        System.out.println("获取name对应的值："+jedis.hget("user", "name"));
        System.out.println("批量获取age和qq对应的值："+jedis.hmget("user", "age", "qq")); 
        System.out.println("获取user中所有的key："+jedis.hkeys("user"));
        System.out.println("获取user中所有的value："+jedis.hvals("user"));
    }
    
    @Test
    public void HashOperate() {
        System.out.println("=============删=============");
        System.out.println("删除前user中的所有值："+jedis.hvals("user"));
        System.out.println("user中删除qq键值对："+jedis.hdel("user", "qq"));
        System.out.println("删除后qquser中的所有值："+jedis.hvals("user"));
        System.out.println("删除user键值对："+jedis.del("user"));  
        System.out.println("删除后user中的所有值："+jedis.hvals("user"));
    }
}
