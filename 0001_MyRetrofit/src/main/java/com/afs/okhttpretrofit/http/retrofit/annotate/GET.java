package com.afs.okhttpretrofit.http.retrofit.annotate;

import java.lang.annotation.*;

/**
 * 作用于方法的注解，被此注解标识的方法，在进行网络请求的时候使用GET请求
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GET {
    String value();
}
