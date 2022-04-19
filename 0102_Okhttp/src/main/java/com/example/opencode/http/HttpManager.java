package com.example.opencode.http;

import com.example.opencode.http.interfaces.ViewCallBack;
import com.example.opencode.http.interfaces.IHttp;

import java.util.Map;

public class HttpManager implements IHttp {

    private static HttpManager INSTANCE;

    public static void init(IHttp iHttp) {
        if (INSTANCE == null) {
            INSTANCE = new HttpManager(iHttp);
        }
    }

    public static HttpManager getInstance() {
        if (INSTANCE == null) {
            throw new RuntimeException("Must call init() method before call this.");
        }
        return INSTANCE;
    }

    private IHttp mHttpImpl;

    public HttpManager(IHttp httpImpl) {
        this.mHttpImpl = httpImpl;
    }

    @Override
    public <T> void get(String url, Map<String, String> paras, ViewCallBack<T> callback) {
        mHttpImpl.get(url, paras, callback);
    }

    @Override
    public <T> void post(String url, Map<String, String> paras, ViewCallBack<T> callback) {
        mHttpImpl.post(url, paras, callback);
    }
}
