package com.example.httpproxy.http;

import java.util.Map;

public interface IHttp {

    <T> void get(String url, Map<String, String> paras, String mockResponse, FacadeNetwork.CallBack<T> callback);
}
