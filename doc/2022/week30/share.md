
## 1. Java 反射机制
Java反射是指在程序运行时，允许我们检查和/或修改类、接口、字段和方法的运行时属性。

## 2. 反射包
在项目中使用反射机制，不需要依赖任何的Jar包或是配置、或Maven依赖。在JDK的`com.lang.reflect`包下已经包含了相关API。


## 3. 获取对象名称

```java
    @Test
    void getClassName() throws ClassNotFoundException {
        Class<?> aClass = Class.forName("com.challenge.arts.week30.User");
        assertEquals("User", aClass.getSimpleName());
        assertEquals("com.challenge.arts.week30.User", aClass.getName());
        assertEquals("com.challenge.arts.week30.User", aClass.getCanonicalName());
    }
```

### 4. 获取修饰符
```java
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
```

### 5. 获取父类

```java
    @Test
    void getSuperClass() throws ClassNotFoundException {
        Class<?> userClass = Class.forName("com.challenge.arts.week30.User");
        Class<?> superclass = userClass.getSuperclass();

        assertEquals("AbstractUser", superclass.getSimpleName());
    }
```

### 6. 获取实现接口
```java
    @Test
    void getUserInterfaces() throws ClassNotFoundException {
        Class<?> userClass = Class.forName("com.challenge.arts.week30.User");
        Class<?>[] interfaces = userClass.getInterfaces();

        assertEquals(1, interfaces.length);

        assertEquals("Run", interfaces[0].getSimpleName());
    }
```

### 7. 获取构造器

```java

    @Test
    void getUserConstructor() throws ClassNotFoundException {
        Class<?> userClass = Class.forName("com.challenge.arts.week30.User");

        Constructor<?>[] constructors = userClass.getConstructors();
        assertEquals(2, constructors.length);
        
    }

```
### 8. 获取字段
```java
    @Test
    void getUserFields() throws ClassNotFoundException {
        Class<?> userClass = Class.forName("com.challenge.arts.week30.User");

        Field[] fields = userClass.getFields();
        List<String> filedNames = Arrays.stream(fields).map(Field::getName).collect(Collectors.toList());
        assertTrue(filedNames.containsAll(Arrays.asList("name", "age")));
    }

```