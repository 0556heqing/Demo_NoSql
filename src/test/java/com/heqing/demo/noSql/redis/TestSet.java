package com.heqing.demo.noSql.redis;

import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * Created by heqing on 2017/9/8.
 */
public class TestSet {

    @Test
    public void sadd() {
        Long length = RedisUtil.getSet().add("set1", "test1", "test2", "test3");
        System.out.println("--->length = "+length);

        length = RedisUtil.getSet().add("set2", "test0", "test1", "test2");
        System.out.println("--->length = "+length);
    }

    @Test
    public void scard() {
        Long length = RedisUtil.getSet().getCount("set1");
        System.out.println("--->length = "+length);
    }

    @Test
    public void sdiff() {
        Set<String> diffs = RedisUtil.getSet().getDiff("set1", "set2");
        for(String diff : diffs) {
            System.out.println("--->diff = "+diff);
        }
    }

    @Test
    public void sdiffstore() {
        Long length = RedisUtil.getSet().getDiffAndMove("set","set1", "set2");
        System.out.println("--->length = "+length);
    }

    @Test
    public void sinter() {
        Set<String> values = RedisUtil.getSet().getIntersection("set1", "set2");
        for(String value : values) {
            System.out.println("--->value = "+value);
        }
    }

    @Test
    public void sinterstore() {
        Long length = RedisUtil.getSet().getIntersectionAndMove("set","set1", "set2");
        System.out.println("--->length = "+length);
    }

    @Test
    public void sismember() {
        Boolean isExist = RedisUtil.getSet().isMember("set","test1");
        System.out.println("--->isExist = "+isExist);
    }

    @Test
    public void smembers() {
        Set<String> values = RedisUtil.getSet().getMemberByKey("set");
        for(String value : values) {
            System.out.println("--->value = "+value);
        }
    }

    @Test
    public void smove() {
        Boolean isSuccess = RedisUtil.getSet().move("set2","set1", "test0");
        System.out.println("--->isSuccess = "+isSuccess);
    }

    @Test
    public void spop() {
        String member = RedisUtil.getSet().removeRandomMember("set1");
        System.out.println("--->member = "+member);
    }

    @Test
    public void srandmember() {
        List<String> members = RedisUtil.getSet().getRandomMember("set1", 1);
        for(String member : members) {
            System.out.println("--->member = "+member);
        }
    }

    @Test
    public void srem() {
        Long length = RedisUtil.getSet().remove("set2", "test1", "test2");
        System.out.println("--->length = "+length);
    }

    @Test
    public void sunion() {
        Set<String> members = RedisUtil.getSet().getUnion("set", "set1", "set2");
        for(String member : members) {
            System.out.println("--->member = "+member);
        }
    }

    @Test
    public void sunionstore() {
        Long length = RedisUtil.getSet().getUnionAndMove("set2", "set", "set1");
        System.out.println("--->length = "+length);
    }
}
