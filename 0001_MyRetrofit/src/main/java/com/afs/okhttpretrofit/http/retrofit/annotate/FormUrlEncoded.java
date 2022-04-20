package com.afs.okhttpretrofit.http.retrofit.annotate;

import java.lang.annotation.*;

/**
 * 作用于接口方法的注解，Post请求有参数的时候需要此注解
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormUrlEncoded {
}
