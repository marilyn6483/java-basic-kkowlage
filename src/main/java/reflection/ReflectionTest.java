package reflection;

import entity.Person;
import org.junit.Test;

import java.lang.reflect.Field;

public class ReflectionTest {

    @Test
    public void test1() throws IllegalAccessException, InstantiationException {
        Class clazz = Person.class;
        //利用反射创建对象实例
        Person person = (Person) clazz.newInstance();
        System.out.println(person);
        //获取类的所有属性
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i]);
        }
    }

    @Test
    public void test2() throws ClassNotFoundException {
        // 通过反射创建对象
        // 1. 通过类的完全限定名获取类的Class对象
        Class personClass = Class.forName("entity.Person");
    }
}
