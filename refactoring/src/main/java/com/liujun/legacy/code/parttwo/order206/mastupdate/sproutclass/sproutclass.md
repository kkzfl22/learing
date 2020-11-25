# 场景
>
    你要修改一个类，但想尽一切办法也不可能在合理的时间期限之内在测试用例中被实例化。
    这就表示你不可能在这个类上创建新生方法并为其编写测试。
>


## 示例代码
```java

public class QuarterlyReportGenerator {

  public String generate() {
    List<Result> results = this.queryResults();

    StringBuilder pageText = new StringBuilder();

    pageText.append("<html>");
    pageText.append("<head>");
    pageText.append("<title>").append("Quarterly Report").append("</title>");
    pageText.append("</head>");
    pageText.append("<body>");
    pageText.append("<table>");
    if (results != null && !results.isEmpty()) {

      for (Result resultItem : results) {
        pageText.append("<tr>");
        pageText.append("<td>").append(resultItem.getDepartment()).append("</td>");
        pageText.append("<td>").append(resultItem.getManager()).append("</td>");
        pageText.append("<td>").append(resultItem.getNetProfit()/100).append("</td>");
        pageText.append("<td>").append(resultItem.getOperatingExpense()/100).append("</td>");
        pageText.append("</tr>");
      }
    } else {
      pageText.append("No Result For this Period");
    }
    pageText.append("</table>");
    pageText.append("</body>");
    pageText.append("</html>");

    return pageText.toString();
  }

  private List<Result> queryResults() {

    int size = ThreadLocalRandom.current().nextInt(2, 20);
    List<Result> dataList = new ArrayList<>(size);

    for (int i = 0; i < size; i++) {
      Result result = new Result();
      result.setDepartment(RandomStringUtils.randomAlphabetic(5));
      result.setManager(RandomStringUtils.randomAlphabetic(5));
      result.setNetProfit(ThreadLocalRandom.current().nextInt());
      result.setOperatingExpense(ThreadLocalRandom.current().nextInt());
      dataList.add(result);
    }

    return dataList;
  }
}

```

## 需求，给生成的html添加一个标题栏
传统的做法，包括我，一般也会这么干

```java
public class QuarterlyReportGenerator {

  public String generate() {
    List<Result> results = this.queryResults();

    StringBuilder pageText = new StringBuilder();

    pageText.append("<html>");
    pageText.append("<head>");
    pageText.append("<title>").append("Quarterly Report").append("</title>");
    pageText.append("</head>");
    pageText.append("<body>");
    pageText.append("<table>");
    if (results != null && !results.isEmpty()) {

      // 直接在代码中添加，这是传统的代码修改方式
      pageText.append("<tr>");
      pageText.append("<td>").append("Department").append("</td>");
      pageText.append("<td>").append("Manager").append("</td>");
      pageText.append("<td>").append("Profit").append("</td>");
      pageText.append("<td>").append("Expenses").append("</td>");
      pageText.append("<tr>");

      for (Result resultItem : results) {
        pageText.append("<tr>");
        pageText.append("<td>").append(resultItem.getDepartment()).append("</td>");
        pageText.append("<td>").append(resultItem.getManager()).append("</td>");
        pageText.append("<td>").append(resultItem.getNetProfit() / 100).append("</td>");
        pageText.append("<td>").append(resultItem.getOperatingExpense() / 100).append("</td>");
        pageText.append("</tr>");
      }
    } else {
      pageText.append("No Result For this Period");
    }
    pageText.append("</table>");
    pageText.append("</body>");
    pageText.append("</html>");

    return pageText.toString();
  }

  private List<Result> queryResults() {

    int size = ThreadLocalRandom.current().nextInt(2, 20);
    List<Result> dataList = new ArrayList<>(size);

    for (int i = 0; i < size; i++) {
      Result result = new Result();
      result.setDepartment(RandomStringUtils.randomAlphabetic(5));
      result.setManager(RandomStringUtils.randomAlphabetic(5));
      result.setNetProfit(ThreadLocalRandom.current().nextInt());
      result.setOperatingExpense(ThreadLocalRandom.current().nextInt());
      dataList.add(result);
    }

    return dataList;
  }
}

```


