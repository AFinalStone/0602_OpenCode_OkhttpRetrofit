package com.example.opencode.http.Interceptor;

import com.example.opencode.http.HttpRequestConfig;
import com.example.opencode.http.exception.NoNetworkException;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    private HttpRequestConfig config;

    public RequestInterceptor(HttpRequestConfig config) {
        this.config = config;
    }

    @Override
    public Response intercept(Chain chain) throws NoNetworkException {
        Request request = chain.request()
                .newBuilder()
                .header("deviceid", config.getDeviceId())
                .build();
        try {
            return chain.proceed(request);
        } catch (IOException e) {
            throw new NoNetworkException("请求参数异常");
        }
    }
}
