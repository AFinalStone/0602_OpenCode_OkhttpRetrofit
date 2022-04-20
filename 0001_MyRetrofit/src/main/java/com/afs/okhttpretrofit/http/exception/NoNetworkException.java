package com.afs.okhttpretrofit.http.exception;


import com.afs.okhttpretrofit.http.exception.base.ConnectException;

public class NoNetworkException extends ConnectException {
    public NoNetworkException(String message) {
        super(message);
    }
}
