package com.example.httpproxy;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.httpproxy.http.FacadeNetwork;

import java.util.HashMap;

public class SecondActivity extends AppCompatActivity {

    public static String TAG = "SecondActivity========";

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

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
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest("https://www.baidu.com",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        });
        queue.add(stringRequest);
    }

    private void test02() {
        FacadeNetwork.getInstance(this).get("https://www.baidu.com", new HashMap<>(), new FacadeNetwork.CallBack<String>() {
            @Override
            public void onSuccess(String s) {
                Log.d(TAG, s);
            }

            @Override
            public void onFailed(String msg) {
                Log.e(TAG, msg);
            }
        });
    }
}