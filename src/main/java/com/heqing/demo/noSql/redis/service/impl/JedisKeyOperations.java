package com.heqing.demo.noSql.redis.service.impl;

import com.heqing.demo.noSql.redis.RedisManager;
import com.heqing.demo.noSql.redis.service.IJedisKeyOperations;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * Created by heqing on 2017/9/6.
 */
public class JedisKeyOperations implements IJedisKeyOperations {

    private JedisPool pool;

    public JedisKeyOperations() {}

    public JedisPool getJedisPool() {
        if(pool == null) {
            pool = RedisManager.getJedisPool();
        }
        return pool;
    }

    public Long delete(String... keys) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.del(keys);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public String serialize(String key) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return new String(jedis.dump(key));
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Boolean isExists(String key) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.exists(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Boolean setExpireBySecond(String key, int seconds) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            Long result = jedis.expire(key, seconds);
            if(result == 1l) return true;
            return false;
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Boolean setExpireByMillisecond(String key, long millseconds) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            Long result = jedis.pexpire(key, millseconds);
            if(result == 1l) return true;
            return false;
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Set<String> getKeyByPattern(String pattern) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.keys(pattern);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Boolean moveToOtherDB(String key, int dbIndex) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            Long result = jedis.move(key, dbIndex);
            if(result == 1l) return true;
            return false;
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Boolean persist(String key) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            Long result = jedis.persist(key);
            if(result == 1l) return true;
            return false;
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Long getRemainingMillisecond(String key) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.pttl(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Long getRemainingSecond(String key) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.ttl(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public String getRandomKey() {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.randomKey();
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }
    public String rename(String oldName, String newName) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.rename(oldName, newName);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Boolean renameNX(String oldName, String newName) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            Long result = jedis.renamenx(oldName, newName);
            if(result == 1l) return true;
            return false;
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public String type(String key) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.type(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public Long getKeySize() {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.dbSize();
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }
}
