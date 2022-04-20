package com.afs.okhttpretrofit.http.interfaces;

public interface HttpCallBack<T> {
    /**
     * 请求成功返回
     *
     * @param t
     */
    void onSuccess(T t);


    /**
     * 请求出现异常回调
     *
     * @param t    异常信息
     * @param code 错误码
     * @param msg  错误消息
     */
    void onFailed(Throwable t, String code, String msg);

}