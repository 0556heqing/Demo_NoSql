package com.heqing.demo.noSql.redis;

import org.junit.Test;

import java.util.List;

/**
 * Created by heqing on 2017/9/8.
 */
public class TestList {

    @Test
    public void blpop() {
        List<String> values = RedisUtil.getList().removeAndGetFirstIndex(10,   "list1");
        for(String value : values) {
            System.out.println("--->value = "+value);
        }
    }

    @Test
    public void brpop() {
        List<String> values = RedisUtil.getList().removeAndGetLastValue(10,   "list1");
        for(String value : values) {
            System.out.println("--->value = "+value);
        }
    }

    @Test
    public void lindex() {
        Long length = RedisUtil.getList().addBeforeValue("list1", "test1", "test2", "test3");
        String value = RedisUtil.getList().getValueByIndex("list1", 0);
        System.out.println("--->value = "+value);
    }

    @Test
    public void linsert() {
        Long length = RedisUtil.getList().insert("list", -1, "test2", "test1");
        System.out.println("--->length = "+length);

        length = RedisUtil.getList().insert("list", 1, "test2", "test3");
        System.out.println("--->length = "+length);
    }

    @Test
    public void llen() {
        Long length = RedisUtil.getList().getCount("list");
        System.out.println("--->length = "+length);
    }

    @Test
    public void lpop() {
        String value = RedisUtil.getList().removeAndGetFirstValue("list");
        System.out.println("--->value = "+value);
    }

    @Test
    public void lpush() {
        Long length = RedisUtil.getList().addBeforeValue("list1", "test1", "test2", "test3");
        System.out.println("--->length = "+length);
    }

    @Test
    public void lpushx() {
        Long length = RedisUtil.getList().addBeforeValueNX("list2", "test1");
        System.out.println("--->length = "+length);

        length = RedisUtil.getList().addBeforeValueNX("list1", "test4");
        System.out.println("--->length = "+length);
    }

    @Test
    public void lrange() {
        List<String> values = RedisUtil.getList().getValueByRange("list1", 1l, 2l);
        for(String value : values) {
            System.out.println("--->value = "+value);
        }
    }

    @Test
    public void lrem() {
        Long length = RedisUtil.getList().removeByValue("list1", -1l, "test4");
        System.out.println("--->length = "+length);
    }

    @Test
    public void lset() {
        String length = RedisUtil.getList().setValueByIndex("list1", 2l, "test0");
        System.out.println("--->length = "+length);
    }

    @Test
    public void ltrim() {
        String value = RedisUtil.getList().getValueByTrim("list1", 0l, 1l);
        System.out.println("--->value = "+value);
    }

    @Test
    public void rpop() {
        String value = RedisUtil.getList().removeAndGetLastIndex("list1");
        System.out.println("--->value = "+value);
    }

    @Test
    public void rpoplpush() {
        String value = RedisUtil.getList().getLastIndexAndMove("list1", "list");
        System.out.println("--->value = "+value);
    }

    @Test
    public void rpush() {
        Long length = RedisUtil.getList().addAfterValue("list", "test4", "test5");
        System.out.println("--->length = "+length);
    }

    @Test
    public void rpushx() {
        Long length = RedisUtil.getList().addAfterValueNX("list", "test6");
        System.out.println("--->length = "+length);
    }
}
