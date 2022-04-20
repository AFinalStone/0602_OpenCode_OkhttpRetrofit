package com.afs.okhttpretrofit;

public interface BaseView {
    /**
     * 显示Toast信息
     *
     * @param msg
     */
    void toastMessage(CharSequence msg);

    /**
     * 显示Toast信息
     *
     * @param resId 资源id
     */
    void toastMessage(int resId);


    /**
     * 显示浮动加载进度条
     */
    void showLoadingView();
    
    /**
     * 停止浮动加载进度条
     */
    void hideLoadingView();
}
