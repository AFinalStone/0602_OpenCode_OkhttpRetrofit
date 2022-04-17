package com.example.httpproxy.http;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class HttpHelper {
    public <T> Class<T> analysisInterfaceInfo(Method method) {
        Type returnType = method.getGenericReturnType();
        if (returnType instanceof ParameterizedType) {
            Type[] TypeArguments = ((ParameterizedType) returnType).getActualTypeArguments();
            Type type = TypeArguments[0];
            return (Class<T>) type;
        }
        return null;
    }
}
