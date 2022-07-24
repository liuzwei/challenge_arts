package com.challenge.arts.week30;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * reflect
 */
public class ReflectMain {

    public static void main(String[] args) throws Exception {
        // Get a Clazz
        Class<?> clazz = Class.forName("com.challenge.arts.week30.UserService");
        // Get All Constructors of the Class
        Constructor<?>[] constructors = clazz.getConstructors();
        System.out.printf("--------------Get Constructors--------------\n");
        for (Constructor constructor : constructors) {
            System.out.printf("constructor is %s \n", constructor);
        }

        // Get All Methods
        System.out.printf("--------------Get Methods--------------\n");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.printf("Method is %s \n", method);
        }

        // instantiate an Object
        Class params[] = new Class[1];
        params[0] = User.class;
        Constructor<?> constructor = clazz.getConstructor(params);
        Object o = constructor.newInstance(new User());

        Method getUserName = clazz.getMethod("getUserName", null);
        Object name = getUserName.invoke(o, null);
        System.out.printf("name is %s \n", name);

    }
}
