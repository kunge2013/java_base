package classloader;

import java.net.URL;

public class ClassLoaderTest extends Object {
    public static void main(String[] args) {
        ClassLoader c = ClassLoaderTest.class.getClassLoader();
        System.out.println("parent:" + c);
        ClassLoader parent1 =c.getParent();
        System.out.println("parent1:" + parent1);
        ClassLoader parent2 = parent1.getParent();
        System.out.println("parent2:" + parent2);
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for(URL url : urls){
            System.out.println(url.toExternalForm());
        }


    }
}
