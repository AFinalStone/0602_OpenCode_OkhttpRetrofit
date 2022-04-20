package com.afs.okhttpretrofit.http.retrofit;

public interface IRetrofit {
    <T> T create(Class<T> clazz);
}
