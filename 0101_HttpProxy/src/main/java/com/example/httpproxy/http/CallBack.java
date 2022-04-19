package com.example.httpproxy.http;

public interface CallBack<T> {

        void onSuccess(T t);

        void onFailed(String msg);

    }