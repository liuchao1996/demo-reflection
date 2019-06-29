package com.kernel;

import com.kernel.annotation.MyAPIMapping;
import com.kernel.annotation.MyMapping;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author lc
 */
public class MyStore {

    private ApplicationContext applicationContext;

    private HashMap<String, MyRunnable> apiMap = new HashMap<>();

    public MyStore(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 装载有注解所有类和方法
     */
    public void loadFromSpringBeans() {

        //获取指定注解所有的 Bean 的名字
        String[] names = applicationContext.getBeanNamesForAnnotation(MyMapping.class);

        //存放类 ? 表示不确定的类型
        Class<?> type;

        for (String name : names) {

            //从spring容器中获取对象类型
            type = applicationContext.getType(name);

            //这是Java 8中新增的，该方法获取直接修饰该class对象对应类的指定类型的Annotation，如果不存在，则返回null
            MyMapping mapping = type.getDeclaredAnnotation(MyMapping.class);

            if (mapping != null) {

                HashMap<String, Method> methodHashMap = new HashMap<>();

                for (Method m : type.getMethods()) {

                    // 通过反射拿到MyAPIMapping注解
                    MyAPIMapping apiMapping = m.getAnnotation(MyAPIMapping.class);
                    if (apiMapping != null) {
                        methodHashMap.put(apiMapping.value(), m);
                    }
                }
                addApiItem(mapping, name, methodHashMap);
            }

        }

    }

    /**
     * 创建一个 执行方法类对象 放进map中
     *
     * @param apiMapping
     * @param beanName
     * @param methodMap
     */
    private void addApiItem(MyMapping apiMapping, String beanName, HashMap<String, Method> methodMap) {
        MyRunnable apiRun = new MyRunnable();
        apiRun.apiName = apiMapping.value();
        apiRun.targetMethodMap = methodMap;
        apiRun.targetName = beanName;
        apiRun.myMapping = apiMapping;
        apiRun.target = applicationContext.getBean(beanName);
        apiMap.put(apiMapping.value(), apiRun);
    }

    /**
     * 获取执行对象
     *
     * @param apiName
     * @return
     */
    public MyRunnable findApiRunnable(String apiName) {
        return apiMap.get(apiName);
    }

}
