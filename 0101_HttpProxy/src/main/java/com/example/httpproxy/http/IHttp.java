package com.example.httpproxy.http;

import java.util.Map;

public interface IHttp {

    <T> void get(String url, Map<String, String> paras, String mockResponse, CallBack<T> callback);

    <T> void post(String url, Map<String, String> paras, String mockResponse, CallBack<T> callback);
}
