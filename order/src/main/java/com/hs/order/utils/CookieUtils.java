package com.hs.order.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {
    public static String getCookieValueByName(HttpServletRequest request, String cookieName) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList != null) {
            for (Cookie cookie : cookieList) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static Cookie getCookieByName(HttpServletRequest request, String cookieName) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList != null) {
            for (Cookie cookie : cookieList) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
