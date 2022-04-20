package com.afs.okhttpretrofit.http.interfaces;

public interface UICallBack<T> {
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


}
