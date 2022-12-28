package src.tools;

import src.pojo.Gameround;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;

public class Reflect {
    public static void inspect() throws SQLException {
        Class<Gameround> c = Gameround.class;
        System.out.println("类名称" + c.getName());
     Constructor<?>[] constructors = c.getConstructors();
     System.out.println("----------------所有构造方法----------------");
     for (int i = 0; i < constructors.length; i++) {
         System.out.println(constructors[i]);
     }
     System.out.println("----------------所有成员变量----------------");
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName() + "===>" + field.getType());
        }
        System.out.println("----------------所有方法----------------");
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName() + "(返回值类型:" + method.getReturnType() + " 返回值个数:" + method.getParameterCount() + ")");
        }
        System.out.println("-------------------------------------------------------");
    }
}
