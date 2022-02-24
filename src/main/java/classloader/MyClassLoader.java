package classloader;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {
    private String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = getByte(name);
            return defineClass(name, data, 0, data.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] getByte(String name) throws IOException {
        name = name.replace(".", "/");
        String classFile = classPath + "/" + name + ".class";
        FileInputStream in = new FileInputStream(classFile);
        int len = in.available();
        byte[] data = new byte[len];
        in.read(data);
        in.close();
        return data;
    }

    public static void main(String[] args) {
        MyClassLoader myClassLoader = new MyClassLoader("D:/Java");
        Class userClass = null;
        try {
            userClass = myClassLoader.loadClass("classloader.User");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(userClass.getClassLoader());
    }
}
