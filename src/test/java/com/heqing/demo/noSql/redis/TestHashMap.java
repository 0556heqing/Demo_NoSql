package com.heqing.demo.noSql.redis;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by heqing on 2017/9/7.
 */
public class TestHashMap {

    @Test
    public void hmset() {
        Map<String, String> info = new HashMap<>();
        info.put("name", "heqing");
        info.put("age", "27");
        info.put("qq", "975656343");
        info.put("wechat", "hq0556246512");
        String isSuccess = RedisUtil.getHashMap().set("info", info);
        System.out.println("--->isSuccess = "+isSuccess);
    }

    @Test
    public void append() {
        long length = RedisUtil.getHashMap().deleteFileds("info", "age", "qq", "test");
        System.out.println("--->length = "+length);
    }

    @Test
    public void hexists() {
        Boolean isExists = RedisUtil.getHashMap().isExists("info", "name");
        System.out.println("--->isExists = "+isExists);
        isExists = RedisUtil.getHashMap().isExists("info", "qq");
        System.out.println("--->isExists = "+isExists);
    }

    @Test
    public void hget() {
        String name = RedisUtil.getHashMap().getValueByFiled("info", "name");
        System.out.println("--->name = "+name);
        String qq = RedisUtil.getHashMap().getValueByFiled("info", "qq");
        System.out.println("--->qq = "+qq);
    }

    @Test
    public void hgetall() {
        Map<String, String> info = RedisUtil.getHashMap().getMapByKey("info");
        for (Map.Entry<String, String> entry : info.entrySet()) {
            System.out.println("key = " + entry.getKey() + " and value = " + entry.getValue());
        }
    }

    @Test
    public void hincrby() {
        long value = RedisUtil.getHashMap().addLongValueByFiled("info", "age", 1);
        System.out.println("--->value = "+value);
    }

    @Test
    public void hincrbyfloat() {
        double value = RedisUtil.getHashMap().addDoubleValueByFiled("info", "age", 1.5);
        System.out.println("--->value = "+value);
    }

    @Test
    public void hkeys() {
        Set<String> fields = RedisUtil.getHashMap().getField("info");
        for(String field : fields) {
            System.out.println("--->field = "+field);
        }
    }

    @Test
    public void hlen() {
        long length = RedisUtil.getHashMap().getCount("info");
        System.out.println("--->length = "+length);
    }

    @Test
    public void hmget() {
        List<String> values = RedisUtil.getHashMap().getFiledAndValue("info", "name", "qq");
        for(String value : values) {
            System.out.println("--->value = "+value);
        }
    }

    @Test
    public void hset() {
        Boolean isSuccess = RedisUtil.getHashMap().setFiledAndValue("info", "qq", "975656343");
        System.out.println("--->isSuccess = "+isSuccess);
    }

    @Test
    public void hsetnx() {
        Boolean isSuccess = RedisUtil.getHashMap().setFiledAndValueNX("info", "qq", "111");
        String name = RedisUtil.getHashMap().getValueByFiled("info", "qq");
        System.out.println("--->isSuccess = "+isSuccess+",  name = " + name);

        isSuccess = RedisUtil.getHashMap().setFiledAndValueNX("info", "address", "anqing");
        String address = RedisUtil.getHashMap().getValueByFiled("info", "address");
        System.out.println("--->isSuccess = "+isSuccess+",  address = " + address);
    }

    @Test
    public void hvals() {
        List<String> infoList = RedisUtil.getHashMap().getValue("info");
        for(String info : infoList) {
            System.out.println("--->info = "+info);
        }
    }
}
