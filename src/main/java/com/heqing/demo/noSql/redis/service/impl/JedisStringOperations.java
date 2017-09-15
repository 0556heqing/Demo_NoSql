package com.heqing.demo.noSql.redis.service.impl;

import com.alibaba.fastjson.JSON;
import com.heqing.demo.noSql.redis.service.IJedisKeyOperations;
import com.heqing.demo.noSql.redis.service.IJedisStringOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by heqing on 2017/9/6.
 */
public class JedisStringOperations extends JedisKeyOperations implements IJedisStringOperations {

    private static final Logger logger = LoggerFactory.getLogger(JedisStringOperations.class);

    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.set(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String set(String key, Object bean) {
        String json = JSON.toJSONString(bean);
        return set(key, bean);
    }

    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.get(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public <T> T get(String key, Class<T> clazz) {
        T object = null;
        String json = get(key);
        if("null".equals(json)) return null;
        try {
            object = JSON.parseObject(json, clazz);
        } catch(Exception e) {
            logger.error("---> String to Object fail ！  String = "+json, e);
            e.printStackTrace();
        }
        return object;
    }

    public String getRange(String key, long startOffset, long endOffset) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.getrange(key, startOffset, endOffset);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String getSet(String key, String newValue) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.getSet(key, newValue);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String setK_V(String... key_value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.mset(key_value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public List<String> getK_V(String... key_value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.mget(key_value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String setValueBySecond(String key, int secods, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.setex(key, secods, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String setValueBySecond(String key, int secods, Object bean) {
        String json = JSON.toJSONString(bean);
        return setValueBySecond(key, secods, bean);
    }

    public Boolean setnx(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Long result = jedis.setnx(key, value);
            if(result == 1l) return true;
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Boolean setnx(String key, Object bean) {
        String json = JSON.toJSONString(bean);
        return setnx(key, bean);
    }

    public Long setrange(String key, long offset, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.setrange(key, offset, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long getLength(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.strlen(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Boolean setK_Vnx(String... key_value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Long result = jedis.msetnx(key_value);
            if(result == 1l) return true;
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String setValueByMillisecond(String key, long millisecods, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.psetex(key, millisecods, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String setValueByMillisecond(String key, long millisecods, Object bean) {
        String json = JSON.toJSONString(bean);
        return setValueByMillisecond(key, millisecods, bean);
    }

    public Long incr(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.incr(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long incrByLong(String key, long value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.incrBy(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Double incrByDouble(String key, double value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.incrByFloat(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long decr(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.decr(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long decrByLong(String key, long value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.decrBy(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long append(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.append(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String substr(String key, int start, int end) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.substr(key, start, end);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
