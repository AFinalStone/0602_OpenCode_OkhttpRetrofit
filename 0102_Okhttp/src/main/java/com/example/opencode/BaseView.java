package com.example.opencode;

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
}
