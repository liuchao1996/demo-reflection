package com.kernel;

import com.kernel.annotation.MyMapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lc
 */
public class MyRunnable {

    String apiName;
    /**
     * 接口name
     */
    String targetName;
    /**
     * 接口实现
     */
    Object target;
    HashMap<String, Method> targetMethodMap = new HashMap<>();

    /**
     * 注解
     */
    MyMapping myMapping;

    /**
     * 反射调用方法
     *
     * @param method
     * @param params
     * @return
     * @throws Exception
     */
    public Map<String, Object> run(Method method, Map<String, Object> params) throws Exception {
        return (Map<String, Object>) method.invoke(target, params.get("data"));
    }

    /**
     * 获取方法Map
     *
     * @return
     */
    public HashMap<String, Method> getTargetMethodMap() {
        return targetMethodMap;
    }
}
