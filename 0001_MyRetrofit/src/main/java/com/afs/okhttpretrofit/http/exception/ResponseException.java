package com.afs.okhttpretrofit.http.exception;


import com.afs.okhttpretrofit.http.exception.base.ConnectException;

public class ResponseException extends ConnectException {
    public ResponseException(String message) {
        super(message);
    }

    public ResponseException(String message, String code) {
        super(message, code);
    }
}
