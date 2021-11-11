package com.hs.order.utils;

import com.hs.order.redis.RedisService;
import org.springframework.util.StringUtils;

public class TokenUtil {
    public static Boolean check(RedisService template, String token, int account) {
        return StringUtils.hasLength(token) && template.exists(token) && template.get(token).equals(account + "");
    }
}