## 使用新生类的方式

1. 声明接口,并实现
```java
public interface QuarterlyReportTableHeaderProducer {

  /**
   * 生成头的方法
   *
   * @return
   */
  String makeHeader();
}

public class QuarterlyReportTableProducer implements QuarterlyReportTableHeaderProducer {

  @Override
  public String makeHeader() {
    StringBuilder outHeader = new StringBuilder();
    outHeader.append("<tr>");
    outHeader.append("<td>").append("Department").append("</td>");
    outHeader.append("<td>").append("Manager").append("</td>");
    outHeader.append("<td>").append("Profit").append("</td>");
    outHeader.append("<td>").append("Expenses").append("</td>");
    outHeader.append("<tr>");
    return outHeader.toString();
  }
}

```

2. 在原方法中使用它
```java

public class QuarterlyReportGenerator {

  private QuarterlyReportTableHeaderProducer makeHeader;

  public QuarterlyReportGenerator(QuarterlyReportTableHeaderProducer makeHeader) {
    this.makeHeader = makeHeader;
  }

  public String generate() {
    List<Result> results = this.queryResults();

    StringBuilder pageText = new StringBuilder();

    pageText.append("<html>");
    pageText.append("<head>");
    pageText.append("<title>").append("Quarterly Report").append("</title>");
    pageText.append("</head>");
    pageText.append("<body>");
    pageText.append("<table>");
    if (results != null && !results.isEmpty()) {
      // 在类中使用它
      pageText.append(makeHeader.makeHeader());
      for (Result resultItem : results) {
        pageText.append("<tr>");
        pageText.append("<td>").append(resultItem.getDepartment()).append("</td>");
        pageText.append("<td>").append(resultItem.getManager()).append("</td>");
        pageText.append("<td>").append(resultItem.getNetProfit() / 100).append("</td>");
        pageText.append("<td>").append(resultItem.getOperatingExpense() / 100).append("</td>");
        pageText.append("</tr>");
      }
    } else {
      pageText.append("No Result For this Period");
    }
    pageText.append("</table>");
    pageText.append("</body>");
    pageText.append("</html>");

    return pageText.toString();
  }

  private List<Result> queryResults() {

    int size = ThreadLocalRandom.current().nextInt(2, 20);
    List<Result> dataList = new ArrayList<>(size);

    for (int i = 0; i < size; i++) {
      Result result = new Result();
      result.setDepartment(RandomStringUtils.randomAlphabetic(5));
      result.setManager(RandomStringUtils.randomAlphabetic(5));
      result.setNetProfit(ThreadLocalRandom.current().nextInt());
      result.setOperatingExpense(ThreadLocalRandom.current().nextInt());
      dataList.add(result);
    }

    return dataList;
  }
}

```

说明
>
    到此为止，你肯定觉得这是在搞笑，就为了一么一点小事，就去创建一个新的类，太荒唐!
    这只是一个小得不能再小的类，在设计上也没有带来任务的好处，而且它还引入了一个接口，使代码更混乱了。
    到目前为止，确实如此，但请注意，之所以这么做，唯一的目的就是为了摆脱一个恶劣的依赖环境。
>


## 现在对接口进行重构
```java
public interface QuarterlyReportTableHeaderGenerator {

  /**
   * 生成头的方法
   *
   * @return
   */
  String generate();
}
```

这样这个类是不是就是我们所熟悉的一部分了
QuarterlyReportTableHeaderGenerator.java和QuarterlyReportGenerator一样是生成器，返回都返回字符串


