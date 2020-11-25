#Extract Subclass (提练子类)

类中的某些特性只被某些（而非全部）实例用到，
新建一个子类，将上面的所说的那一部分特性移到子类中。


##原因
发现类中的某些行为只被一部分实例用到，其他实例不需要使用它们。

Extract class 是 Extract subclass之外的别一种选择。
两者之间的抉择其实就是委托和继承之彰的抉择。
Extract Subclass：更容易进行，但它也有限制：一旦对象创建完成，你无法再改变与类型相关的行为。
但如果使用Extract Class，你只需要插入别一个组件 ，就可以改变对象的行为。
子类只能用于表现一些组变化。
如果希望一个类以几种不同的方式变化，那就必须使用委托。

##做法：
<ul>
    <li>1,为源类定义一个新的子类。</li>
    <li>2,为这个新子类提供构建函数。</li>
    <li>3，找出调用超类构建函数的所有地点，如果它们需要的是新建的子类，令它们改而调用新的构建函数。</li>
    <li>4，逐一使用Push Down Method和Push Down field 将源类的特性移到子类去。</li>
    <li>5，找到所有这样的这样的字段。它们所传达的信息如今由继续体系传达。以Self Encapsulate Field避免直接使用这些字段。
        将它们的取值函数替换为多态常量函数。所有使用这些字段的地方都应该以Replace Conditional with Polymorphism重构。
    </li>
    <li>6，每次下移之后，编译并测试。</li>
</ul>

##src
用JobItem类，用来决定当地修车厂的工作报价。
```java
public class JobItem {

  private int unitPrice;

  private int quantity;

  private boolean isLabor;

  private Employee employee;

  public JobItem(int unitPrice, int quantity, boolean isLabor, Employee employee) {
    this.unitPrice = unitPrice;
    this.quantity = quantity;
    this.isLabor = isLabor;
    this.employee = employee;
  }

  public int getTotalPrice() {
    return getUnitPrice() * quantity;
  }

  public int getUnitPrice() {
    return (isLabor) ? employee.getRate() : unitPrice;
  }

  public int getQuantity() {
    return quantity;
  }

  public Employee getEmployee() {
    return employee;
  }
}

public class Employee {

  private int rate;

  public Employee(int rate) {
    this.rate = rate;
  }

  public int getRate() {
    return rate;
  }
}
```


##Refactor
提练出一个LaborItem子类，因为上述某些行为和数据只在按工时收费的情况下才需要。

1,新建一个子类，将按工时收费的信息转换到子类中。
```java
public class LaborItem extends JobItem {

  public LaborItem(int unitPrice, int quantity, boolean isLabor, Employee employee) {
    super(unitPrice, quantity, isLabor, employee);
  }
}
```

2，将相关字段下移
```java
public class JobItem {

  private int unitPrice;

  private int quantity;

  public JobItem(int unitPrice, int quantity) {
    this.unitPrice = unitPrice;
    this.quantity = quantity;
  }

  public int getTotalPrice() {
    return getUnitPrice() * quantity;
  }

  public int getUnitPrice() {
    return unitPrice;
  }

  protected boolean isLabor() {
    return false;
  }

  public int getQuantity() {
    return quantity;
  }
}

public class LaborItem extends JobItem {

  private Employee employee;

  public LaborItem(int unitPrice, int quantity, Employee employee) {
    super(unitPrice, quantity);
  }

  public Employee getEmployee() {
    return employee;
  }

  @Override
  public int getUnitPrice() {
    return employee.getRate();
  }

  @Override
  protected boolean isLabor() {
    return true;
  }
}
```