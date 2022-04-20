package com.afs.okhttpretrofit.http.exception.base;

import java.io.IOException;

public class ConnectException extends IOException {
    private String code;

    public ConnectException(String message) {
        super(message);
    }

    public ConnectException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
