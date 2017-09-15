package com.heqing.demo.noSql.redis.service.impl;

import com.heqing.demo.noSql.redis.service.IJedisListOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by heqing on 2017/9/8.
 */
public class JedisListOperations extends JedisKeyOperations implements IJedisListOperations {

    private static final Logger logger = LoggerFactory.getLogger(JedisListOperations.class);

    public List<String> removeAndGetFirstIndex(int timeout, String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.blpop(timeout, key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public List<String> removeAndGetLastValue(int timeout, String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.brpop(timeout, key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String getValueByIndex(String key, long index) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.lindex(key, index);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long insert(String key, int position, String pivot, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            if(position == -1) {
                return jedis.linsert(key, BinaryClient.LIST_POSITION.BEFORE, pivot, value);
            } else if(position == 1) {
                return jedis.linsert(key, BinaryClient.LIST_POSITION.AFTER, pivot, value);
            }
            return -2l;
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
            return jedis.llen(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String removeAndGetFirstValue(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.lpop(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long addBeforeValue(String key, String... values) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.lpush(key, values);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long addBeforeValueNX(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.lpushx(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public List<String> getValueByRange(String key, Long start, Long end) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.lrange(key, start, end);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long removeByValue(String key, Long count, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.lrem(key, count, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String setValueByIndex(String key, Long index, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.lset(key, index, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String getValueByTrim(String key, Long start, Long end) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.ltrim(key, start, end);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String removeAndGetLastIndex(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.rpop(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String getLastIndexAndMove(String srcKey, String dstKey) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.rpoplpush(srcKey, dstKey);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long addAfterValue(String key, String... values) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.rpush(key, values);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long addAfterValueNX(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.rpushx(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
