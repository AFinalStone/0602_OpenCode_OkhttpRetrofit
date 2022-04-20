package com.example.opencode;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.opencode.bean.BaseRespBean;
import com.example.opencode.bean.LauncherBean;
import com.example.opencode.http.HttpManager;
import com.example.opencode.http.callback.ViewCallBackImpl;

public class MainActivity extends AppCompatActivity implements BaseView {
    public static String TAG = "MainActivity========";

    private TextView mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvContent = findViewById(R.id.tv_content);
        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpManager.getInstance().get("http://dev.usapi.miyuelive.cn:83/v1/init/start", null, new ViewCallBackImpl<BaseRespBean<LauncherBean>, MainActivity>(MainActivity.this) {
                    @Override
                    public void onSuccess(BaseRespBean<LauncherBean> s) {
                        LauncherBean launcherBean = s.getData();
                        mTvContent.setText(launcherBean.toString());
                    }

                    @Override
                    public boolean isToastCommError() {
                        return true;
                    }

                    @Override
                    public boolean isToastBusinessError() {
                        return true;
                    }
                });
            }
        });
        findViewById(R.id.btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public void toastMessage(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastMessage(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }
}
