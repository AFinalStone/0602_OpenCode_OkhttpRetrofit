package com.afs.okhttpretrofit.http.retrofit;

import android.os.SystemClock;
import android.util.Log;

import com.afs.okhttpretrofit.bean.LoginBean;
import com.afs.okhttpretrofit.http.interfaces.IHttp;
import com.afs.okhttpretrofit.http.retrofit.annotate.GET;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public final class Retrofit implements IRetrofit {
    public static String TAG = "Retrofit========";
    private Builder builder;

    public Retrofit(Builder builder) {
        this.builder = builder;
    }

    @Override
    public <T> T create(Class<T> clazz) {
        T t = (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Log.d(TAG, method.getName());
                if ("login".equals(method.getName())) {
                    SystemClock.sleep(5000);
                    GET get = method.getAnnotation(GET.class);
                    String url = "https://www.fastmock.site/mock/923c76c832db681132f9ac8d9abd6e05/girl_shopping" + get.value();
                    LoginBean respBean = builder.iHttp.get_sync(url, null);
                    return respBean;
                }
                return null;
            }
        });
        return t;
    }


    public static class Builder {
        IHttp iHttp;

        public Builder(IHttp iHttp) {
            this.iHttp = iHttp;
        }

        public Retrofit builder() {
            return new Retrofit(this);
        }
    }
}
