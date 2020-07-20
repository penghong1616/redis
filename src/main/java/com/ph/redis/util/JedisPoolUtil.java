package com.ph.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
   private static  JedisPool jedisPool=null;
    static {
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(30);
        jedisPoolConfig.setMaxWaitMillis(10000);
        jedisPoolConfig.setMinIdle(10);
        jedisPoolConfig.setMaxTotal(30);
        jedisPool=new JedisPool(jedisPoolConfig,"master",6379,1000,"123123r@",0);

    }
    public  static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
