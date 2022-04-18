package com.example.httpproxy;

import android.app.Application;

import com.example.httpproxy.http.HttpImpl_Me;
import com.example.httpproxy.http.HttpImpl_OkHttp;
import com.example.httpproxy.http.HttpImpl_Volley;
import com.example.httpproxy.http.HttpManager;

public class HApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        HttpManager.init(new HttpImpl_Me(this));
//        HttpManager.init(new HttpImpl_OkHttp(this));
        HttpManager.init(new HttpImpl_Volley(this));
    }
}
