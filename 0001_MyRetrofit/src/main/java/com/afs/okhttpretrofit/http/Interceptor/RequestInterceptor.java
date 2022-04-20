package com.afs.okhttpretrofit.http.Interceptor;

import com.afs.okhttpretrofit.http.HttpRequestConfig;
import com.afs.okhttpretrofit.http.exception.NoNetworkException;

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
                .header("deviceid", returnNoNull(config.getDeviceId()))
                .build();
        try {
            return chain.proceed(request);
        } catch (IOException e) {
            throw new NoNetworkException(e.getMessage());
        }
    }

    private String returnNoNull(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        return str;
    }

}
