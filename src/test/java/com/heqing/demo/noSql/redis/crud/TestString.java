package com.heqing.demo.noSql.redis.crud;

import java.util.Set;

import com.heqing.demo.noSql.redis.RedisUtil;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * redis存储字符串
 */
public class TestString {

    private Jedis jedis; // 获取数据库的连接,非切片客户端连接
    
    @Before
    public void setup() {
        jedis = RedisUtil.getJedisPool().getResource();
    }
    
    /**
     * jedis.setnx("name","***") 不存在便增加，存在不会修改原来的值
     * jedis.set("name","***")   不存在便增加，存在会修改原来的值
     */
    @Test
    public void testAdd() {
    	System.out.println("=============增=============");
    	jedis.set("name","heqing");
        jedis.set("age","26");
        System.out.println(jedis.get("name"));
        System.out.println(jedis.get("age"));
        Set<String> keys = jedis.keys("*"); 
        System.out.println("共有"+keys.size()+"个键值");
        
        System.out.println("=============新增键值对时防止覆盖原先值=============");
        System.out.println("当age存在时，尝试新增age："+jedis.setnx("age", "27"));
        System.out.println("获取age对应的值："+jedis.get("age"));
        System.out.println("原先age不存在时，新增age："+jedis.set("age", "28"));
        System.out.println("获取age对应的值："+jedis.get("age"));
    }
    
    /** 
     * set 直接覆盖原来的数据
     * append 在原来的数据后增加 
     * incr 进行加1操作,并返回增加后的值
     */ 
    @Test
    public void testUpdate() {
    	System.out.println("=============改=============");
        System.out.println("直接覆盖name原来的数据："+jedis.set("name","heqing-update"));
        System.out.println("获取name对应的新值："+jedis.get("name"));
        System.out.println("在name原来值后面追加："+jedis.append("name","-append"));
        System.out.println("获取name对应的新值："+jedis.get("name")); 
        System.out.println("在age进行加1操作："+jedis.incr("age"));
        System.out.println("获取age对应的新值："+jedis.get("age"));
        
        System.out.println("=============获取原值，更新为新值一步完成=============");
        System.out.println("name原值："+jedis.getSet("name", "heqing-new"));
        System.out.println("name新值："+jedis.get("name"));
    }
    
    /** 
     * mset,mget同时新增，修改，查询多个键值对 
     * 等价于：
     * jedis.set("name","ssss"); 
     * jedis.set("jarorwar","xxxx"); 
     */ 
    @Test
    public void testFind() {
        System.out.println("=============获取子串=============");
        System.out.println("获取name对应值中的子串："+jedis.getrange("name", 3, 5)); 
    	
    	System.out.println("=============增，删，查（多个）=============");
    	Set<String> keys = jedis.keys("*"); 
        System.out.println("一次性新增name,age及其对应值："+jedis.mset("name","heqing","age","26"));  
        System.out.println("一次性获取name,age各自对应的值："+jedis.mget("name","age"));
        keys = jedis.keys("*"); 
        System.out.println("---->共有"+keys.size()+"个键值");
        System.out.println("一次性删除name,age："+jedis.del(new String[]{"name","age"}));
        System.out.println("一次性获取name,age各自对应的值："+jedis.mget("name","age")); 
        keys = jedis.keys("*"); 
        System.out.println("---->共有"+keys.size()+"个键值");
    }
    
    @Test
    public void testDelete() {
    	System.out.println("=============删=============");  
        System.out.println("删除name键值对："+jedis.del("name"));  
        System.out.println("获取name键对应的值："+jedis.get("name"));
        Set<String> keys = jedis.keys("*"); 
        System.out.println("共有"+keys.size()+"个键值");
        
        System.out.println("=============超过有效期键值对被删除=============");
        // 设置key的有效期，并存储数据 
        System.out.println("新增age，并指定过期时间为2秒"+jedis.setex("age", 2, "27")); 
        System.out.println("获取age对应的值："+jedis.get("age")); 
        try{ 
            Thread.sleep(3000); 
        } 
        catch (InterruptedException e){ 
        	e.printStackTrace();
        } 
        System.out.println("3秒之后，获取age对应的值："+jedis.get("age"));
    }
   
}
