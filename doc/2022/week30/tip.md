### JunitTest中断言的使用

在测试过程中，判断测试中的结果时一般会用到断言。
首先编写测试代码要先引入测试依赖：
```xml
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.8.2</version>
            <scope>test</scope>
        </dependency>
```

简单的断言判断有：

| 方法  | 说明  |
|-----|-----|
|assertEquals  | 判断两个对象或两个原始类型是否相等    |
|assertNotEquals     | 判断两个对象或两个原始类型是否不相等    |
|assertSame     | 判断两个对象引用是否指向同一个对象    |
|assertNotSame     |  判断两个对象引用是否指向不同的对象   |
|assertTrue     | 判断给定的布尔值是否为 true    |
|assertFalse     | 判断给定的布尔值是否为 false    |
|assertNull     | 判断给定的对象引用是否为 null    |
|assertNotNull     | 判断给定的对象引用是否不为 null    |

除了简单断言外，还支持一些较复杂的断言：

1. 数组断言
```java
    @Test
    public void array() {
        assertArrayEquals(new int[]{1, 2}, new int[] {1, 2});
    }
```

2. 组合断言
```java
@Test
public void all() {
 assertAll("Math",
    () -> assertEquals(2, 1 + 1),
    () -> assertTrue(1 > 0)
 );
}
```

3. 异常断言
```java
    @Test
    public void exceptionTest() {
        ArithmeticException exception = assertThrows(
                //扔出断言异常
                ArithmeticException.class, () -> System.out.println(1 % 0));
    }
```

4. 超时断言
```java
@Test
    public void timeoutTest() {
        //如果测试方法时间超过1s将会异常
        assertTimeout(Duration.ofMillis(1000), () -> Thread.sleep(500));
    }
```
