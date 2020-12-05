## 隐藏依赖。

>
    有些类是具有欺骗性的。例如有个类的构建函数是我们想要的，然后试图调用这个函数，结果碰的一声，撞到了石头上
    而这个石头最常见就是隐藏依赖
    关于隐藏依赖：比如构建函数中使用了一我们在测试用具中根本无法很好的访问到的资源。
>


示例
```java

public class DependClass {

  /** 用于模拟依赖的方法 */
  public void dependMethod() {
    try {
      System.out.println("隐藏依赖调用...");
      Thread.sleep(3002);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

public class HideClassDemo {

  private final DependClass depend;

  public HideClassDemo() {
    depend = new DependClass();
    depend.dependMethod();
  }

  public void invoke() {
    System.out.println("方法调用。。。");
  }
}


public class TestHideClassDemo {

  @Test
  public void testInvoke() {
    HideClassDemo instance = new HideClassDemo();
    instance.invoke();
  }
}

```


那针对这种隐藏的依赖有没有解决办法呢？

>
    针对隐藏依赖的问题，可以采用的一个技术是参数化构建函数（Parameterize Constructor）,将一个隐藏在构建函数中的依赖“外部化”，
    即让它以参数的形式从外面传进来。
    利用参数化构建函数技术，可以很方便的将构建函数中的依赖外部化。但人们却常常想不到使用它。
    一个原因是人们往往会认为随着某构建函数的依赖被外部化，它的所有调用地方都得进行改动，以便传递新参数。
    但实际上这种想法并不正确。我们可以解决这个问题：
    首先构建函数的函数体提取到一个新方法initialize中,跟大多数的方法提取有所不同，这一提取在没有测试保护的情况下也是相当安全的，
    因为我们可以在提取时运用签名操持技术(java中称为构建函数重载。)
>


示例,通过参数化构建后，可以很容易的解依赖

```java

public class DependClass {
  /** 用于模拟依赖的方法 */
  public void dependMethod() {
    try {
      System.out.println("隐藏依赖调用...");
      Thread.sleep(3002);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

public class HideClassDemo {

  private final DependClass depend;

  /** 默认的构建函数 */
  public HideClassDemo() {
    depend = new DependClass();
    init();
  }

  /**
   * 新加一个构建函数，为解依赖而设
   *
   * @param depend 依赖的类
   */
  public HideClassDemo(DependClass depend) {
    this.depend = depend;
    init();
  }

  /** 进行相关依赖的初始化操作 */
  private void init() {
    depend.dependMethod();
  }

  public void invoke() {
    System.out.println("方法调用。。。");
  }
}

public class TestHideClassDemo {

  @Test
  public void testInvokeBase() {
    HideClassDemo instance = new HideClassDemo();
    instance.invoke();
  }

  @Test
  public void testInvoke() {
    DependClass depend = new TestDependency();
    HideClassDemo instance = new HideClassDemo(depend);
    instance.invoke();
  }

  class TestDependency extends DependClass {

    @Override
    public void dependMethod() {
      System.out.println("依赖的解除，模拟的调用");
    }
  }
}

```

通过构建参数依赖的外部化，可以很容易对依赖进行mock，可以更方便的进行单元测试。
同时使用签名操持技术使原先客户端的调用保持不变。
