package com.heqing.demo.noSql.redis.service.impl;

import com.heqing.demo.noSql.redis.service.IJedisSortedSetOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;

/**
 * Created by heqing on 2017/9/8.
 */
public class JedisSortedSetOperations extends JedisKeyOperations implements IJedisSortedSetOperations {

    private static final Logger logger = LoggerFactory.getLogger(JedisSortedSetOperations.class);

    public Long add(String key, Map<String, Double> scoreMembers) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.zadd(key, scoreMembers);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Long getcount(String key) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.zcard(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Long getCountByRange(String key, double min, double max) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.zcount(key, min, max);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Double addSroceByMember(String key, double sroce, String member) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.zincrby(key, sroce, member);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Long intersectionMove(String desKey, String... sets) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.zinterstore(desKey, sets);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Set<String> getRangeByIndex(String key, long start, long end) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.zrange(key, start, end);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Set<String> getRangeByScore(String key, double min, double max) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.zrangeByScore(key, min, max);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Long getRankByMember(String key, String member) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.zrank(key, member);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Long remove(String key, String... member) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.zrem(key, member);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Long removeByRank(String key, long start, long end) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.zremrangeByRank(key, start, end);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Long removeByScore(String key, double min, double max) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.zremrangeByScore(key, min, max);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Set<String> getRangeByIndexDesc(String key, long start, long end) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.zrevrange(key, start, end);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Set<String> getRangeByScoreDesc(String key, double max, double min) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.zrevrangeByScore(key, max, min);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Long getRankDescByMember(String key, String member) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.zrevrank(key, member);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Double getScoreByMember(String key, String member) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.zscore(key, member);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Long getUnionAndMove(String dstKey, String... sets) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.zunionstore(dstKey, sets);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

}
