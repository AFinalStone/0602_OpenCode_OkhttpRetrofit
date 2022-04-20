package com.example.opencode.http.callback;

import com.example.opencode.BaseView;
import com.example.opencode.http.interfaces.HttpCallBack;

public abstract class ViewCallBackImpl<T, V extends BaseView> implements HttpCallBack<T> {

    private V view;

    public ViewCallBackImpl(V view) {
        this.view = view;
    }

    public boolean isToastCommError() {
        return false;
    }

    public boolean isToastBusinessError() {
        return false;
    }

    @Override
    public void onFailed(Throwable t, String code, String msg) {
        if (null == code) {
            if (isToastCommError()) {
                view.toastMessage(msg);
            }
        } else {
            if (isToastBusinessError()) {
                view.toastMessage(msg);
            }
        }
    }

}
