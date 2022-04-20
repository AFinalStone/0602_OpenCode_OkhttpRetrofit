package com.afs.okhttpretrofit.api;


import com.afs.okhttpretrofit.bean.LauncherBean;
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
     * 获取打招呼列表
     *
     * @return
     */
    @GET("/v1/home/get-hello-goddess")
    String getHelloGoddess();

    /**
     * 一键打招呼，发送
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/v1/home/say-hello-goddess")
    String sendHelloGoddess(@Field("goddess") String goddess);

    /**
     * 上传GPS信息
     *
     * @param location 具体的定位信息
     * @return
     */
    @FormUrlEncoded
    @POST("/v1/home/update-cur-location")
    String uploadGPSInfo(@Field("location") String location);

    /**
     * 获取主页底部有哪些Tab以及默认要显示那个tab的相关信息
     *
     * @return
     */
    @GET("/v1/init/index")
    Integer getMainIndexBottomTabSetting();


    /**
     * 刷新红点
     *
     * @return
     */
    @GET("/v1/init/refresh")
    Integer refreshRed();

    /**
     * 获取女性用户主播认证的结果
     *
     * @return
     */
    @GET("/v1/user/goddess-auth-res")
    LauncherBean getGoddessAuthResult();


    /**
     * 向服务端报告主播认证成功弹已经显示过了
     *
     * @return
     */
    @POST("/v1/user/goddess-auth-res-report")
    Object reportGoddessAuthDialogHaveShow();

}