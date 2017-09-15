package com.heqing.demo.noSql.redis;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by heqing on 2017/9/8.
 */
public class TestSortedSet {

    @Test
    public void zadd() {
        Map<String, Double> sortedSet1 = new LinkedHashMap<>();
        sortedSet1.put("test1", 70d);
        sortedSet1.put("test2", 50d);
        sortedSet1.put("test3", 30d);
        Long length = RedisUtil.getSortedSet().add("sortedSet1", sortedSet1);
        System.out.println("--->length = "+length);

        Map<String, Double> sortedSet2 = new LinkedHashMap<>();
        sortedSet2.put("test0", 70d);
        sortedSet2.put("test1", 50d);
        sortedSet2.put("test4", 30d);
        length = RedisUtil.getSortedSet().add("sortedSet2", sortedSet2);
        System.out.println("--->length = "+length);
    }

    @Test
    public void zcard() {
        Long length = RedisUtil.getSortedSet().getcount("sortedSet");
        System.out.println("--->length = "+length);
    }

    @Test
    public void zcount() {
        Long length = RedisUtil.getSortedSet().getCountByRange("sortedSet", 30d, 60d);
        System.out.println("--->length = "+length);
    }

    @Test
    public void zincrby() {
        double score = RedisUtil.getSortedSet().addSroceByMember("sortedSet", 5d, "test1");
        System.out.println("--->score = "+score);
    }

    @Test
    public void zinterstore() {
        Long length = RedisUtil.getSortedSet().intersectionMove("sortedSet3", "sortedSet1", "sortedSet2");
        System.out.println("--->length = "+length);
    }

    @Test
    public void zrange() {
        Set<String> members = RedisUtil.getSortedSet().getRangeByIndex("sortedSet1", 1, 2);
        for(String member : members) {
            System.out.println("--->member = "+member);
        }
    }

    @Test
    public void zrangebyscore() {
        Set<String> members = RedisUtil.getSortedSet().getRangeByScore("sortedSet", 30d, 60d);
        for(String member : members) {
            System.out.println("--->member = "+member);
        }
    }

    @Test
    public void zrank() {
        Long index = RedisUtil.getSortedSet().getRankByMember("sortedSet", "test2");
        System.out.println("--->index = "+index);
    }

    @Test
    public void zrem() {
        Long length = RedisUtil.getSortedSet().remove("sortedSet", "test2");
        System.out.println("--->length = "+length);
    }

    @Test
    public void zremrangebyrank() {
        //  ?
        Long length = RedisUtil.getSortedSet().removeByRank("sortedSet", 10, 50);
        System.out.println("--->length = "+length);
    }

    @Test
    public void zremrangebyscore() {
        Long length = RedisUtil.getSortedSet().removeByScore("sortedSet", 10, 50);
        System.out.println("--->length = "+length);
    }

    @Test
    public void zrevrange() {
        Set<String> members = RedisUtil.getSortedSet().getRangeByIndexDesc("sortedSet1", 1, 2);
        for(String member : members) {
            System.out.println("--->member = "+member);
        }
    }

    @Test
    public void zrevrangebyscore() {
        Set<String> members = RedisUtil.getSortedSet().getRangeByScoreDesc("sortedSet1", 60, 40);
        for(String member : members) {
            System.out.println("--->member = "+member);
        }
    }

    @Test
    public void zrevrank() {
        Long length = RedisUtil.getSortedSet().getRankDescByMember("sortedSet1", "test2");
        System.out.println("--->length = "+length);
    }

    @Test
    public void zscore() {
        Double score = RedisUtil.getSortedSet().getScoreByMember("sortedSet1", "test2");
        System.out.println("--->score = "+score);
    }

    @Test
    public void zunionstore() {
        Long length = RedisUtil.getSortedSet().getUnionAndMove("sortedSet3", "sortedSet1", "sortedSet2");
        System.out.println("--->length = "+length);
    }
}
