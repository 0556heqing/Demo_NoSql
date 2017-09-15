package com.heqing.demo.noSql.redis.crud;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.heqing.demo.noSql.redis.RedisUtil;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * redis操作Map
 */
public class TestMap {

	private Jedis jedis; // 获取数据库的连接,非切片客户端连接
    
    @Before
    public void setup() {
        jedis = RedisUtil.getJedisPool().getResource();
    }
    
    @Test
    public void testAddAndUpdate() {
    	System.out.println("=============增或改============="); 
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "heqing");
        map.put("age", "27");
        map.put("qq", "975656343");
        jedis.hmset("user",map);
        Iterator<String> iter=jedis.hkeys("user").iterator();  
        while (iter.hasNext()){  
            String key = iter.next();  
            System.out.println("user集合中的键："+key+"，值:"+jedis.hmget("user",key));
        } 
    }
    
    @Test
    public void testFind() {
        System.out.println("=============查=============");
        System.out.println("user集合中所有的键："+jedis.hkeys("user"));//返回map对象中的所有key  
        System.out.println("user集合中所有的值："+jedis.hvals("user"));//返回map对象中的所有value
        //取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List  
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数  
        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println("user集合中name，age，qq："+rsmap);
    }
    
    @Test
    public void testDelete() { 	
        System.out.println("=============删=============");  
        System.out.println("删除前的值为："+jedis.hmget("user", "age"));
        jedis.hdel("user","age");
        System.out.println("删除后的值为："+jedis.hmget("user", "age")); //因为删除了，所以返回的是null  
        System.out.println("user中键的个数为："+jedis.hlen("user")); //返回key为user的键中存放的值的个数2 
        System.out.println("是否存在key为user："+jedis.exists("user"));//是否存在key为user的记录 返回true 
        System.out.println("删除user键值对："+jedis.del("user")); 
        System.out.println("是否存在key为user："+jedis.exists("user"));//是否存在key为user的记录 返回true 
    }
}
