package com.IdolTicketing.exception;

public class CUserNotFoundException extends RuntimeException {

    public CUserNotFoundException(int code,Throwable t) {
        super(String.valueOf(code));
    }
    public CUserNotFoundException(int code){
        super(String.valueOf(code));
    }
}
