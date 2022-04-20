package com.afs.okhttpretrofit.http.retrofit.annotate;

import java.lang.annotation.*;

/**
 * 此注解作用于方法参数
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Field {
    String value();
}
