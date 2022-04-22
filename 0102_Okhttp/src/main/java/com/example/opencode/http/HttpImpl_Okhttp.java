package com.example.opencode.http;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.example.opencode.http.Interceptor.RequestInterceptor;
import com.example.opencode.http.Interceptor.ResponseInterceptor;
import com.example.opencode.http.exception.base.ConnectException;
import com.example.opencode.http.interfaces.HttpCallBack;
import com.example.opencode.http.interfaces.IHttp;
import com.example.opencode.http.utils.GenericUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

public class HttpImpl_Okhttp implements IHttp {

    OkHttpClient mOkHttpClient;

    public HttpImpl_Okhttp(HttpRequestConfig config) {
        OkHttpClient.Builder builder = new OkHttpClient
                .Builder()
//                //创建缓存目录
//                .cache(new Cache(new File(config.getCacheFilePath()), config.getCacheFileSize()))
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
    public <T> void get(String url, Map<String, String> paras, HttpCallBack<T> callback) {
        Request request = new Request.Builder().url(url).get().build();
        Call call = mOkHttpClient.newCall(request);
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        call.cancel();
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callback == null) {
                    return;
                }
                runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        String code = null;
                        String message;
                        if (e instanceof ConnectException) {
                            code = ((ConnectException) e).getCode();
                            message = ((ConnectException) e).getMessage();
                        } else {
                            message = "网络异常";
                        }
                        callback.onFailed(e, code, message);
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (callback == null) {
                    return;
                }
                runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!response.isSuccessful()) {
                            callback.onFailed(null, String.valueOf(response.code()), response.message());
                            return;
                        }
                        try {
                            ResponseBody body = response.body();
                            String result = body.string();
                            Type type = GenericUtil.analysisInterfaceInfo(callback);
                            if (type instanceof Class && TextUtils.equals(((Class<?>) type).getName(), String.class.getName())) {
                                callback.onSuccess((T) result);
                                return;
                            }
                            T t = new Gson().fromJson(result, type);
                            callback.onSuccess(t);
                        } catch (Exception es) {
                            callback.onFailed(es, null, "数据解析失败");
                        }
                    }
                });
            }
        });
    }

    @Override
    public <T> void post(String url, Map<String, String> paras, HttpCallBack<T> callback) {
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
                runOnUIThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                runOnUIThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
    }


    private void runOnUIThread(Runnable runnable) {
        new Handler(Looper.getMainLooper())
                .post(runnable);
    }

}
