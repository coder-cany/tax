package com.hs.order.exception;

public class SimpleException extends RuntimeException {
    public SimpleException(String msg) {
        super(msg);
    }

    public String getMessage() {
        return super.getMessage();
    }
}
