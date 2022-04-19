package com.example.httpproxy.http;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.httpproxy.utils.GenericUtil;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Map;

public class FacadeNetwork {


    private static RequestQueue mQueue;
    private static FacadeNetwork _instance;

    public FacadeNetwork(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }

    public static FacadeNetwork getInstance(Context context) {
        if (_instance == null) {
            synchronized (FacadeNetwork.class) {
                if (_instance == null) {
                    _instance = new FacadeNetwork(context.getApplicationContext());
                }
            }
        }
        return _instance;
    }

    /**
     * 模拟get网络请求
     *
     * @param mockResponse
     * @param callback
     * @param <T>
     */
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
}
