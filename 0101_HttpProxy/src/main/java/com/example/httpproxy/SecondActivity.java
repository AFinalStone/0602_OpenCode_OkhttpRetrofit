package com.example.httpproxy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.httpproxy.bean.APerson;
import com.example.httpproxy.http.FacadeNetwork;
import com.example.httpproxy.http.HttpManager;
import com.google.gson.Gson;

import java.util.HashMap;

public class SecondActivity extends AppCompatActivity {

    public static String TAG = "SecondActivity========";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.btn_get_url).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test02();
            }
        });
    }

    private void test01() {
        APerson aPerson = new APerson("小明", 15, 20180512);
        String response = new Gson().toJson(aPerson);
        FacadeNetwork.getInstance(this).get("https://www.baidu.com", new HashMap<>(), response, new FacadeNetwork.CallBack<APerson>() {
            @Override
            public void onSuccess(APerson s) {
                Log.d(TAG, String.valueOf(s) + "ThreadId=" + Thread.currentThread());
            }

            @Override
            public void onFailed(String msg) {
                Log.e(TAG, String.valueOf(msg) + "ThreadId=" + Thread.currentThread());
            }
        });
    }

    private void test02() {
        APerson aPerson = new APerson("小明", 15, 20180512);
        String response = new Gson().toJson(aPerson);
        HttpManager.getInstance().get("https://www.baidu.com", new HashMap<>(), response, new FacadeNetwork.CallBack<String>() {
            @Override
            public void onSuccess(String s) {
                Log.d(TAG, String.valueOf(s) + "ThreadId=" + Thread.currentThread());
            }

            @Override
            public void onFailed(String msg) {
                Log.e(TAG, String.valueOf(msg) + "ThreadId=" + Thread.currentThread());
            }
        });
    }
}