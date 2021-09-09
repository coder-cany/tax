package com.hs.order.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.*;

import java.io.IOException;

/**
 * RedisService Class
 */
@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;

    ObjectMapper objectMapper = new ObjectMapper();

    public <T> T get(String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = jedis.get(key);
            if (str == null) {
                return null;
            }
            T t = null;
            try {
                t = stringToBean(str, clazz);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return t;
        } finally {
            returnToPool(jedis);
        }
    }

    public String get(String key) {
        return get(key, String.class);
    }

    public <T> boolean set(String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String v = null;
            try {
                v = beanToString(value);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            if (v == null || v.length() == 0) {
                return false;
            }
            jedis.set(key, v);
            return true;
        } finally {
            returnToPool(jedis);
        }
    }


    public boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Boolean result = jedis.exists(key);
            return result;
        } finally {
            returnToPool(jedis);
        }
    }


    public boolean expire(String key, int second) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis.exists(key)) {
                jedis.expire(key, second);
                return false;
            } else {
                return true;
            }

        } finally {
            returnToPool(jedis);
        }
    }

    public boolean delete(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            long result = jedis.del(key);
            return result > 0;
        } finally {
            returnToPool(jedis);
        }
    }


    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    //-----------------------------JSON 字符串和对象间的相互转换-----------------------------------------

    public <T> T stringToBean(String str, Class<T> clazz) throws IOException {
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        }
        if (clazz == String.class) {
            return (T) str;
        }
        if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return objectMapper.readValue(str, clazz);
        }

    }

    public <T> String beanToString(T t) throws JsonProcessingException {
        if (t == null) {
            return null;
        }
        Class<?> clzz = t.getClass();
        if (clzz == int.class || clzz == Integer.class) {
            return "" + t;
        }
        if (clzz == String.class) {
            return (String) t;
        }
        if (clzz == long.class || clzz == Long.class) {
            return "" + t;
        }

        return objectMapper.writeValueAsString(t);
    }

}
