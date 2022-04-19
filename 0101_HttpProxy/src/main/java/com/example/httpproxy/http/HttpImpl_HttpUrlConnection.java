package com.example.httpproxy.http;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.example.httpproxy.utils.GenericUtil;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpImpl_HttpUrlConnection implements IHttp {

    private Context mContext;
    Handler handler = new Handler(Looper.getMainLooper());

    public HttpImpl_HttpUrlConnection(Context context) {
        mContext = context.getApplicationContext();
    }

    @Override
    public <T> void get(String url, Map<String, String> paras, String mockResponse, CallBack<T> callback) {
        new Thread() {
            @Override
            public void run() {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.connect();
                    int code = httpURLConnection.getResponseCode();
                    if (code == 200) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        byte[] buffer = new byte[1024];
                        ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
                        int length = -1;
                        while ((length = inputStream.read(buffer)) != -1) {
                            byteArrayInputStream.write(buffer, 0, length);
                        }
                        String response = byteArrayInputStream.toString();
                        byteArrayInputStream.close();
                        Type type = GenericUtil.analysisInterfaceInfo(callback);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (type instanceof Class && TextUtils.equals(((Class<?>) type).getName(), String.class.getName())) {
                                    callback.onSuccess((T) response);
                                    return;
                                }
                                try {
                                    T t = new Gson().fromJson(response, type);
                                    callback.onSuccess(t);
                                } catch (Exception es) {
                                    callback.onFailed("解析失败");
                                }
                            }
                        });

                    } else {
                        callback.onFailed("链接失败");
                    }
                    httpURLConnection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    @Override
    public <T> void post(String url, Map<String, String> paras, String mockResponse, CallBack<T> callback) {

    }
}
