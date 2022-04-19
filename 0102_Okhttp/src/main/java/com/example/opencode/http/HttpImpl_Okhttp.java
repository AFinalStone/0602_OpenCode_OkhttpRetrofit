package com.example.opencode.http;

import com.example.opencode.http.Interceptor.RequestInterceptor;
import com.example.opencode.http.Interceptor.ResponseInterceptor;
import com.example.opencode.http.callback.CommonCallBack;
import com.example.opencode.http.interfaces.IHttp;
import com.example.opencode.http.interfaces.ViewCallBack;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class HttpImpl_Okhttp implements IHttp {

    OkHttpClient mOkHttpClient;

    public HttpImpl_Okhttp(HttpRequestConfig config) {
        OkHttpClient.Builder builder = new OkHttpClient
                .Builder()
                //创建缓存目录
                .cache(new Cache(new File(config.getCacheFilePath()), config.getCacheFileSize()))
                //连接超时时间
                .connectTimeout(20, TimeUnit.SECONDS)
                //读取超时时间
                .readTimeout(20, TimeUnit.SECONDS)
                //写入超时时间
                .writeTimeout(20, TimeUnit.SECONDS);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(new RequestInterceptor(config));
        builder.addInterceptor(new ResponseInterceptor(config));
        if (config.isDebug()) {
            builder.addInterceptor(loggingInterceptor);
        }
        builder.retryOnConnectionFailure(true);
        mOkHttpClient = builder.build();
    }


    @Override
    public <T> void get(String url, Map<String, String> paras, ViewCallBack<T> callback) {
        Request request = new Request.Builder().url(url).get().build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonCallBack() {
            @Override
            public void handleResult(Object o) {

            }

            @Override
            public void handleException(Throwable t, String code, String msg) {

            }
        });
    }

    @Override
    public <T> void post(String url, Map<String, String> paras, ViewCallBack<T> callback) {
        FormBody.Builder builder = new FormBody.Builder();
        if (paras != null && paras.size() > 0) {
            for (Map.Entry<String, String> entry : paras.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }


}
