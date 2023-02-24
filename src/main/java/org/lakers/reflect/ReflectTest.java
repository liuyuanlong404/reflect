package org.lakers.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created on 2023/2/24 11:34
 *
 * @author lakers
 */
public class ReflectTest {

    public static void main(String[] args) throws Exception {

        // 获取 TargetObject 类的 Class 对象并且创建 TargetObject 类实例
        Class<TargetObject> targetObjectClass = getTargetObjectClass4();
        Constructor<?>[] declaredConstructors = targetObjectClass.getDeclaredConstructors();
        TargetObject targetObject = (TargetObject) declaredConstructors[0].newInstance();

        // 获取 TargetObject 类中定义的所有方法
        Method[] methods = targetObjectClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        // 获取指定方法并调用
        Method publicMethod = targetObjectClass.getDeclaredMethod("publicMethod", String.class);
        publicMethod.invoke(targetObject, "Java");

        // 获取指定参数并对参数进行修改
        Field field = targetObjectClass.getDeclaredField("value");
        // 为了对类中的参数进行修改我们取消安全检查
        field.setAccessible(true);
        field.set(targetObject, "Hello");

        // 调用指定private方法
        Method privateMethod = targetObjectClass.getDeclaredMethod("privateMethod");
        // 为了调用private方法我们取消安全检查
        privateMethod.setAccessible(true);
        privateMethod.invoke(targetObject);
    }

    public static Class<TargetObject> getTargetObjectClass1() {
        return TargetObject.class;
    }

    @SuppressWarnings("unchecked")
    public static Class<TargetObject> getTargetObjectClass2() throws ClassNotFoundException {
        return (Class<TargetObject>) Class.forName("org.lakers.reflect.TargetObject");
    }

    @SuppressWarnings("unchecked")
    public static Class<TargetObject> getTargetObjectClass3() {
        return (Class<TargetObject>) new TargetObject().getClass();
    }

    @SuppressWarnings("unchecked")
    public static Class<TargetObject> getTargetObjectClass4() throws ClassNotFoundException {
        return (Class<TargetObject>) ClassLoader.getSystemClassLoader().loadClass("org.lakers.reflect.TargetObject");
    }


}
