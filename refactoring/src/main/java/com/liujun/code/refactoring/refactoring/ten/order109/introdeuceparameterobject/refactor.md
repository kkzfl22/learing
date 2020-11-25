#Introduce Parameter Object (引入参数对象)

原因：
经常会看到一组参数总是一起被传递，可能有好几个函数都使用这一组参数，这些函数可能隶属同一个类，也可能隶属不同的类。
这样的一组参数就是所谓的data clumps(数据泥团)，我们可以运用一个对象包装所有这些数据，再以该对象取代他们。
本重构的价值在于缩短参数列，而过长的参数列总是难以理解。

本项重构，还可以带来更多好处。当你把这些参数组织到一起，后往往很快可以发现一些可被移到新建类的行为。


#做法
<ul>
    <li>1,新建一个类，用以表现你想替换的一组参数，将这个类设为不可变的</li>
    <li>2,编译</li>
    <li>3,针对使用该组参数的所有函数，实施Add Parameter,传入上述新建类的实体对象，并将此参数值设为null。</li>
    <li>4,对于Data Clumps中的每一项，从函数签名中移除之，将改调用端和函数本体，令它们都改而通过新的参数对象取得该值。</li>
    <li>5,每去除一个参数，编译并测试。</li>
    <li>6,将原选 的参数全部去除之后，观察有无适当函数可以运用move Method搬移到参数对象之中。</li>
</ul>

#src
```java
/**
 * 使用帐目与帐项的范例
 *
 * @author liujun
 * @version 0.0.1
 */
public class Entry {

  private Date chargeDate;

  private double value;

  public Entry(Date chargeDate, double value) {
    this.chargeDate = chargeDate;
    this.value = value;
  }

  public Date getChargeDate() {
    return chargeDate;
  }

  public double getValue() {
    return value;
  }
}


/**
 * 帐目
 *
 * @author liujun
 * @version 0.0.1
 */
public class Account {

  private List<Entry> dataList = new ArrayList<>();

  public double getFlowBetween(Date start, Date end) {
    double result = 0;

    Iterator<Entry> iterList = dataList.iterator();

    while (iterList.hasNext()) {
      Entry item = iterList.next();
      if (item.getChargeDate().equals(start)
          || item.getChargeDate().equals(end)
          || (item.getChargeDate().after(start) && item.getChargeDate().before(end))) {
        result += item.getValue();
      }
    }

    return result;
  }
}


public class Client {

  public static void main(String[] args) {
    Account dataAccount = new Account();

    Date start = new Date();
    Date end = new Date();

    dataAccount.getFlowBetween(start, end);
  }
}

```




#refactor
1,新建一个表示范围的类
```java
public class DataRange {

  private final Date start;

  private final Date end;

  public DataRange(Date start, Date end) {
    this.start = start;
    this.end = end;
  }

  public Date getStart() {
    return start;
  }

  public Date getEnd() {
    return end;
  }
}
```

2,添加对象至函数的参数中,并替换一个参数
```java
/**
 * 帐目
 *
 * @author liujun
 * @version 0.0.1
 */
public class Account {

  private List<Entry> dataList = new ArrayList<>();

  public double getFlowBetween(Date end, DataRange range) {
    double result = 0;

    Iterator<Entry> iterList = dataList.iterator();

    while (iterList.hasNext()) {
      Entry item = iterList.next();
      if (item.getChargeDate().equals(range.getStart())
          || item.getChargeDate().equals(end)
          || (item.getChargeDate().after(range.getStart()) && item.getChargeDate().before(end))) {
        result += item.getValue();
      }
    }

    return result;
  }
}

public class Client {

  public static void main(String[] args) {
    Account dataAccount = new Account();

    Date start = new Date();
    Date end = new Date();

    DataRange range = new DataRange(start, end);

    dataAccount.getFlowBetween(end, range);
  }
}

```

3,继续重构，直到去掉对象所包含的所有对象
```java
public class Account {

  private List<Entry> dataList = new ArrayList<>();

  public double getFlowBetween(DataRange range) {
    double result = 0;

    Iterator<Entry> iterList = dataList.iterator();

    while (iterList.hasNext()) {
      Entry item = iterList.next();
      if (item.getChargeDate().equals(range.getStart())
          || item.getChargeDate().equals(range.getEnd())
          || (item.getChargeDate().after(range.getStart())
              && item.getChargeDate().before(range.getEnd()))) {
        result += item.getValue();
      }
    }

    return result;
  }
}

public class Client {

  public static void main(String[] args) {
    Account dataAccount = new Account();

    Date start = new Date();
    Date end = new Date();

    DataRange range = new DataRange(start, end);

    dataAccount.getFlowBetween(range);
  }
}
```


4,对于表达式代码，可以使用Extract Method和Move Method
```java
public class DataRange {

  private final Date start;

  private final Date end;

  public DataRange(Date start, Date end) {
    this.start = start;
    this.end = end;
  }

  public Date getStart() {
    return start;
  }

  public Date getEnd() {
    return end;
  }

  /**
   * 提取表达式函数，并转换移方法
   *
   * @param item
   * @return
   */
  public boolean include(Date item) {
    return item.equals(this.start)
        || item.equals(this.end)
        || (item.after(this.start) && item.before(this.end));
  }
}



public class Account {

  private List<Entry> dataList = new ArrayList<>();

  public double getFlowBetween(DataRange range) {
    double result = 0;

    Iterator<Entry> iterList = dataList.iterator();

    while (iterList.hasNext()) {
      Entry item = iterList.next();
      if (range.include(item.getChargeDate())) {
        result += item.getValue();
      }
    }

    return result;
  }
}

```