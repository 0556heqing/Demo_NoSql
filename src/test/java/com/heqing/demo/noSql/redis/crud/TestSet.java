package com.heqing.demo.noSql.redis.crud;

import java.util.Iterator;
import java.util.Set;

import com.heqing.demo.noSql.redis.RedisUtil;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

/** 
 * jedis操作Set 
 */  
public class TestSet {

	private Jedis jedis; // 获取数据库的连接,非切片客户端连接
    
    @Before
    public void setup() {
        jedis = RedisUtil.getJedisPool().getResource();
    }
    
    @Test
    public void testAdd() {
    	System.out.println("=============增=============");
        System.out.println("向user集合中加入元素name："+jedis.sadd("user", "name")); 
        System.out.println("向user集合中加入元素age："+jedis.sadd("user", "age"));
        System.out.println("向user集合中加入元素age："+jedis.sadd("user", "qq"));
        System.out.println("查看user集合中的所有元素:"+jedis.smembers("user")); 
    }
    
    @Test
    public void testFind() {
        System.out.println("=============查=============");
        System.out.println("判断name是否在集合user中："+jedis.sismember("user", "name"));
        System.out.println("判断who是否在集合user中："+jedis.sismember("user", "who"));
        System.out.println("循环查询获取sets中的每个元素：");
        Set<String> set = jedis.smembers("user");   
        Iterator<String> it=set.iterator() ;   
        while(it.hasNext()){   
            Object obj=it.next();   
            System.out.println(obj);   
        }
        System.out.println("随机返回key集合中的一个元素："+jedis.srandmember("user"));
        System.out.println("集合的元素个数  ："+jedis.scard("user"));
    }
    
	@Test
	public void testSet(){  
	    System.out.println("=============删=============");
	    System.out.println("删除前:"+jedis.smembers("user")); 
		System.out.println("集合user中删除元素qq："+jedis.srem("user", "qq"));
		System.out.println("删除qq后:"+jedis.smembers("user"));
        System.out.println("删除user键值对："+jedis.del("user"));  
        System.out.println("删除后:"+jedis.smembers("user"));
	}  
    
    @Test
    public void testOperation() {
        System.out.println("=============集合运算=============");
        System.out.println("user1中添加元素name："+jedis.sadd("user1", "name")); 
        System.out.println("user1中添加元素age："+jedis.sadd("user1", "age")); 
        System.out.println("user1中添加元素qq："+jedis.sadd("user1", "qq")); 
        System.out.println("user2中添加元素name："+jedis.sadd("user2", "name")); 
        System.out.println("user2中添加元素age："+jedis.sadd("user2", "age")); 
        System.out.println("user2中添加元素e_nail："+jedis.sadd("user2", "e_nail"));
        System.out.println("查看user1集合中的所有元素:"+jedis.smembers("user1"));
        System.out.println("查看user2集合中的所有元素:"+jedis.smembers("user2"));
        System.out.println("user1和user2交集："+jedis.sinter("user1", "user2"));
        System.out.println("user1和user2并集："+jedis.sunion("user1", "user2"));
        System.out.println("user1和user2差集："+jedis.sdiff("user1", "user2"));
        System.out.println("user2和user1差集："+jedis.sdiff("user2", "user1"));
    }

}
