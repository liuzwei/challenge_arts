## Guava中Future的使用

### 应用场景

假设有这样一个场景，一个程序要学生信息进行应用。首先要查询大量的学生信息，然后要对学生信息进行处理，最终才是对学生信息的使用。
一般的做法就是这三步同步进行，先查询、再处理、最后应用。如果每一步耗时时间挺长怎么处理？如果每一步的处理都在不同的服务如何处理？

这样可以使用Future的相关特性来处理相关流程。

### 业务实现

```java

public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);

        ListenableFuture<List<Student>> searchStudent = listeningExecutorService.submit(() -> {
            // 模拟查询学生信息
            Thread.sleep(3000);
            List<Student> list = new ArrayList<>();
            for (int i=0; i<5; i++) {
                list.add(new Student("student"+i,10));
            }
            return  list;
        });

        // 模拟查询完学生信息后，对学生信息处理
        ListenableFuture<List<Student>> handleStudent = Futures.transform(searchStudent, students -> {
            for (int i=0; i<students.size(); i++) {
                students.get(i).setAge(i+10);
            }
            return students;
        }, MoreExecutors.directExecutor());

        // 模拟对处理完的信息进行使用
        FutureCallback<List<Student>> futureCallback = new FutureCallback<List<Student>>() {
            @Override
            public void onSuccess(@Nullable List<Student> result) {
                for (Student student : result) {
                    log.info("student is {}", student);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };
        Futures.addCallback(handleStudent, futureCallback, executorService);
        log.info("it's over!");

    }
```
