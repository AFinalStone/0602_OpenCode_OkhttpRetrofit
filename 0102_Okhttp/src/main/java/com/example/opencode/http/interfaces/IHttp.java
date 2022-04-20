package com.example.opencode.http.interfaces;

import java.util.Map;

public interface IHttp {

    <T> void get(String url, Map<String, String> paras, HttpCallBack<T> callback);

    <T> void post(String url, Map<String, String> paras, HttpCallBack<T> callback);
}
