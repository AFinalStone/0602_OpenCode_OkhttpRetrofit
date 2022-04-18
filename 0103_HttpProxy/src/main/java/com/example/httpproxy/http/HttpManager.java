package com.example.httpproxy.http;

import java.util.Map;

public class HttpManager implements IHttp {

    private static HttpManager _instance;
    private static IHttp _iHttp;

    public static void init(IHttp http) {
        _iHttp = http;
    }

    public static HttpManager getInstance() {
        if (_instance == null) {
            synchronized (HttpManager.class) {
                if (_instance == null) {
                    _instance = new HttpManager();
                }
            }
        }
        return _instance;
    }

    @Override
    public <T> void get(String url, Map<String, String> paras, String mockResponse, FacadeNetwork.CallBack<T> callback) {
        _iHttp.get(url, paras, mockResponse, callback);
    }
}
