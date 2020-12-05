# 针对传递null对象的问题。


关键记住一点：不到万不得已，千万别在产品代码中传递null。

>
    在编写测试时，如果发现某个对象需要的某参数难以构建，便可以考滤传递一个null,这样一来如果这个参数在测试过程中被用到了的话，
    代码就会抛出一个异常，而测试用具这会捕获这个异常，这时候，如果确实需要一个对象而非null的话，再去构建这个对象并将它作为参数再传递也不晚。
    传null的手法在某些语言中是一项非常便利的技术，在java和C#中使用起来没有什么问题，其实只要是那些会对运行期使用空引用抛出异常的语言，
    这项技术都适用。但这也意味着在C和C++中使用这项技术并不是个好意义，除非你知道运行时系统会检查出空指针的使用。如果系统并不具备这样的能力，
    还是别用为妙，否则最后会发现你的测试神秘崩溃，而这还算是运气好的结果，运气不好的话测试则会悄元声息地出问题，而你拿他们一点办法也没有，
    它们在运行时会破坏内存，而你还蒙在鼓里。
>



## 针对空指针问题
>
    我总结的有以下几种解决方案：
    1. 直接抛出一个异常。
    2. 使用空对象模式。
    3. jdk8后提供了一种Optional机制。   
>  



## 示例的问题:
```java

public class CustomerFactory {

  private static final String[] DATA = {"1", "2", "3"};

  public RealDataQuery query(String query) {

    for (String dataId : DATA) {
      if (dataId.equals(query)) {
        return new RealDataQuery();
      }
    }

    return null;
  }
}

public class RealDataQuery {

  public String query() {
    return "success";
  }
}


public class TestCustomerFactory {

  @Test
  public void testCustomer() {
    CustomerFactory factory = new CustomerFactory();

    for (int i = 0; i < 6; i++) {
      RealDataQuery customer = factory.query(String.valueOf(i));
      System.out.println(customer.query());
    }
  }
}

```

### 运行，空指针来袭

>
    java.lang.NullPointerException
            at com.TestCustomerFactory.testCustomer(TestCustomerFactory.java:17)
            at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
            at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
            at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
            at java.lang.reflect.Method.invoke(Method.java:498)
            at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:54)
            at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:67)
            at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:52)
            at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:96)
            at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:75)
            at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:69)
            at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)
            at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)
            at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)
>



  



## 解决方案1： 1. 空对象模式

>
    空对象模式用于避免在程序中传递null。
>

### 1. 声明公共的接口
```java
/**
 * 查询的接口
 *
 * @version 0.0.1
 */
public interface CustomerInf {

  /**
   * 定义查询方法
   *
   * @return 查询的结果
   */
  String query();

  /**
   * 是否为null
   *
   * @return
   */
  boolean isNul();
}



/**
 * 用来模拟实际的查询操作
 *
 * @version 0.0.1
 */
public class RealDataQuery implements CustomerInf {

  @Override
  public String query() {
    return "success";
  }

  @Override
  public boolean isNul() {
    return false;
  }
}

/**
 *  当为空的时候的处理。
 *
 * @version 0.0.1
 */
public class NullQuery implements CustomerInf {

  @Override
  public String query() {
    return "can not find";
  }

  @Override
  public boolean isNul() {
    return true;
  }
}


/**
 * 对象工厂
 * 返回值做出修改 
 *
 * @author liujun
 * @version 0.0.1
 */
public class CustomerFactory {

  private static final String[] DATA = {"1", "2", "3"};

  public CustomerInf query(String query) {

    for (String dataId : DATA) {
      if (dataId.equals(query)) {
        return new RealDataQuery();
      }
    }

    return new NullQuery();
  }
}

public class TestNullObject {

  @Test
  public void testCustomer() {
    CustomerFactory factory = new CustomerFactory();

    for (int i = 0; i < 6; i++) {
      CustomerInf customer = factory.query(String.valueOf(i));
      System.out.println(customer.query());
    }
  }
}

```

>
    经过这个操作之后，不会再有空指针异常异常的犯脑了
>


###  没有空指针了，就不一定没有问题了
```java

public class TestNullObject {

  @Test
  public void testCustomerCount() {
    CustomerFactory factory = new CustomerFactory();

    int countNum = 0;
    for (int i = 0; i < 6; i++) {
      CustomerInf customer = factory.query(String.valueOf(i));
      System.out.println(customer.query());
      countNum++;
    }
    System.out.println(countNum);
  }
}


```

这段统计就是一个代表。因为中间包含了空对象，所以统计就是错误的。





