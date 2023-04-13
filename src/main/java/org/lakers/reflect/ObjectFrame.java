package org.lakers.reflect;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

/**
 * Created on 2023/4/13 10:27
 *
 * @author lakers
 */
public class ObjectFrame {

    public static void saveObject(Object obj) {
        PrintStream printStream;
        try {
            printStream = new PrintStream(new FileOutputStream("src/data.txt", true));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Class<?> clazz = obj.getClass();
        printStream.println("---------------" + clazz.getSimpleName() + "-------------------");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            field.setAccessible(true);
            String value;
            try {
                value = field.get(obj).toString();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            printStream.println(name + "=" + value);
        }

    }
}
