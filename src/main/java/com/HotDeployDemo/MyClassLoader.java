package com.HotDeployDemo;

import java.io.IOException;

public class MyClassLoader extends ClassLoader{

    private static final String BASE_DIR = "data/c87/";

    private String FILE_NAME = "G:/IdeaProjects/demo-reflection/target/classes/com/HotDeployDemo/HelloImpl.class";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        System.out.println(name+"=====================");

        String fileName = name.replaceAll("\\.", "/");
        fileName = BASE_DIR + fileName + ".class";
        try {
            byte[] bytes = BinaryFileUtils.readFileToByteArray(fileName);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException ex) {
            throw new ClassNotFoundException("failed to load class " + name, ex);
        }
    }

}
