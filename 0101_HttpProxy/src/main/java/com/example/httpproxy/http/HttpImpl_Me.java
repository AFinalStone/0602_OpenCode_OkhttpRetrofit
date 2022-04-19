package com.example.httpproxy.http;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;

import com.example.httpproxy.utils.GenericUtil;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Map;

public class HttpImpl_Me implements IHttp {

    private Context mContext;

    public HttpImpl_Me(Context context) {
        mContext = context.getApplicationContext();
    }

    @Override
    public <T> void get(String url, Map<String, String> paras, String mockResponse, CallBack<T> callback) {
        SystemClock.sleep(2000);
        if (callback != null) {
            // 返回实现的接口
            Type type = GenericUtil.analysisInterfaceInfo(callback);
            if (type instanceof Class && TextUtils.equals(((Class<?>) type).getName(), String.class.getName())) {
                callback.onSuccess((T) mockResponse);
                return;
            }
            try {
                T t = new Gson().fromJson(mockResponse, type);
                callback.onSuccess(t);
            } catch (Exception e) {
                callback.onFailed("解析失败");
            }
        } else {
            // 无泛型参数
            callback.onSuccess((T) mockResponse);
        }
    }

    @Override
    public <T> void post(String url, Map<String, String> paras, String mockResponse, CallBack<T> callback) {

    }
}