## 最后完成之后，就变成了如下代码 
```java

public interface QuarterlyReportTableHeaderGenerator {

  /**
   * 生成头的方法
   *
   * @return
   */
  String generate();
}

public class QuarterlyReportTableGenerator implements QuarterlyReportTableHeaderGenerator {

  @Override
  public String generate() {
    StringBuilder outHeader = new StringBuilder();
    outHeader.append("<tr>");
    outHeader.append("<td>").append("Department").append("</td>");
    outHeader.append("<td>").append("Manager").append("</td>");
    outHeader.append("<td>").append("Profit").append("</td>");
    outHeader.append("<td>").append("Expenses").append("</td>");
    outHeader.append("<tr>");
    return outHeader.toString();
  }
}


public class QuarterlyReportGenerator {

  private QuarterlyReportTableHeaderGenerator makeHeader;

  public QuarterlyReportGenerator(QuarterlyReportTableHeaderGenerator makeHeader) {
    this.makeHeader = makeHeader;
  }

  public String generate() {
    List<Result> results = this.queryResults();

    StringBuilder pageText = new StringBuilder();

    pageText.append("<html>");
    pageText.append("<head>");
    pageText.append("<title>").append("Quarterly Report").append("</title>");
    pageText.append("</head>");
    pageText.append("<body>");
    pageText.append("<table>");
    if (results != null && !results.isEmpty()) {
      // 在类中使用它
      pageText.append(makeHeader.generate());
      for (Result resultItem : results) {
        pageText.append("<tr>");
        pageText.append("<td>").append(resultItem.getDepartment()).append("</td>");
        pageText.append("<td>").append(resultItem.getManager()).append("</td>");
        pageText.append("<td>").append(resultItem.getNetProfit() / 100).append("</td>");
        pageText.append("<td>").append(resultItem.getOperatingExpense() / 100).append("</td>");
        pageText.append("</tr>");
      }
    } else {
      pageText.append("No Result For this Period");
    }
    pageText.append("</table>");
    pageText.append("</body>");
    pageText.append("</html>");

    return pageText.toString();
  }

  private List<Result> queryResults() {

    int size = ThreadLocalRandom.current().nextInt(2, 20);
    List<Result> dataList = new ArrayList<>(size);

    for (int i = 0; i < size; i++) {
      Result result = new Result();
      result.setDepartment(RandomStringUtils.randomAlphabetic(5));
      result.setManager(RandomStringUtils.randomAlphabetic(5));
      result.setNetProfit(ThreadLocalRandom.current().nextInt());
      result.setOperatingExpense(ThreadLocalRandom.current().nextInt());
      dataList.add(result);
    }

    return dataList;
  }
}


```



## 关于新生类的使用情况：
1. 所要进行的修改迫使你为某个类添加一个全新的职责。
2. 我们想要添加的只是一点小小的功能，可以将它放入一个现有的类中，但问题是我们无法将这个类放入测试用全例。
这两种情况的判断的判断在于动机不同
一个是为了避免职责混淆。
另一个则是因为原类无法放入测试用例。


## 新生类技术的步骤
>
    1. 确实修改点。
    2. 如果你的修改可以在一个方法中的一处地方以单块连续语句出现，那么用一个类来完成这些工作，并为这个类起一个恰当的名字。
    然后，在修改点插入代码创建该类的对象,调用其上的方法，然后将刚插入的这几行代码注释掉。
    3. 确实定需要原方法的哪些局部变量，并将它们做为参数传递给新类的构造函数。
    4. 确实新生类是否需要返回什么值给原方法，如果需要，则在该类中提供一个相应的方法。并在原方法中插入对它的调用来获取返回值。
    5. 使用测试驱动开发的方式来开发这个新类。
    6. 使用原方法中被注释的几行代码重新生效。
>


## 优点
>
    在侵入性较强的修改时有更大的自信去去继续开展自己的工作。
>

## 缺点
>
    它可能会使系统中的概念复杂化。
>