package com.heqing.demo.noSql.redis;

import java.util.List;

import com.heqing.demo.noSql.redis.RedisUtil;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;

public class TestAffairs {
	
    private Jedis jedis; // 获取数据库的连接,非切片客户端连接
    
    @Before
    public void setup() {
      jedis = RedisUtil.getJedisPool().getResource();
    }
    
    @Test
    public void testRedisPool() {
    	//清空redis中所有数据。不可轻易尝试
//    	System.out.println("清空库中所有数据："+jedis.flushDB());

        jedis.set("test", "中文测试");
        System.out.println(jedis.get("test"));
    }
    
    /** 
     * 事物主要目的是保障，一个client发起的事务中的命令可以连续的执行，而中间不会插入其他client的命令。
     * jedis事物 以 MULTI 开始一个事务， 然后将多个命令入队到事务中， 最后由 EXEC 命令触发事务
     */
    @Test
    public void testTrans() {
        long start = System.currentTimeMillis();
        Transaction tx = jedis.multi();
        for (int i = 0; i < 100000; i++) {
//            tx.set("t:" + i, "t" + i);
            tx.del("t:" + i);
        }
        List<Object> results = tx.exec();
        for (Object rt : results) System. out.println(rt.toString());

        long end = System.currentTimeMillis();
        System.out.println("Transaction SET: " + ((end - start)/1000.0) + " s");
        jedis.disconnect();
    }

    /**
     * 管道中使用事务
     */
    @Test
    public void testCombPipelineTrans() {
        long start = System.currentTimeMillis();
        Pipeline pipeline = jedis.pipelined();
        pipeline.multi();
        for (int i = 0; i < 100000; i++) {
//            pipeline.set("pt:" + i, "" + i);
            pipeline.del("pt:" + i);
        }
        pipeline.exec();
        pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        System.out.println("Pipelined transaction: " + ((end - start)/1000.0) + " s");
        jedis.disconnect();
    }
    
    /** 
     * 管道采用异步方式，一次发送多个指令，不同步等待其返回结果。提高执行效率
     */
    @Test
    public void testPipelined() {
        Pipeline pipeline = jedis.pipelined();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
//            pipeline.set("p:" + i, "p" + i);
        	pipeline.del("p:" + i);
        }
        pipeline.syncAndReturnAll();
        
        long end = System.currentTimeMillis();
        System.out.println("Pipelined SET: " + ((end - start)/1000.0) + " s");
        jedis.disconnect();
    }

    //经测试运行时间：Pipelined < Pipelined transaction < Transaction
}
