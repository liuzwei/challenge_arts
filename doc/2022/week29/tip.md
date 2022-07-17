
### Objects中的一些用法

### 1.空值判断

在开发过程中有一些对象需要验证是否为空，为空的情况下可能需要抛出异常。像空指针这样的异常可以这样抛


```java
        // 业务逻辑获取某个对象
        Object someObj = getSomeObject();
        // 验证对象是否为空
        Objects.requireNonNull(someObj, "某业务对象不能为空");
        // TODO 接下来处理相关逻辑
```

### 2.空值判断增加函数

在开发过程中获取某个对象，如果对象为空，异常判断时可以根据自身相关业务逻辑传递一个Supplier函数到Objects中。

```java
public class TestObjects {

    private static String ObjectId = "ABC";
    public static void main(String[] args) {
        // 业务逻辑获取某个对象
        Object someObj = getSomeObject();
        // 验证对象是否为空
        Objects.requireNonNull(someObj, messageSupplier());
//        Objects.requireNonNull(someObj, "某业务对象不能为空");
        // TODO 接下来处理相关逻辑

    }

    private static Object getSomeObject(){

        return null;
    }

    private static Supplier<String> messageSupplier(){
        return () -> {
            return "ObjectId "+ObjectId + "获取对象为空";
        };
    }
}
```


