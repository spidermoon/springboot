package com.example.springboot.util;

import redis.clients.jedis.Jedis;

import java.util.Collections;

public class RedisUtil {


    /**
     * 尝试获取分布式锁
     * @param jedis         Redis客户端
     * @param lockKey       锁
     * @param requestId     请求标识
     * @param expireTime    超期时间
     * @return              是否获取成功
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime){
        String result = jedis.set(lockKey, requestId, "NX", "PX", expireTime);
        if ("OK".equals(result)){
            return true;
        }
        return false;
    }

    /**
     * 释放分布式锁
     * @param jedis       Redis客户端
     * @param lockKey     锁
     * @param requestId   请求标识
     * @return            是否释放成功
     */
    public static boolean releaseDistributeLock(Jedis jedis, String lockKey, String requestId){
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if ("OK".equals(result)){
            return true;
        }
        return false;
    }

}
