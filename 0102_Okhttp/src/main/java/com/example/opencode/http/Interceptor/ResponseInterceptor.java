package com.example.opencode.http.Interceptor;

import com.example.opencode.http.HttpRequestConfig;
import com.example.opencode.http.exception.RequestParamException;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ResponseInterceptor implements Interceptor {

    private HttpRequestConfig config;

    public ResponseInterceptor(HttpRequestConfig config) {
        this.config = config;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = null;
        response = chain.proceed(chain.request());
        if (!response.isSuccessful()) {
            throw new RequestParamException(response.message());
        }
        return response;
    }
}
