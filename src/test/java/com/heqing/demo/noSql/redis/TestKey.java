package com.heqing.demo.noSql.redis;

import org.junit.Test;

import java.util.Set;

/**
 * Created by heqing on 2017/9/6.
 */
public class TestKey {

    @Test
    public void delete() {
        RedisUtil.getString().set("name","heqing");
        long isSuccess = RedisUtil.getKey().delete("name");
        System.out.println("----->name = "+isSuccess);
        isSuccess = RedisUtil.getKey().delete("tempName");
        System.out.println("----->tempName = "+isSuccess);
    }

    @Test
    public void dump() {
        RedisUtil.getString().set("name","heqing");
        String serialize = RedisUtil.getKey().serialize("name");
        System.out.println("----->serialize = "+serialize);
    }

    @Test
    public void exists() {
        RedisUtil.getString().set("name","heqing");
        Boolean isExists = RedisUtil.getKey().isExists("name");
        System.out.println("----->isExists = "+isExists);
        RedisUtil.getKey().delete("name");
        isExists = RedisUtil.getKey().isExists("name");
        System.out.println("----->isExists = "+isExists);
    }

    @Test
    public void expire() {
        RedisUtil.getString().set("name","heqing");
        Boolean isSuccess = RedisUtil.getKey().setExpireBySecond("name", 5);
        System.out.println("----->isSuccess = "+isSuccess);
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e){
        }
        System.out.println("-->start = "+RedisUtil.getString().get("name"));
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e){}
        System.out.println("-->end = "+RedisUtil.getString().get("name"));
    }

    @Test
    public void pexpire() {
        RedisUtil.getString().set("name","heqing");
        Boolean isSuccess = RedisUtil.getKey().setExpireByMillisecond("name", 5000);
        System.out.println("----->isSuccess = "+isSuccess);
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e){
        }
        System.out.println("-->start = "+RedisUtil.getString().get("name"));
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e){}
        System.out.println("-->end = "+RedisUtil.getString().get("name"));
    }

    @Test
    public void keys() {
        RedisUtil.getString().set("name","heqing");
        Set<String> keyList = RedisUtil.getKey().getKeyByPattern("name");
        System.out.println("-->keyList = " + keyList);
    }

    @Test
    public void move() {
        RedisUtil.getString().set("name","heqing");
        System.out.println("----->start = "+RedisUtil.getString().get("name"));
        Boolean isSuccess = RedisUtil.getKey().moveToOtherDB("name", 1);
        System.out.println("----->isSuccess = "+ isSuccess);
        System.out.println("----->end = "+RedisUtil.getString().get("name"));
    }

    @Test
    public void persist() {
        RedisUtil.getString().set("name","heqing");
        Boolean isSuccess = RedisUtil.getKey().setExpireByMillisecond("name", 3000);
        System.out.println("----->isSuccess = "+isSuccess);
        isSuccess = RedisUtil.getKey().persist("name");
        System.out.println("----->isSuccess = "+isSuccess);
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e){
        }
        System.out.println("-->name = "+RedisUtil.getString().get("name"));
    }

    @Test
    public void pttl() {
        RedisUtil.getString().set("name","heqing");
        Boolean isSuccess = RedisUtil.getKey().setExpireByMillisecond("name", 5000);
        System.out.println("----->isSuccess = "+isSuccess);
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e){
        }
        System.out.println("-->time = "+RedisUtil.getKey().getRemainingMillisecond("name"));
    }

    @Test
    public void ttl() {
        RedisUtil.getString().set("name","heqing");
        Boolean isSuccess = RedisUtil.getKey().setExpireByMillisecond("name", 5000);
        System.out.println("----->isSuccess = "+isSuccess);
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e){
        }
        System.out.println("-->time = "+RedisUtil.getKey().getRemainingSecond("name"));
    }

    @Test
    public void randomkey() {
        RedisUtil.getString().set("name","heqing");
        RedisUtil.getString().set("qq","975656343");
        RedisUtil.getString().set("wechat","hq0556246512");
        String randomkey = RedisUtil.getKey().getRandomKey();
        System.out.println("----->randomkey = "+randomkey);
    }

    @Test
    public void rename() {
        RedisUtil.getString().set("name","heqing");
        System.out.println("-->start = "+RedisUtil.getString().get("name"));
        String isSuccess = RedisUtil.getKey().rename("name", "newName");
        System.out.println("----->isSuccess = "+isSuccess);
        System.out.println("-->end = "+RedisUtil.getString().get("name"));
        System.out.println("-->name = "+RedisUtil.getString().get("newName"));
    }

    @Test
    public void renamenx() {
        RedisUtil.getString().set("name","heqing");
        RedisUtil.getString().set("newName","newheqing");
        Boolean isSuccess = RedisUtil.getKey().renameNX("name", "newName");
        System.out.println("----->isSuccess = "+isSuccess);                     //newheqing
        System.out.println("-->name = "+RedisUtil.getString().get("newName"));
        RedisUtil.getKey().delete("newName");
        isSuccess = RedisUtil.getKey().renameNX("name", "newName");
        System.out.println("----->isSuccess = "+isSuccess);
        System.out.println("-->name = "+RedisUtil.getString().get("newName")); //heqing
    }

    @Test
    public void type() {
        RedisUtil.getString().set("name","heqing");
        System.out.println("-->name = "+RedisUtil.getString().get("name"));
        String type = RedisUtil.getKey().type("name");
        System.out.println("-->type = "+type);
    }

    @Test
    public void file() {
        RedisUtil.getString().set("db:name","heqing");
        System.out.println("-->name = "+RedisUtil.getString().get("db:name"));
    }

}
