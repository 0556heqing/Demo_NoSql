package com.heqing.demo.noSql.redis;

import org.junit.Test;

import java.util.List;

/**
 * Created by heqing on 2017/9/7.
 */
public class TestString {

    @Test
    public void set() {
        String isSuccess = RedisUtil.getString().set("name","heqing");
        System.out.println("--->isSuccess = "+isSuccess);
    }

    @Test
    public void get() {
        String name = RedisUtil.getString().get("name");
        System.out.println("--->name = "+name);
    }

    @Test
    public void getRange() {
        String name = RedisUtil.getString().getRange("name", 2 , 5);
        System.out.println("--->name = "+name);
    }

    @Test
    public void getSet() {
        String oldName = RedisUtil.getString().getSet("name", "hq0556");
        System.out.println("--->oldName = "+oldName);
        String newName = RedisUtil.getString().get("name");
        System.out.println("--->newName = "+newName);
    }

    @Test
    public void mSet() {
        String isSuccess = RedisUtil.getString().setK_V("name", "heqing", "qq", "975656343");
        System.out.println("--->isSuccess = "+isSuccess);
        String name = RedisUtil.getString().get("name");
        System.out.println("--->name = "+name);
    }

    @Test
    public void gSet() {
        List<String> valueList = RedisUtil.getString().getK_V("name", "qq", "test");
        for(String value : valueList) {
            System.out.println("--->value = "+value);
        }
    }

    @Test
    public void setex() {
        RedisUtil.getString().set("name","heqing");
        String isSuccess = RedisUtil.getString().setValueBySecond("name", 3, "hq246512");
        System.out.println("--->isSuccess = "+isSuccess);
        System.out.println("-->start = "+RedisUtil.getString().get("name"));
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e){
        }
        System.out.println("-->end = "+RedisUtil.getString().get("name"));
    }

    @Test
    public void setnx() {
        RedisUtil.getString().set("name","heqing");
        Boolean isSuccess = RedisUtil.getString().setnx("name", "hq246512");
        System.out.println("--->isSuccess = "+isSuccess);
        System.out.println("-->name = "+RedisUtil.getString().get("name"));
        RedisUtil.getString().delete("name");
        isSuccess = RedisUtil.getString().setnx("name", "hq246512");
        System.out.println("--->isSuccess = "+isSuccess);
        System.out.println("-->name = "+RedisUtil.getString().get("name"));
    }

    @Test
    public void setrange() {
        RedisUtil.getString().set("name","heqing");
        Long length = RedisUtil.getString().setrange("name", 2, "246512");
        System.out.println("--->length = "+length);
        System.out.println("-->name = "+RedisUtil.getString().get("name"));
    }

    @Test
    public void strlen() {
        Long length = RedisUtil.getString().getLength("name");
        System.out.println("--->length = "+length);
    }

    @Test
    public void msetnx() {
        Boolean isSuccess = RedisUtil.getString().setK_Vnx("name", "heqing", "wechat", "hq0556246512");
        System.out.println("--->isSuccess = "+isSuccess);
        isSuccess = RedisUtil.getString().setK_Vnx("qq", "975656343", "wechat", "hq0556246512");
        System.out.println("--->isSuccess = "+isSuccess);
    }

    @Test
    public void psetex() {
        RedisUtil.getString().set("name","heqing");
        String isSuccess = RedisUtil.getString().setValueByMillisecond("name", 3000, "hq246512");
        System.out.println("--->isSuccess = "+isSuccess);
        System.out.println("-->start = "+RedisUtil.getString().get("name"));
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e){
        }
        System.out.println("-->end = "+RedisUtil.getString().get("name"));
    }

    @Test
    public void incr() {
        RedisUtil.getString().set("age","27");
        Long age = RedisUtil.getString().incr("age");
        System.out.println("--->age = "+age);
    }

    @Test
    public void incrBy() {
        RedisUtil.getString().set("age","20");
        Long age = RedisUtil.getString().incrByLong("age", 6);
        System.out.println("--->age = "+age);
    }

    @Test
    public void incrByFloat() {
        RedisUtil.getString().set("age","20");
        double age = RedisUtil.getString().incrByDouble("age", 6.3);
        System.out.println("--->age = "+age);
    }

    @Test
    public void decr() {
        RedisUtil.getString().set("age","20");
        long age = RedisUtil.getString().decr("age");
        System.out.println("--->age = "+age);
    }

    @Test
    public void decrBy() {
        RedisUtil.getString().set("age","20");
        long age = RedisUtil.getString().decrByLong("age", 5);
        System.out.println("--->age = "+age);
    }

    @Test
    public void append() {
        RedisUtil.getString().set("newName","hq");
        long length = RedisUtil.getString().append("newName", "_246512");
        System.out.println("--->length = "+length);
        System.out.println("--->name = "+RedisUtil.getString().get("newName"));
    }
}
