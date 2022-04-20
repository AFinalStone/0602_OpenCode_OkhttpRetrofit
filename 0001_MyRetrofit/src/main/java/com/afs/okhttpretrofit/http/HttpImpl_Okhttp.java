package com.afs.okhttpretrofit.http;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.afs.okhttpretrofit.bean.LauncherBean;
import com.afs.okhttpretrofit.http.Interceptor.RequestInterceptor;
import com.afs.okhttpretrofit.http.Interceptor.ResponseInterceptor;
import com.afs.okhttpretrofit.http.exception.ResponseException;
import com.afs.okhttpretrofit.http.exception.base.ConnectException;
import com.afs.okhttpretrofit.http.interfaces.HttpCallBack;
import com.afs.okhttpretrofit.http.interfaces.IHttp;
import com.afs.okhttpretrofit.http.utils.GenericUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

public class HttpImpl_Okhttp implements IHttp {

    OkHttpClient mOkHttpClient;

    public HttpImpl_Okhttp(HttpRequestConfig config) {
        OkHttpClient.Builder builder = new OkHttpClient
                .Builder()
//                //创建缓存目录
//                .cache(new Cache(new File(config.getCacheFilePath()), config.getCacheFileSize()))
                //连接超时时间
                .connectTimeout(20, TimeUnit.SECONDS)
                //读取超时时间
                .readTimeout(20, TimeUnit.SECONDS)
                //写入超时时间
                .writeTimeout(20, TimeUnit.SECONDS);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(new RequestInterceptor(config));
        builder.addInterceptor(new ResponseInterceptor(config));
        if (config.isDebug()) {
            builder.addInterceptor(loggingInterceptor);
        }
        builder.retryOnConnectionFailure(true);
        mOkHttpClient = builder.build();
    }


    @Override
    public <T> T get_sync(String url, Map<String, String> paras) throws IOException {
        Request request = new Request.Builder().url(url).get().build();
        Call call = mOkHttpClient.newCall(request);
        Response response = call.execute();
        if (!response.isSuccessful()) {
            throw new ResponseException(response.message(), String.valueOf(response.code()));
        }
        ResponseBody body = response.body();
        LauncherBean launcherBean = new Gson().fromJson("{\"recharge\":{\"hycz\":\"https://p.mx530.cn/static/img/ns1.png\",\"mbcz\":\"https://p.mx530.cn/static/img/ns2.png\",\"mbcztc\":\"https://p.mx530.cn/image/21/04/29/47a96094ca3a77e9e7f1b58a74ec6b9e.png\",\"mzcz\":\"https://p.mx530.cn/image/21/03/15/e0311256d380816a1c644ee268c56e94.png\",\"mzcztc\":\"https://p.mx530.cn/image/21/04/14/d2b1526086ede8701bda9581222c6d32.png\"},\"sf\":{\"jctj\":\"https://p.mx530.cn/image/21/04/16/20286987cea77f08444d845093138e9d.png\",\"tjjyzb\":\"https://p.mx530.cn/image/21/07/16/cfe776ade3d6709d9c5c9e2b60922905.png\"},\"jy\":{\"yjdzh\":\"https://p.mx530.cn/image/21/04/16/8cecc749c83333f790de742207286e86.png\"},\"live\":{\"zbtjtc\":\"https://p.mx530.cn/static/img/yaoqin.png\"},\"login\":{\"wechat_app_id\":\"wxb7516c01b12667f3\",\"qq_app_id\":\"101957308\",\"yidun_business_id\":\"529f8bfc439d406cb080eee5ef3a04d7\",\"pay_wechat_app_id\":\"wxed0be07dd72f758f\"},\"html\":{\"about_logo\":\"https://x1.mx89.cn:1083/mj/icon.jpg\",\"user_protocol_link\":\"https://x1.mx89.cn:1083/mj/agree_user.html\",\"privacy_protocol_link\":\"https://x1.mx89.cn:1083/mj/agree_privacy.html\",\"recharge_protocol_link\":\"https://x1.mx89.cn:1083/mj/agree_recharge.html\",\"relief_protocol_link\":\"https://x1.mx89.cn:1083/mj/agree_relief.html\",\"culture_protocol_link\":\"https://x1.mx89.cn:1083/mj/culture.html\",\"agree_platform_link\":\"https://x1.mx89.cn:1083/mj/agree_platform.html\",\"protocol_link\":\"https://x1.mx89.cn:1083/mj/protocol.html\",\"teenager_link\":\"https://x1.mx89.cn:1083/mj/teenager.html\",\"copyright_info\":\"版权所有\"}}"
                , LauncherBean.class);
        return (T) launcherBean;
    }

    @Override
    public <T> void get(String url, Map<String, String> paras, HttpCallBack<T> callback) {
        Request request = new Request.Builder().url(url).get().build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callback == null) {
                    return;
                }
                runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        String code = null;
                        String message;
                        if (e instanceof ConnectException) {
                            code = ((ConnectException) e).getCode();
                            message = ((ConnectException) e).getMessage();
                        } else {
                            message = "网络异常";
                        }
                        callback.onFailed(e, code, message);
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (callback == null) {
                    return;
                }
                runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!response.isSuccessful()) {
                            callback.onFailed(null, String.valueOf(response.code()), response.message());
                            return;
                        }
                        try {
                            ResponseBody body = response.body();
                            String result = body.string();
                            Type type = GenericUtil.analysisInterfaceInfo(callback);
                            if (type instanceof Class && TextUtils.equals(((Class<?>) type).getName(), String.class.getName())) {
                                callback.onSuccess((T) result);
                                return;
                            }
                            T t = new Gson().fromJson(result, type);
                            callback.onSuccess(t);
                        } catch (Exception es) {
                            callback.onFailed(es, null, "数据解析失败");
                        }
                    }
                });
            }
        });
    }

    @Override
    public <T> void post(String url, Map<String, String> paras, HttpCallBack<T> callback) {
        FormBody.Builder builder = new FormBody.Builder();
        if (paras != null && paras.size() > 0) {
            for (Map.Entry<String, String> entry : paras.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUIThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                runOnUIThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
    }


    private void runOnUIThread(Runnable runnable) {
        new Handler(Looper.getMainLooper())
                .post(runnable);
    }

}
