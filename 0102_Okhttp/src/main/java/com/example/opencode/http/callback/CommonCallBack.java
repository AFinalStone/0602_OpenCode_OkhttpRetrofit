package com.example.opencode.http.callback;

import android.text.TextUtils;

import com.example.opencode.http.exception.NoNetworkException;
import com.example.opencode.http.interfaces.ResponseCallBack;
import com.example.opencode.http.utils.GenericUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class CommonCallBack<T> implements Callback, ResponseCallBack<T> {

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        ResponseBody body = response.body();
        String result = body.string();
        Type type = GenericUtil.analysisInterfaceInfo(this);
        if (type instanceof Class && TextUtils.equals(((Class<?>) type).getName(), String.class.getName())) {
            handleResult((T) result);
            return;
        }
        try {
            T t = new Gson().fromJson(result, type);
            handleResult(t);
        } catch (Exception es) {
        }
    }

    private boolean handleFailed(IOException exception) {
        if (exception instanceof NoNetworkException) {

        }
        if (exception instanceof NoNetworkException) {

        }
        return false;
    }

    @Override
    public boolean isToastCommError() {
        return false;
    }

    @Override
    public boolean isToastBusinessError() {
        return false;
    }


}
