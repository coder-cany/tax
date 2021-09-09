package com.hs.common.vo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Response<T> {
    private Integer status;
    private String msg;
    private T data;

    public static Response fail() {
        return new Response(400, "数据校验出错", null);
    }

    public static Response fail(String msg) {
        return new Response(400, msg, null);
    }

    public static Response success() {
        return new Response(200, "ok",null);
    }

    public static <T> Response success(T data) {
        return new Response(200, "ok", data);
    }
}
