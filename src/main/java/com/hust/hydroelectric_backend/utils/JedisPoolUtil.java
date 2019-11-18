package com.hust.hydroelectric_backend.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/18 19:05
 */
public class JedisPoolUtil {

    private static String HOST = "222.20.81.50";

    private static int PORT = 6379;

    private JedisPoolUtil(){

    }

    private static volatile JedisPool jedisPool = null;

    private static JedisPool getJedisPoolInstance(){
        if(jedisPool == null){
            synchronized (JedisPoolUtil.class){
                if(jedisPool == null){
                    JedisPoolConfig config = new JedisPoolConfig();
                    config.setMaxTotal(1000);           // 最大连接数
                    config.setMaxIdle(32);              // 最大空闲连接数
                    config.setMaxWaitMillis(100*1000);  // 最大等待时间
                    config.setTestOnBorrow(true);       // 检查连接可用性, 确保获取的redis实例可用
                    jedisPool = new JedisPool(config, HOST, PORT);
                }
            }
        }
        return jedisPool;
    }

    public static Jedis getJedisInstance(){
        return getJedisPoolInstance().getResource();
    }
}
