## 语言中 “有益”的特性。

>
    语言设计者会试图加入一些方便语言特性，但这件事情并不容易。他们得在易编程与安全性中进行折中。
    有些语言特性一开始看上去的确是“面面俱到”了，然尔当我们想要测试使用这些特性的代码时，残酷的现实就显露出来了。
>

阻止子类化
>
    在java中,final关键字就是用来做这个的，当一个类在安全性方面很敏感时，便可以使用final。
>

## 原始代码

```java

public class HttpFile {

  public List getKsrsStream(HttpFileCollection files) {
    List outList = new ArrayList();

    for (String name : files.getNames()) {
      HttpPostedFile file = files.getPosted(name);
      if (file.getFileName().endsWith(".ksr") || file.getFileName().endsWith(".txt")) {
        outList.add(file.getInputStream());
      }
    }

    return outList;
  }
}


public interface HttpFileCollectionInf {

  /**
   * 获取文件名
   *
   * @return
   */
  List<String> getNames();

  /**
   * 使用名称获取信息
   *
   * @param name
   * @return
   */
  HttpPostedFile getPosted(String name);
}


public final class HttpFileCollection implements HttpFileCollectionInf {

  private HttpFileCollection() {}

  private List<String> names;

  private Map<String, HttpPostedFile> postFile;

  @Override
  public List<String> getNames() {
    return names;
  }

  @Override
  public HttpPostedFile getPosted(String name) {
    return postFile.get(name);
  }
}

public class HttpPostedFile implements HttpPostedFileInf {

  /** 文件名 */
  private String fileName;

  private InputStream inputStream;

  @Override
  public String getFileName() {
    return fileName;
  }

  @Override
  public InputStream getInputStream() {
    return inputStream;
  }
}

public interface HttpPostedFileInf {

  /**
   * 提取公用的接口
   *
   * @return 获取文件名
   */
  String getFileName();

  /**
   * 获取输入流
   *
   * @return
   */
  InputStream getInputStream();
}
```

对于这类不能实例化的类，我们不能使用接口摘取和实现抽取技术。
它还是类库中的类，不能随便去改，所以，只能使用参数适配。
对于示例代码，还是比较幸运的。它却有一个非封装的基类HttpFileCollectionInf，依靠编译器技术。就可以依赖接口而非实现。

新的实现
```java



```

