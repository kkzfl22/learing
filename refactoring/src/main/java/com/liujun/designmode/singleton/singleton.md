## 单例的实例方式

### 1. 为什么会需要单例：
>
    1. 节省内存，单例对象可避免频繁的创建与销毁，带来性能的提升。
    2. 对象的共享，例如配制。
>

### 2. 实现单例的方式
1. 饿汉式
2. 懒汉式
3. 双重检测
4. 静态内部类
5. 枚举


### 2.1 单例-饿汉式
```java
public class UniqueIdGenerator {

  private static final UniqueIdGenerator INSTANCE = new UniqueIdGenerator();

  /** 私有化构建函数 */
  private UniqueIdGenerator() {}

  public static UniqueIdGenerator getInstance() {
    return INSTANCE;
  }

  public String getUniqueId() {
    return UUID.randomUUID().toString();
  }
}
```

优势：
>
    实现简单
    由于在类加载期间已经就已经将实例初始化完成，创建实例对象无线程安全问题
>

劣势：
>
    如果初始化非常耗时，将导致系统启动缓慢。
    不支持延迟加载，由于在启动时，就直接加载了，无法做到按需加载。
    极端情况下，还是可以拿到多实例,如通过反射。
>


### 2.2 懒汉式
```java
public class UniqueIdGenerator {

  private static UniqueIdGenerator INSTANCE;

  /** 私有化构建函数 */
  private UniqueIdGenerator() {}

  public static synchronized UniqueIdGenerator getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new UniqueIdGenerator();
    }

    return INSTANCE;
  }

  public String getUniqueId() {
    return UUID.randomUUID().toString();
  }
}

```
优势：
>
    支持延迟加载，可做到按需加载。
    线程安全
>

劣势:
>
    性能瓶颈，这种实现方式，导致每次调用都需要获取锁与释放锁，在大量并发请求时将产生性能问题。
    并发度低。由于加入了synchronized，并行度为1，导致并发度低。
    极端情况下，还是可以拿到多实例,如通过反射。
>


### 2.3 双重检测
```java
public class UniqueIdGenerator {

  private static UniqueIdGenerator INSTANCE;

  /** 私有化构建函数 */
  private UniqueIdGenerator() {}

  public static UniqueIdGenerator getInstance() {
    if (null == INSTANCE) {
      // 类级别的锁
      synchronized (UniqueIdGenerator.class) {
        if (null == INSTANCE) {
          INSTANCE = new UniqueIdGenerator();
        }
      }
    }
    return INSTANCE;
  }

  public String getUniqueId() {
    return UUID.randomUUID().toString();
  }
}

```

优势：
>
    支持延迟加载，可做到按需加载。
    又支持高并发
>

劣势:
>
    这个劣势较小，由于使用了锁，使用时需要小心。
    极端情况下，还是可以拿到多实例,如通过反射。
>



### 2.4 静态内部类
```java
public class UniqueIdGenerator {

  private static UniqueIdGenerator INSTANCE;

  /** 私有化构建函数 */
  private UniqueIdGenerator() {}

  /** 通过静态内部类获取实例 */
  private static class SingleInner {
    private static final UniqueIdGenerator INSTANCE = new UniqueIdGenerator();
  }

  public static UniqueIdGenerator getInstance() {
    return SingleInner.INSTANCE;
  }

  public String getUniqueId() {
    return UUID.randomUUID().toString();
  }
}

```

优势：
>
    支持延迟加载，可做到按需加载。
    又支持高并发
    相比双重检查,实现更为简单，无需再使用锁。
>

劣势:
>
    极端情况下，还是可以拿到多实例,如通过反射。
>

### 2.5 枚举
```java
public enum UniqueIdGenerator {
  INSTANCE;

  public String getUniqueId() {
    return UUID.randomUUID().toString();
  }
}

```

优势：
>
    支持延迟加载，可做到按需加载。
    又支持高并发
    最极端的情况，也无法拿到多实例。
    java最推荐的写法。
>




