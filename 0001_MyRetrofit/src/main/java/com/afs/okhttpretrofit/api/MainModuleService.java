package com.afs.okhttpretrofit.api;


import com.afs.okhttpretrofit.bean.LoginBean;
import com.afs.okhttpretrofit.http.retrofit.annotate.Field;
import com.afs.okhttpretrofit.http.retrofit.annotate.FormUrlEncoded;
import com.afs.okhttpretrofit.http.retrofit.annotate.GET;
import com.afs.okhttpretrofit.http.retrofit.annotate.POST;

/**
 * @author syl
 * @time 2022/4/20 15:34
 */
public interface MainModuleService {

    /**
     * 登录接口
     *
     * @return
     */
    @GET("/user/login")
    LoginBean login();


}