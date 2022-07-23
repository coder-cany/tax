package com.hs.user.util;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class Md5Utils extends DigestUtils {
    public static String md5DigestAsHex(int id, String password) {
        String s = Integer.toString(id);
        String pre = DigestUtils.md5DigestAsHex(s.getBytes(StandardCharsets.UTF_8));
        password += pre;
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
