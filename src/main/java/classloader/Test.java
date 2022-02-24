package classloader;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "classLoader")
public class Test {

    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        log.info("系统默认的类加载器 {}", classLoader);

        ClassLoader extClassLoader = classLoader.getParent();
        log.info("App classLoader的父加载器 {}", extClassLoader);
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        log.info("Extension classLoader的父加载器 {}", extClassLoader);

        /**
         * 13:03:49.564 [main] INFO classLoader - 系统默认的类加载器 sun.misc.Launcher$AppClassLoader@18b4aac2
         * 13:03:49.570 [main] INFO classLoader - App classLoader的父加载器 sun.misc.Launcher$ExtClassLoader@4783da3f
         * 13:03:49.570 [main] INFO classLoader - Extension classLoader的父加载器 sun.misc.Launcher$ExtClassLoader@4783da3f
         * */

    }


}
