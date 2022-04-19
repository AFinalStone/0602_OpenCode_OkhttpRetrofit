package com.example.httpproxy.http;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.httpproxy.utils.GenericUtil;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Map;

public class HttpImpl_Volley implements IHttp {

    private static HttpImpl_Volley _instance;


    public static HttpImpl_Volley getInstance(Context context) {
        if (_instance == null) {
            synchronized (HttpImpl_Volley.class) {
                if (_instance == null) {
                    _instance = new HttpImpl_Volley(context);
                }
            }
        }
        return _instance;
    }

    private RequestQueue mQueue;
    private Context mContext;

    public HttpImpl_Volley(Context context) {
        mContext = context.getApplicationContext();
        mQueue = Volley.newRequestQueue(mContext);
    }

    @Override
    public <T> void get(String url, Map<String, String> paras, String mockResponse, FacadeNetwork.CallBack<T> callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // 返回实现的接口
                Type type = GenericUtil.analysisInterfaceInfo(callback);
                if (type instanceof Class && TextUtils.equals(((Class<?>) type).getName(), String.class.getName())) {
                    callback.onSuccess((T) response);
                    return;
                }
                try {
                    T t = new Gson().fromJson(response, type);
                    callback.onSuccess(t);
                } catch (Exception e) {
                    callback.onFailed("解析失败");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (callback != null) {
                    callback.onFailed(error.getMessage());
                }
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return paras;
            }
        };
        mQueue.add(stringRequest);
    }

    @Override
    public <T> void post(String url, Map<String, String> paras, String mockResponse, FacadeNetwork.CallBack<T> callback) {

    }
}
