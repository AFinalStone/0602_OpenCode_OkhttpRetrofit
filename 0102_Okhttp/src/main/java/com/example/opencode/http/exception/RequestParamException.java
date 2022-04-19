package com.example.opencode.http.exception;

import com.example.opencode.http.exception.base.ConnectException;

public class RequestParamException extends ConnectException {
    public RequestParamException(String message) {
        super(message);
    }
}
