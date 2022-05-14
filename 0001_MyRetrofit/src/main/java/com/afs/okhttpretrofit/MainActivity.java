package com.afs.okhttpretrofit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.afs.okhttpretrofit.api.MainModuleService;
import com.afs.okhttpretrofit.bean.LoginBean;
import com.afs.okhttpretrofit.http.retrofit.RetrofitManager;


public class MainActivity extends AppCompatActivity implements BaseView {
    public static String TAG = "MainActivity========";

    private TextView mTvContent;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvContent = findViewById(R.id.tv_content);
        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingView();
                new Thread() {
                    @Override
                    public void run() {
                        LoginBean loginBean = RetrofitManager.getRetrofit().create(MainModuleService.class).login();
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                hideLoadingView();
                                mTvContent.setText(loginBean.toString());
                            }
                        });
                    }
                }.start();

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

    @Override
    public void showLoadingView() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        if (progressDialog.isShowing()) {
            return;
        }
        progressDialog.show();
    }


    @Override
    public void hideLoadingView() {
        if (progressDialog != null) {
            progressDialog.hide();
        }
    }
}
