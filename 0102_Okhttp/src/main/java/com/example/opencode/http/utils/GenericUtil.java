package com.example.opencode.http.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericUtil {
    public static Type analysisInterfaceInfo(Object clazz) {
        ParameterizedType genericInterfaces = (ParameterizedType) clazz.getClass().getGenericSuperclass();
        // 仅包含一个泛型参数
        Type type = genericInterfaces.getActualTypeArguments()[0];
        return type;
    }
}
