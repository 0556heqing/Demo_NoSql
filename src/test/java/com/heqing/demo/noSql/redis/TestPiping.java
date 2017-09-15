package com.heqing.demo.noSql.redis;

import com.heqing.demo.noSql.redis.RedisUtil;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

//分布式
public class TestPiping {

	private ShardedJedis  shardedJedis; //切片客户端  (分布式)
	
	@Before
    public void setup() {
      shardedJedis =  RedisUtil.getShardedJedisPool().getResource();
    }
	
    /** 
     * 分布式直接连接，并且是同步调用，每步执行都返回执行结果
     */
    @Test
    public void testShardNormal() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
//        	shardedJedis.set("sn:" + i, "n" + i);
        	shardedJedis.del("sn:" + i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Simple@Sharing SET: " + ((end - start)/1000.0));
        shardedJedis.disconnect();
    }
    
    /** 
     * 分布式直接连接，并且是异步调用，每步执行都返回执行结果
     */
    @Test
    public void testShardpipelined() {
        ShardedJedisPipeline pipeline = shardedJedis.pipelined();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
//            pipeline.set("sp:" + i, "p" + i);
            pipeline.del("sp:" + i);
        }
        pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        System.out.println("Pipelined@Sharing SET: " + ((end - start)/1000.0));
        shardedJedis.disconnect();
    }
    
    /** 
     * 分布式调用代码是运行在线程中，那么上面两个直连调用方式就不合适了，因为直连方式是非线程安全的.必须选择连接池调用
     * 同步调用
     */
    @Test
    public void testShardSimplePool() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
//            String result = shardedJedis.set("spn:" + i, "n" + i);
        	shardedJedis.del("spn:" + i);
        }
        long end = System.currentTimeMillis();
        RedisUtil.closeShardedJedisPool();
        System.out.println("Simple@Pool SET: " + ((end - start)/1000.0) + " seconds");
        RedisUtil.destroyShardedJedisPool();
    }

    /** 
     * 分布式连接池异步调用
     */
    @Test
    public void testShardPipelinedPool() {
        ShardedJedisPipeline pipeline = shardedJedis.pipelined();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
//            pipeline.set("sppn:" + i, "n" + i);
        	pipeline.del("sppn:" + i);
        }
        pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        RedisUtil.closeShardedJedisPool();
        System.out.println("Pipelined@Pool SET: " + ((end - start)/1000.0) + " seconds");
        RedisUtil.destroyShardedJedisPool();
    }
}
