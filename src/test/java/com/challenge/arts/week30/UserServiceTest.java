package com.challenge.arts.week30;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class UserServiceTest {

    @Test
    void getClassName() throws ClassNotFoundException {
        Class<?> aClass = Class.forName("com.challenge.arts.week30.User");
        assertEquals("User", aClass.getSimpleName());
        assertEquals("com.challenge.arts.week30.User", aClass.getName());
        assertEquals("com.challenge.arts.week30.User", aClass.getCanonicalName());
    }

    /**
     * 获取修饰符
     */
    @Test
    void getClassModifier() throws ClassNotFoundException {
        Class<?> userClass = Class.forName("com.challenge.arts.week30.User");
        Class<?> abstractUserClass = Class.forName("com.challenge.arts.week30.AbstractUser");
        // 获取User的类修饰符
        int userClassModifiers = userClass.getModifiers();
        // 获取AbstractUser的修饰符
        int abstractUserClassModifiers = abstractUserClass.getModifiers();

        assertTrue(Modifier.isPublic(userClassModifiers));
        assertTrue(Modifier.isAbstract(abstractUserClassModifiers));
    }

    @Test
    void getSuperClass() throws ClassNotFoundException {
        Class<?> userClass = Class.forName("com.challenge.arts.week30.User");
        Class<?> superclass = userClass.getSuperclass();

        assertEquals("AbstractUser", superclass.getSimpleName());
    }

    @Test
    void getUserInterfaces() throws ClassNotFoundException {
        Class<?> userClass = Class.forName("com.challenge.arts.week30.User");
        Class<?>[] interfaces = userClass.getInterfaces();

        assertEquals(1, interfaces.length);

        assertEquals("Run", interfaces[0].getSimpleName());
    }

    @Test
    void getUserConstructor() throws ClassNotFoundException {
        Class<?> userClass = Class.forName("com.challenge.arts.week30.User");

        Constructor<?>[] constructors = userClass.getConstructors();
        assertEquals(2, constructors.length);

    }

    @Test
    void getUserFields() throws ClassNotFoundException {
        Class<?> userClass = Class.forName("com.challenge.arts.week30.User");

        Field[] fields = userClass.getFields();
        List<String> filedNames = Arrays.stream(fields).map(Field::getName).collect(Collectors.toList());
        assertTrue(filedNames.containsAll(Arrays.asList("name", "age")));
    }


    @Test
    void getUserName() {

    }

}