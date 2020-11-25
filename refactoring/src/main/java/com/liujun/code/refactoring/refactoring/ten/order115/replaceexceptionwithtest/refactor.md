#Replace Exception with Test(以测试取代异常)

##原因
异常的出现是程序语言一大进步，运用Replace Error code with Exception,异常便可协助我们避免很多复杂的错误处理逻辑。
但就像许多好东西一样，异常也会被滥用，从而变得 不再让人愉快。
异常只应该被用于异常、罕见的很为，也就是那些产生意料之外的错误行，而不应该成为条件检查的替代品，
如果你可以合理期调用者在调用函数之前先检查某个条件。那么就应该提供一个测试，而调用者应该使用它。


##做法
<ul>
    <li>1,在函数调用点之前，放置一个测试语句，将函数内catch区段中的代码复制到测试句的适当if分支中。</li>
    <li>2,在catch区段起始处加入一个断言，确保catch区段绝对不会被执行。</li>
    <li>3,编译，测试</li>
    <li>4,移除所有catch区段，然后将try区段内的代码复杂到try之后，然后移除try区段</li>
    <li>5,编译，测试。</li>
</ul>



##以资源池为例
##src
```java
public class ResourcePool {

  private Stack<Resource> available = new 邀请你查看摹客项目演示，点击链接即可进入演示。
                                                在飞书打开：https://applink.feishu.cn/client/mini_program/open?appId=cli_9d2d59be33e0910c&mode=window&path=pages%2Fcommon%2Findex%3FotherUrl%3Dhttps%3A%2F%2Fapp.mockplus.cn%2Frun%2Frp%2FnmteV3GDdDR%2FiwPsDrhaS%3Fps%3D0%26ha%3D0%26la%3D1%26fc%3D1%26out%3D1%26isPreview%3Dtrue
                                                在浏览器打开：https://app.mockplus.cn/run/rp/nmteV3GDdDR/iwPsDrhaS?ps=0&ha=0&la=1&fc=1&out=1邀请你查看摹客项目演示，点击链接即可进入演示。
                                                                                                                                           在飞书打开：https://applink.feishu.cn/client/mini_program/open?appId=cli_9d2d59be33e0910c&mode=window&path=pages%2Fcommon%2Findex%3FotherUrl%3Dhttps%3A%2F%2Fapp.mockplus.cn%2Frun%2Frp%2FnmteV3GDdDR%2FiwPsDrhaS%3Fps%3D0%26ha%3D0%26la%3D1%26fc%3D1%26out%3D1%26isPreview%3Dtrue
                                                                                                                                           在浏览器打开：https://app.mockplus.cn/run/rp/nmteV3GDdDR/iwPsDrhaS?ps=0&ha=0&la=1&fc=1&out=1<>();
  private Stack<Resource> allocatedList = new Stack<>();

  public Resource getResource() {
    Resource result;

    try {
      result = available.pop();
      allocatedList.push(result);
      return result;
    } catch (EmptyStackException e) {
      result = new DataResourceDemo();
      allocatedList.push(result);
      return result;
    }
  }
}
```


##refactor
1,为去掉这里的异常，首先必须添加一个适当的提前测试，并在其中处理可用资源池为空的情况
```java
public class ResourcePool {

  private Stack<Resource> availableList = new Stack<>();
  private Stack<Resource> allocatedList = new Stack<>();

  public Resource getResource() {
    Resource result;

    // 添加测试资源为空的情况
    if (availableList.isEmpty()) {
      result = new DataResourceDemo();
      allocatedList.push(result);
      return result;
    }

    try {
      result = availableList.pop();
      allocatedList.push(result);
      return result;
    } catch (EmptyStackException e) {
      result = new DataResourceDemo();
      allocatedList.push(result);
      return result;
    }
  }
}
```


2，添加断言保证getResource()不会再抛出异常了
```java
public class ResourcePool {

  private Stack<Resource> availableList = new Stack<>();
  private Stack<Resource> allocatedList = new Stack<>();

  public Resource getResource() {
    Resource result;

    // 添加测试资源为空的情况
    if (availableList.isEmpty()) {
      result = new DataResourceDemo();
      allocatedList.push(result);
      return result;
    }

    try {
      result = availableList.pop();
      allocatedList.push(result);
      return result;
    } catch (EmptyStackException e) {
      Assert.shouldNeverReachHere("available was empty on pop");
      result = new DataResourceDemo();
      allocatedList.push(result);
      return result;
    }
  }
}

class Assert {
  static void shouldNeverReachHere(String message) {
    throw new RuntimeException(message);
  }
}
```

3,编译并测试，如果一切正常可以将try区段中的代码复制到try区间之外，然后将try区段全部移除。
```java
public class ResourcePool {

  private Stack<Resource> availableList = new Stack<>();
  private Stack<Resource> allocatedList = new Stack<>();

  public Resource getResource() {
    Resource result;

    // 添加测试资源为空的情况
    if (availableList.isEmpty()) {
      result = new DataResourceDemo();
      allocatedList.push(result);
      return result;
    }

    result = availableList.pop();
    allocatedList.push(result);
    return result;
  }
}
```


4,最后再对代码进行整理,代码最终如下:
```java
public class ResourcePool {

  private Stack<Resource> availableList = new Stack<>();
  private Stack<Resource> allocatedList = new Stack<>();

  public Resource getResource() {
    Resource result;

    // 添加测试资源为空的情况
    if (availableList.isEmpty()) {
      result = new DataResourceDemo();
    } else {
      result = availableList.pop();
    }

    allocatedList.push(result);
    return result;
  }
}
```