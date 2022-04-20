package com.afs.okhttpretrofit.http.interfaces;

import java.io.IOException;
import java.util.Map;

public interface IHttp {

    /**
     * 同步请求
     *
     * @param url
     * @param paras
     */
    <T> T get_sync(String url, Map<String, String> paras) throws IOException;

    <T> void get(String url, Map<String, String> paras, HttpCallBack<T> callback);

    <T> void post(String url, Map<String, String> paras, HttpCallBack<T> callback);
}
