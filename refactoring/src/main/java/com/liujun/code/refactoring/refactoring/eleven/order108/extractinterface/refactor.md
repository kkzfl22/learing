#Extract Interface(提练接口)

若干客户使用类接口中的同一子集，或者两个类的接口有部分相同。
将相同的子集提练到一个独立接口中。



##做法：
<ul>
    <li>1,新建一个空接口。</li>
    <li>2,在接口中声明待提练的类的共通操作。</li>
    <li>3,让相关的类实现上述接口。</li>
    <li>4,调整客户端的类型声明。令其使用接口。</li>
</ul>


客户工作时间表，从中计算客户应该支持的费用
#Src
```java
public class DataEmployee {

  public double charge(Employee emp, int days) {
    int base = emp.getRate() * days;

    if (emp.hashSpecialSkill()) {
      return base * 1.05;
    }
    return base;
  }
}

public class Employee {

  private String name;

  private int rate;

  public int getRate() {
    return rate;
  }

  public boolean hashSpecialSkill() {
    return true;
  }
}
```

#refactor
1,声明声明接口
```java
public interface Billable {

  /**
   * 获取利率
   *
   * @return
   */
  int getRate();

  /** @return */
  boolean hashSpecialSkill();
}
```


2,让employee实现接口
```java
public class Employee implements Billable {

  private String name;

  private int rate;

  @Override
  public int getRate() {
    return rate;
  }

  @Override
  public boolean hashSpecialSkill() {
    return true;
  }
}
```

3,修改函数的引用
让使用修改为接口,而非实现类。
```java
public class DataEmployee {

  public double charge(Billable emp, int days) {
    int base = emp.getRate() * days;

    if (emp.hashSpecialSkill()) {
      return base * 1.05;
    }
    return base;
  }
}
```