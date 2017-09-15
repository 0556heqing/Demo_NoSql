package com.heqing.demo.noSql.redis.Subscribe;

import com.heqing.demo.noSql.redis.RedisUtil;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by heqing on 2017/9/11.
 */
public class TestMsg {

    private Jedis jedis; // 获取数据库的连接,非切片客户端连接

    RedisMsgPubSubListener listener;

    @Before
    public void setup() {
        jedis = RedisUtil.getJedisPool().getResource();
        listener = new RedisMsgPubSubListener();
    }

    @Test
    public void TestSubscribe() {
        jedis.subscribe(listener, "redisChatTest");
        jedis.subscribe(listener, "test1", "test2");
        //也用数组的方式设置多个频道
        jedis.subscribe(listener, new String[]{"hello_foo","hello_test"});
    }

    @Test
    public void TestPublish() {
        jedis.publish("redisChatTest", "heqing");
    }

    @Test
    public void TestPSubscribe() {
        jedis.psubscribe(listener, "redisChatTest");
    }
}
