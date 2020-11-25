#Pull Up Method(函数上移)

有些函数，在各子类中产生完全相同的结果。将该函数移至超类。

##原因
避免行为重复是很重要的，尽管重复的两个函数也可以各自工作的很好，但重复自身只会成为错误的滋生地，此外别无价值。无论何时，只要系统之内出现重复，
你就会面临“修改其中一个却未能修另一个”的风险，通常，找出重复也有有一定的困难。

有一种特殊的情况，也需要使用Pull Up Method,子类的函数覆写了超类的函数，但却仍然做相同的工作。

Pull Up Method过程中最麻烦的一点就是：被提升的函数可能会引用只出现于子类则不出现于超类的特性。如果被引用的是个函数，你可以将函数也一同提升到超类。
或者在超类中建立一个抽象函数。在此过程中，你可能需要修改某个函数的签名，或建立一个委托函数。

如果两个函数相似但不相同，或者可以先借助form Template Method构建出相同的函数，然后再提升他们。


##做法
<ul>
    <li>1,检查待提升函数，确定它们是完全一致的</li>
    <li>2,如果待提升的签名不同，将那些签名都修改为你想要在超类中使用签名。</li>
    <li>3,在超类中新建一个函数，将某一个待提升函数的代码复制到其中，做适当调整，然后编译。</li>
    <li>4,移除一个待提升的子类函数</li>
    <li>5,逐一移除提升的子类函数，直到只剩下超类中的函数为止，每次移除之后都需要逐一测试。</li>
    <li>6,观察该函数的调用者，看着是否改用超类类型的对象。</li>
</ul>

##src:
```java
public class Customer {

  private long lastBillDate;

  public void addBill(Date data) {
    System.out.println("parent add bill");
  }

  public long getLastBillDate() {
    return lastBillDate;
  }
}

public class PreferredCustomer extends Customer {

  void createBill(Date data) {
    double chargeAmount = chargeFor(getLastBillDate(), data);
    this.addBill(data);
  }

  private double chargeFor(long lastBillDate, Date date) {
    System.out.println("last :" + lastBillDate + date);
    return lastBillDate + 11;
  }
}

public class RegularCustomer extends Customer {

  void createBill(Date data) {
    double chargeAmount = chargeFor(getLastBillDate(), data);
    this.addBill(data);
  }

  private double chargeFor(long lastBillDate, Date date) {
    System.out.println("last :" + lastBillDate + date);
    return lastBillDate + 22;
  }
}
```

#Refactor
在子类中createBill做的事情基本一致，就chargeFor不一致，在父类中创建chargeFor抽象方法
```java
public abstract class Customer {

  private long lastBillDate;

  public void addBill(Date data) {
    System.out.println("parent add bill");
  }

  protected abstract double chargeFor(long lastBillDate, Date date);

  public long getLastBillDate() {
    return lastBillDate;
  }
}

```


2，将createBill方法抽取到父类中
```java
public abstract class Customer {

  private long lastBillDate;

  /**
   * 公共方法上移至你类中
   *
   * @param data
   */
  void createBill(Date data) {
    double chargeAmount = chargeFor(getLastBillDate(), data);
    this.addBill(data);
  }

  public void addBill(Date data) {
    System.out.println("parent add bill");
  }

  /**
   * 抽取抽象方法，供子类实现
   *
   * @param lastBillDate
   * @param date
   * @return
   */
  protected abstract double chargeFor(long lastBillDate, Date date);

  public long getLastBillDate() {
    return lastBillDate;
  }
}


public class PreferredCustomer extends Customer {

  @Override
  protected double chargeFor(long lastBillDate, Date date) {
    System.out.println("last :" + lastBillDate + date);
    return lastBillDate + 11;
  }
}


public class RegularCustomer extends Customer {

  @Override
  protected double chargeFor(long lastBillDate, Date date) {
    System.out.println("last :" + lastBillDate + date);
    return lastBillDate + 22;
  }
}
```