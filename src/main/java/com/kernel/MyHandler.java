package com.kernel;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取ApplicationContext,调用方法商店
 *
 * @author lc
 */
@Component
public class MyHandler implements ApplicationContextAware {

    MyStore myStore;

    public Map<String, Object> handle(String tag, String methodTag, Map<String, Object> params) {

        Map<String, Object> result = new HashMap<>();

        MyRunnable myRunnable = myStore.findApiRunnable(tag);

        if (myRunnable == null) {
            result.put("msg", "未找到可用类");
            return result;
        }

        try {
            Method method = myRunnable.getTargetMethodMap().get(methodTag);
            if (method == null) {
                result.put("msg", "未找到可用接口");
                return result;
            }

            result = myRunnable.run(method, params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        myStore = new MyStore(applicationContext);
        //加载对象
        myStore.loadFromSpringBeans();
    }


}
