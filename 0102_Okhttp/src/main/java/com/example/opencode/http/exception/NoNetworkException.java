package com.example.opencode.http.exception;

import com.example.opencode.http.exception.base.ConnectException;

public class NoNetworkException extends ConnectException {
    public NoNetworkException(String message) {
        super(message);
    }
}
