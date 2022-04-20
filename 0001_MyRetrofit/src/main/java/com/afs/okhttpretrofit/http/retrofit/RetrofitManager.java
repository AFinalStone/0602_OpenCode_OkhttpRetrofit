package com.afs.okhttpretrofit.http.retrofit;

import com.afs.okhttpretrofit.http.HttpManager;

public class RetrofitManager {

    private static IRetrofit retrofit;

    public static IRetrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (RetrofitManager.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder(HttpManager.getInstance()).builder();
                }
            }
        }
        return retrofit;
    }
}
