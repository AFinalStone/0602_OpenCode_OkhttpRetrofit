package com.example.httpproxy.http;

import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public class FacadeNetwork {

    public interface CallBack<T> {

        void onSuccess(T t);

        void onFailed(String msg);

    }

    private static RequestQueue mQueue;
    private static FacadeNetwork _instance;

    public FacadeNetwork(Context context) {
        mQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static FacadeNetwork getInstance(Context context) {
        if (_instance == null) {
            synchronized (FacadeNetwork.class) {
                if (_instance == null) {
                    _instance = new FacadeNetwork(context);
                }
            }
        }
        return _instance;
    }

    public void get(String url, Map<String, String> paras, CallBack<String> callBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (callBack != null) {
                    callBack.onSuccess(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (callBack != null) {
                    callBack.onFailed(error.getMessage());
                }
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return paras;
            }
        };
        mQueue.add(stringRequest);
    }
}
