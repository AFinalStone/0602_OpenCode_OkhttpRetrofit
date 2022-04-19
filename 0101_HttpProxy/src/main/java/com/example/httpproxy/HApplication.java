package com.example.httpproxy;

import android.app.Application;
import android.os.StrictMode;

import com.example.httpproxy.http.HttpImpl_HttpUrlConnection;
import com.example.httpproxy.http.HttpImpl_Volley;
import com.example.httpproxy.http.HttpManager;

public class HApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        if (BuildConfig.DEBUG) {
//            enabledStrictMode();
//        }
//        HttpManager.init(new HttpImpl_Me(this));
//        HttpManager.init(new HttpImpl_OkHttp(this));
//        HttpManager.init(new HttpImpl_Volley(this));
        HttpManager.init(new HttpImpl_HttpUrlConnection(this));
    }

    private void enabledStrictMode() {
        //开启Thread策略模式
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectNetwork()//监测主线程使用网络io
                .detectCustomSlowCalls()//监测自定义运行缓慢函数
                .detectDiskReads() // 检测在UI线程读磁盘操作
                .detectDiskWrites() // 检测在UI线程写磁盘操作
                .penaltyLog() //写入日志
                .penaltyDialog()//监测到上述状况时弹出对话框
                .build());
        //开启VM策略模式
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects()//监测sqlite泄露
                .detectLeakedClosableObjects()//监测没有关闭IO对象
                .setClassInstanceLimit(MainActivity.class, 1) // 设置某个类的同时处于内存中的实例上限，可以协助检查内存泄露
                .detectActivityLeaks()
                .penaltyLog()//写入日志
                .penaltyDeath()//出现上述情况异常终止
                .build());
    }
}
