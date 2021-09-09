package com.hs.order.utils;

import com.hs.order.redis.RedisService;
import org.springframework.util.StringUtils;

public class CheckToken {
    public static Boolean check(RedisService template, String token, int account) {
        if (StringUtils.hasLength(token) && template.exists(token) && template.get(token).equals(account + "")) {
            return true;
        }
        return false;
    }
}
