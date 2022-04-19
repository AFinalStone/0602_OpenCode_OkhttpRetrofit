package com.example.opencode.http.interfaces;

public interface ResponseCallBack<T> {
    /**
     * 是否显示通用的异常，例如：网络连接不上、无网络连接等等
     *
     * @return
     */
    boolean isToastCommError();

    /**
     * 是否显示业务异常，某些业务异常需要特殊处理时，需要重写该方法，返回false
     *
     * @return
     */
    boolean isToastBusinessError();

    /**
     * 请求成功返回
     *
     * @param t
     */
    void handleResult(T t);

    /**
     * 请求出现异常回调
     *
     * @param t    异常信息
     * @param code 错误码
     * @param msg  错误消息
     */
    void handleException(Throwable t, String code, String msg);
}
