package com.example.httpproxy.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericUtil {
    public static Type analysisInterfaceInfo(Object clazz) {
        Type[] genericInterfaces = clazz.getClass().getGenericInterfaces();
        if (genericInterfaces[0] instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) (genericInterfaces[0]);
            // 仅包含一个泛型参数
            Type type = parameterizedType.getActualTypeArguments()[0];
            return type;
        }
        return null;
    }
}
