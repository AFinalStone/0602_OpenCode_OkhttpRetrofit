package com.example.httpproxy.http;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.example.httpproxy.utils.GenericUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpImpl_OkHttp implements IHttp {

    private static HttpImpl_OkHttp _instance;

    public static HttpImpl_OkHttp getInstance(Context context) {
        if (_instance == null) {
            synchronized (HttpImpl_OkHttp.class) {
                if (_instance == null) {
                    _instance = new HttpImpl_OkHttp(context);
                }
            }
        }
        return _instance;
    }

    private Context mContext;
    private OkHttpClient mOkHttpClient;

    public HttpImpl_OkHttp(Context context) {
        mOkHttpClient = new OkHttpClient();
    }

    @Override
    public <T> void get(String url, Map<String, String> paras, String mockResponse, FacadeNetwork.CallBack<T> callback) {
        okhttp3.Request getRequest = new okhttp3.Request.Builder()
                .url(url)
                .get()
                .build();
        mOkHttpClient.newCall(getRequest).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (callback != null) {
                    callback.onFailed(e.getMessage());
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                ResponseBody body = response.body();
                String result = body.string();
                Type type = GenericUtil.analysisInterfaceInfo(callback);
                if (type instanceof Class && TextUtils.equals(((Class<?>) type).getName(), String.class.getName())) {
                    callback.onSuccess((T) result);
                    return;
                }
                try {
                    T t = new Gson().fromJson(result, type);
                    callback.onSuccess(t);
                } catch (Exception es) {
                    callback.onFailed("解析失败");
                }

            }
        });
    }

    @Override
    public <T> void post(String url, Map<String, String> paras, String mockResponse, FacadeNetwork.CallBack<T> callback) {

    }
}
