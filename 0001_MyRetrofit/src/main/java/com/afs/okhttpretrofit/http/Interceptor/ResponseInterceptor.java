package com.afs.okhttpretrofit.http.Interceptor;


import com.afs.okhttpretrofit.http.HttpRequestConfig;
import com.afs.okhttpretrofit.http.exception.ResponseException;

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
            throw new ResponseException(response.message());
        }
        return response;
    }
}
