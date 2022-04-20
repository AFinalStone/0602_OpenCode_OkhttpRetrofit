package com.afs.okhttpretrofit.http;


import com.afs.okhttpretrofit.http.interfaces.HttpCallBack;
import com.afs.okhttpretrofit.http.interfaces.IHttp;

import java.io.IOException;
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
    public <T> T get_sync(String url, Map<String, String> paras) throws IOException {
        return mHttpImpl.get_sync(url, paras);
    }

    @Override
    public <T> void get(String url, Map<String, String> paras, HttpCallBack<T> callback) {
        mHttpImpl.get(url, paras, callback);
    }

    @Override
    public <T> void post(String url, Map<String, String> paras, HttpCallBack<T> callback) {
        mHttpImpl.post(url, paras, callback);
    }
}
