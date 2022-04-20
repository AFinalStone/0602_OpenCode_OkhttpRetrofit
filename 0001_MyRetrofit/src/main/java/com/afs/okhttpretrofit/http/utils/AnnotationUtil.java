package com.afs.okhttpretrofit.http.utils;

import com.afs.okhttpretrofit.http.retrofit.annotate.Field;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationUtil {

    /**
     * 获取给 "方法参数" 中注解的数值
     *
     * @param method 要获取参数名的方法
     * @return 按参数顺序排列的参数名列表
     */
    public static Object[] getMethodParameterNamesByAnnotation(Method method) {
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (parameterAnnotations == null || parameterAnnotations.length == 0) {
            return null;
        }
        Object[] parameterNames = new Object[parameterAnnotations.length];
        int i = 0;
        for (Annotation[] parameterAnnotation : parameterAnnotations) {
            for (Annotation annotation : parameterAnnotation) {
                if (annotation instanceof Field) {
                    Field param = (Field) annotation;
                    parameterNames[i++] = param.value();
                }
            }
        }
        return parameterNames;
    }
}
