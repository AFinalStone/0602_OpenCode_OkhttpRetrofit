package com.example.opencode.http.interfaces;

public interface ViewCallBack<T> {

    void onSuccess(T t);

    void onFailed(String msg);

}