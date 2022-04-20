package com.afs.okhttpretrofit.http.callback;


import com.afs.okhttpretrofit.BaseView;
import com.afs.okhttpretrofit.http.interfaces.HttpCallBack;

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
