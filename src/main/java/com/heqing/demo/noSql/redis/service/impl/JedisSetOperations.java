package com.heqing.demo.noSql.redis.service.impl;

import com.heqing.demo.noSql.redis.service.IJedisSetOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * Created by heqing on 2017/9/8.
 */
public class JedisSetOperations extends JedisKeyOperations implements IJedisSetOperations {

    private static final Logger logger = LoggerFactory.getLogger(JedisSetOperations.class);

    public Long add(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.sadd(key, members);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long getCount(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.scard(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Set<String> getDiff(String... keys) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.sdiff(keys);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long getDiffAndMove(String dstKey, String... keys) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.sdiffstore(dstKey, keys);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Set<String> getIntersection(String... keys) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.sinter(keys);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long getIntersectionAndMove(String dstKey, String... keys) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.sinterstore(dstKey, keys);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Boolean isMember(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.sismember(key, member);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Set<String> getMemberByKey(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.smembers(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Boolean move(String srcKey, String destKey, String member) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Long result = jedis.smove(srcKey, destKey, member);
            if(result == 1l) return true;
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String removeRandomMember(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.spop(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public List<String> getRandomMember(String key, int count) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.srandmember(key, count);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long remove(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.srem(key, members);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Set<String> getUnion(String... keys) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.sunion(keys);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long getUnionAndMove(String dstKey, String... keys) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.sunionstore(dstKey, keys);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
