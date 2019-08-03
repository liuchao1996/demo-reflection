package com.HotDeployDemo;

import java.io.File;

public class HotDeployDemo {
    // shuo.laoma.dynamic.c87.HelloImpl
    private static final String CLASS_NAME = "com.HotDeployDemo.HelloImpl";
    // data/c87/  G:\IdeaProjects\demo-reflection\src\main\java\com\HotDeployDemo\HelloImpl.java
    // G:/IdeaProjects/demo-reflection/target/classes/com/HotDeployDemo/HelloImpl.class
    private static final String FILE_NAME = "G:/IdeaProjects/demo-reflection/target/classes/com/HotDeployDemo/HelloImpl.class";
    //+ CLASS_NAME.replaceAll("\\.", "/") + ".java";
    private static volatile IHelloService helloService;

    private static IHelloService getHelloService() {
        if (helloService != null) {
            return helloService;
        }
        synchronized (HotDeployDemo.class) {
            if (helloService == null) {
                helloService = createHelloService();
            }
            return helloService;
        }
    }

    private static IHelloService createHelloService() {
        try {
            MyClassLoader cl = new MyClassLoader();
            Class<?> cls = cl.loadClass(CLASS_NAME);
            if (cls != null) {
                return (IHelloService) cls.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void client() {
        Thread t = new Thread(() -> {
            try {
                while (true) {
                    IHelloService helloService = getHelloService();
                    helloService.sayHello();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
            }
        });
        t.start();
    }

    public static void monitor() {
        Thread t = new Thread() {
            private long lastModified = new File(FILE_NAME).lastModified();

            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(1000);
                        long now = new File(FILE_NAME).lastModified();

                        //System.out.println("now:" + now + "  lastModified:" + lastModified + "  " + (now == lastModified));

                        if (now != lastModified) {
                            lastModified = now;
                            reloadHelloService();
                        }
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }

    public static void reloadHelloService() {
        helloService = createHelloService();
    }

    public static void main(String[] args) {
        monitor();
        client();

        /*String fileName = "G:\\IdeaProjects\\demo-reflection\\src\\main\\java\\com\\HotDeployDemo\\HelloImpl.java";

         long lastModified = new File(fileName).lastModified();
        System.out.println(lastModified);*/
    }
}

