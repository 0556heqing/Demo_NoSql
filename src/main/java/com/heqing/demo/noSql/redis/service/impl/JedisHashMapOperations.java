package com.heqing.demo.noSql.redis.service.impl;

import com.heqing.demo.noSql.redis.service.IJedisHashMapOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by heqing on 2017/9/7.
 */
public class JedisHashMapOperations extends JedisKeyOperations implements IJedisHashMapOperations {

    private static final Logger logger = LoggerFactory.getLogger(JedisHashMapOperations.class);

    public String set(String key, Map<String, String> hash) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.hmset(key, hash);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long deleteFileds(String key, String... fields) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.hdel(key, fields);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Boolean isExists(String key, String filed) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.hexists(key, filed);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String getValueByFiled(String key, String filed) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.hget(key, filed);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Map<String, String> getMapByKey(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.hgetAll(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long addLongValueByFiled(String key, String filed, long value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.hincrBy(key, filed, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Double addDoubleValueByFiled(String key, String filed, double value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.hincrByFloat(key, filed, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Set<String> getField(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.hkeys(key);
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
            return jedis.hlen(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public List<String> getFiledAndValue(String key, String... field) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.hmget(key, field);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Boolean setFiledAndValue(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Long result = jedis.hset(key, field, value);
            if(result == 1l) return true;
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Boolean setFiledAndValueNX(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Long result = jedis.hsetnx(key, field, value);
            if(result == 1l) return true;
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public List<String> getValue(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.hvals(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
