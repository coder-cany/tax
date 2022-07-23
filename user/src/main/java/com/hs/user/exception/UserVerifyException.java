package com.hs.user.exception;

public class UserVerifyException extends RuntimeException {
    private int code = 2100;

    public UserVerifyException() {
    }

    public UserVerifyException(String msg) {
        super(msg);
    }

    public UserVerifyException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
