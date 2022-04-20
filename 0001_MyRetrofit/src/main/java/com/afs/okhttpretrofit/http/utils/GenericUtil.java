package com.afs.okhttpretrofit.http.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericUtil {
    public static Type analysisInterfaceInfo(Object object) {
        ParameterizedType genericInterfaces = (ParameterizedType) object.getClass().getGenericSuperclass();
        // 仅包含一个泛型参数
        Type type = genericInterfaces.getActualTypeArguments()[0];
        return type;
    }
}
