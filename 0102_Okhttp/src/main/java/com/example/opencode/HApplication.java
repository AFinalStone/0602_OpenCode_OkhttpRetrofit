package com.example.opencode;

import android.app.Application;

import com.example.opencode.http.HttpImpl_Okhttp;
import com.example.opencode.http.HttpManager;
import com.example.opencode.http.HttpRequestConfig;

public class HApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initNetServer();
    }

    private void initNetServer() {
        HttpRequestConfig.Builder builder = new HttpRequestConfig.Builder();
        builder.setDebug(BuildConfig.DEBUG);
        builder.setPackageValue(BuildConfig.APPLICATION_ID);
        builder.setCacheFilePath(getCacheDir().getPath() + System.getProperty("file.separator") + "https");
        builder.setCacheFileSize(10 * 1024);
        HttpManager.init(new HttpImpl_Okhttp(builder.build()));
    }

}
