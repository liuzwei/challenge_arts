## Go语言编程技巧分享

在Java语言中如果两个变量互换，则需要借助第三个变量：
```java
public static void main(String[] args) {
        String a = "Hello";
        String b = "World";

        System.out.println(a +" "+ b);
        String c = "";
        c = a ;
        a = b;
        b = c;

        System.out.println(a +" "+ b);
        }
```

但是在Go语言编程，不用引入第三个变量就可以对两个变量进行交换
```go
   a := "hello"
   b := "world"
   a, b = b, a
```

那么为Go语言是如何实现这样的赋值的呢？
其实也没有什么高大上的逻辑，实现的原理还是采用了临时变量，
```go
aTemp = a
bTemp = b
a, b = bTemp, aTemp
```
只不过把这些操作交给了编译器干。