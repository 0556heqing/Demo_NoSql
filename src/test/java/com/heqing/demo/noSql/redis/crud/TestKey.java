package com.heqing.demo.noSql.redis.crud;

import java.util.Iterator;
import java.util.Set;

import com.heqing.demo.noSql.redis.RedisUtil;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class TestKey {

	private Jedis jedis; // 获取数据库的连接,非切片客户端连接
    
    @Before
    public void setup() {
        jedis = RedisUtil.getJedisPool().getResource();
    }
    
    @Test
    public void testAdd() {
    	System.out.println("=============增=============");
        // 判断key否存在 
        System.out.println("判断name键是否存在："+jedis.exists("name")); 
        System.out.println("新增name,heqing键值对："+jedis.set("name", "heqing")); 
        System.out.println("判断name键是否存在："+jedis.exists("name")); 
        
        // 设置 qq的过期时间
        System.out.println("判断qq是否存在："+jedis.set("qq", "975656343"));
        System.out.println("设置 qq的过期时间为3秒:"+jedis.expire("qq", 3));
        try{ 
            Thread.sleep(4000); 
        } 
        catch (InterruptedException e){ 
        } 
        System.out.println("判断qq是否存在："+jedis.exists("qq"));
        // 查看某个key的剩余生存时间,单位【秒】.永久生存或者不存在的都返回-1
        System.out.println("查看qq的剩余生存时间："+jedis.ttl("qq"));
        // 移除某个key的生存时间
        System.out.println("移除qq的生存时间："+jedis.persist("qq"));
    }
    
    @Test
    public void testFind() {
    	// 输出系统中所有的key
        Set<String> keys = jedis.keys("*");
        Iterator<String> it=keys.iterator() ;   
        while(it.hasNext()) {
      	  String key = it.next();
      	  System.out.println(key +" 的类型:"+ jedis.type(key));   
        }  
    }
    
    
	@Test
	public void testKey() {
        // 删除某个key,若key不存在，则忽略该命令。
        System.out.println("系统中删除name: "+jedis.del("name"));
        System.out.println("判断name是否存在："+jedis.exists("name"));
	}
	
	@Test
	public void hqTest() {
		System.out.println("系统中删除name: "+jedis.dump("name1"));
	}
}
