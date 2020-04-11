package com.hust.hydroelectric_backend.utils;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/18 19:05
 */
@Component
public class JedisUtil {

//    private static String HOST = "222.20.81.50";
    private static String HOST = "127.0.0.1";

    private static int PORT = 6379;

    private JedisUtil() {
    }

    private static volatile JedisPool jedisPool = null;

    @PostConstruct
    private void init() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1000);           // 最大连接数
        config.setMaxIdle(20);              // 最大空闲连接数
        config.setMaxWaitMillis(100 * 1000);  // 最大等待时间
        config.setTestOnBorrow(true);       // 检查连接可用性, 确保获取的redis实例可用
        jedisPool = new JedisPool(config, HOST, PORT);
    }

    /**
     * redis client 存储key 设置过期时间
     *
     * @param key     key
     * @param seconds key过期时间
     * @return 是否存储成功
     */
    public boolean incKey(String key, int seconds) {
        Jedis jedis = jedisPool.getResource();
        if (jedis != null) {
            try {
                jedis.incr(key);
                if (seconds >= 0) {
                    jedis.expire(key, seconds);
                }
                return true;
            } catch (Exception e) {
                System.err.println("redis client error:" + e);
                return false;
            } finally {
                jedis.close();
            }
        } else {
            return false;
        }
    }

    /**
     * 清空key
     */
    public String flushdb() {
        Jedis jedis = jedisPool.getResource();
        if (jedis != null) {
            try {
                return jedis.flushDB();
            } catch (Exception e) {
                System.err.println("redis client error:" + e);
                return "";
            } finally {
                jedis.close();
            }
        } else {
            return "";
        }
    }


    /**
     * 集合中加入数据
     *
     * @param key        key
     * @param value      值
     * @param expireTime 过期时间
     * @return 返回是否加入成功
     */
    public boolean sadd(String key, String value, int expireTime) {
        Jedis jedis = jedisPool.getResource();
        if (jedis != null) {
            try {
                jedis.sadd(key, value);
                jedis.expire(key, expireTime);
                return true;
            } catch (Exception e) {
                System.err.println("sadd error:" + e);
                return false;
            } finally {
                jedis.close();
            }
        } else {
            return false;
        }
    }

    /**
     * 获取 key 集合的长度信息
     *
     * @param key key信息
     * @return key的集合的长度
     */
    public long scard(String key) {
        Jedis jedis = jedisPool.getResource();
        if (jedis != null) {
            try {
                return jedis.scard(key);
            } catch (Exception e) {
                System.err.println("scard error:" + e);
                return -1L;
            } finally {
                jedis.close();
            }
        } else {
            return -1L;
        }
    }


    /**
     * 查询redis 中集合的数量
     *
     * @param key redis Key
     * @return 集合的数量
     */
    public int getSetValueSize(String key) {
        Jedis jedis = jedisPool.getResource();
        if (jedis != null) {
            try {
                return jedis.smembers(key).size();
            } catch (Exception e) {
                System.err.println(e);
                return -1;
            } finally {
                jedis.close();
            }
        } else {
            return -1;
        }
    }


    /**
     * delete 删除key
     *
     * @param key 需要删除的key
     * @return 删除结果说明
     */
    public boolean delete(String key) {
        Jedis jedis = jedisPool.getResource();
        if (jedis != null) {
            try {
                jedis.del(key);
                return true;
            } catch (Exception e) {
                System.err.println(e);
                return false;
            } finally {
                jedis.close();
            }
        } else {
            return false;
        }
    }

    /**
     * 获取返回结果值
     *
     * @param key key
     * @return 返回结果
     */
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        if (jedis != null) {
            try {
                return jedis.get(key);
            } catch (Exception e) {
                System.err.println(e);
                return null;
            } finally {
                jedis.close();
            }
        } else {
            return null;
        }
    }

    /**
     * 设置 key value  并设置过期时间
     *
     * @param key        key
     * @param value      value
     * @param expireTime 过期时间
     * @return 是否设置成功
     */
    public boolean set(String key, String value, int expireTime) {
        Jedis jedis = jedisPool.getResource();
        if (jedis != null) {
            try {
                jedis.setex(key, expireTime, value);
                return true;
            } catch (Exception e) {
                System.err.println(e);
                return false;
            } finally {
                jedis.close();
            }
        } else {
            return false;
        }
    }


    public boolean hSet(String key, String fieldName, String value) {
        Jedis jedis = jedisPool.getResource();
        if (jedis != null) {
            try {
                jedis.hset(key, fieldName, value);
                return true;
            } catch (Exception e) {
                System.err.println("redis client error:" + e);
                return false;
            } finally {
                jedis.close();
            }
        } else {
            return false;
        }
    }


    public boolean hSetAndEx(String key, String fieldName, String value, int expireTime) {

        Jedis jedis = jedisPool.getResource();
        if (jedis != null) {
            try {
                jedis.hset(key, fieldName, value);
                jedis.expire(key, expireTime);
                return true;
            } catch (Exception e) {
                System.err.println("redis client error:" + e);
                return false;
            } finally {
                jedis.close();
            }
        } else {
            return false;
        }
    }


    public String hGet(String key, String fieldName) {

        Jedis jedis = jedisPool.getResource();
        if (jedis != null) {
            try {
                return jedis.hget(key, fieldName);
            } catch (Exception e) {
                System.err.println("redis client error:" + e);
                return null;
            } finally {
                jedis.close();
            }
        } else {
            return null;
        }
    }

    public Long hDel(String key, String fieldName) {

        Jedis jedis = jedisPool.getResource();
        if (jedis != null) {
            try {
                return jedis.hdel(key, fieldName);
            } catch (Exception e) {
                System.err.println("redis client error:" + e);
                return -1L;
            } finally {
                jedis.close();
            }
        } else {
            return -1L;
        }
    }


    public boolean hInc(String key, String fieldName, int seconds) {
        Jedis jedis = jedisPool.getResource();
        if (jedis != null) {
            try {
                jedis.hincrBy(key, fieldName, 1);
                jedis.expire(key, seconds);
                return true;
            } catch (Exception e) {
                System.err.println("redis client error:" + e);
                return false;
            } finally {
                jedis.close();
            }
        } else {
            return false;
        }
    }


    public boolean hIncAndSet(String key, String incName, String fieldName, String fieldValue, int seconds) {
        Jedis jedis = jedisPool.getResource();
        if (jedis != null) {
            try {
                jedis.hset(key, fieldName, fieldValue);
                jedis.hincrBy(key, incName, 1);
                jedis.expire(key, seconds);
                return true;
            } catch (Exception e) {
                System.err.println("redis client error:" + e);
                return false;
            } finally {
                jedis.close();
            }
        } else {
            return false;
        }
    }

    public long sadd(String key, String[] members) {
        Jedis jedis = jedisPool.getResource();
        if (jedis != null) {
            try {
                jedis.del(key);
                return jedis.sadd(key, members);
            } catch (Exception e) {
                System.err.println("redis client error:" + e);
                return -1L;
            } finally {
                jedis.close();
            }
        } else {
            return -1L;
        }
    }

    public boolean sismember(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        if (jedis != null) {
            try {
                return jedis.sismember(key, value);
            } catch (Exception e) {
                System.err.println("redis client error:" + e);
                return true;
            } finally {
                jedis.close();
            }
        } else {
            return true;
        }
    }


}
