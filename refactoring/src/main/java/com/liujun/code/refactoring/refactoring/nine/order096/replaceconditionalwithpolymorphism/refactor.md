#Replace Conditional with Polymorphism(以多态取代条件表达式)

当有多个条件表达式，它根据对象类型的不同而选择不同的行为。
将这个表达式的第个支持放进一个子类内的覆写函数中，然后将原始函数声明为抽象函数。


##原因
多态最根本的好处就是：如果你需地根据对象的不同类型而采取不同的行为，多态使你不必编写明显的条件表达式。
正因为有了多态，“类型码的swatch语句”以及“基于类型名称的if-then-else语句”在面向对象程序中很少出现。
如果同一组条件表达式在程序这么多地点出现，那么使用多态的收益是最大的。
使用条件表达式时，如果想添加一种新类型，就必须查找并更新所有条件表达式，
但如果改用多态，只需建立一个新的子类，并在其中提供适当的函数就行了。

##做法：
在使用Replace Conditional with Polymorphism之前，必须得到一个继承结构
如果没有就需要建立它
两种办法可以得到继承结构：Replace Type Code with Subclasses和Replace Type code with state/strategy
<ul>
<li>1,如果要处理的表达式是一个更大的函数中的一部分，首先对条件表达式进行分析，然后使用Extract Method将它提练到一个独立函数去。</li>
<li>2，如果有必要，使用move Method将条件表达式放置到继承结构的顶端。</li>
<li>3，任选一个子类，在其中建立一个函数，使之覆写超类中容纳条件表达式的那个函数，
将与该子类相关的条件表达式分支复制到新建函数中，并对它进行适当调整</li>
<li>4，编译，测试</li>
<li>5，在超类中删除条件表达内的复杂的分支，</li>
<li>6，编译，测试。</li>
<li>7，针对条件表达式的每个分支 ，重复上述过程，直到所有支持都被移到了子类内的函数为止。</li>
<li>8，将超类之中的容纳条件表达式的函数声明为抽象函数。</li>
</ul>


##src
以之前的员工和薪资为例
```java
public class Employee {

  private EmployeeType type;

  /** 岗位基本工资 */
  private final int MONTHLY_SALARY = 100;

  /** */
  private final int COMMISSION = 30;

  /** */
  private final int BONUS = 50;

  Employee(int type) {
    this.setType(type);
  }

  int payAmount() {
    switch (this.getType()) {
      case EmployeeType.ENGINEER:
        return MONTHLY_SALARY;
      case EmployeeType.SALESMAN:
        return MONTHLY_SALARY + COMMISSION;
      case EmployeeType.MANAGER:
        return MONTHLY_SALARY + BONUS;
      default:
        throw new RuntimeException("Incorrect Employee");
    }
  }

  public int getMonthlySalary() {
    return MONTHLY_SALARY;
  }

  public int getCommission() {
    return COMMISSION;
  }

  public int getBonus() {
    return BONUS;
  }

  /**
   * 1，封装类型码字段
   *
   * @return
   */
  public int getType() {
    return type.getTypeCode();
  }

  public void setType(int typeArgs) {
    type = EmployeeType.newType(typeArgs);
  }
}



public abstract class EmployeeType {

  /**
   * 获取类型码
   *
   * @return 返回类型信息
   */
  abstract int getTypeCode();

  static final int ENGINEER = 0;

  static final int SALESMAN = 1;

  static final int MANAGER = 2;

  public static EmployeeType newType(int typeArgs) {
    switch (typeArgs) {
      case ENGINEER:
        return new Engineer();
      case SALESMAN:
        return new Saleman();
      case MANAGER:
        return new Manager();
      default:
        throw new IllegalArgumentException("Incorrect Employee code");
    }
  }
}

public class Engineer extends EmployeeType {

  @Override
  int getTypeCode() {
    return EmployeeType.ENGINEER;
  }
}

public class Manager extends EmployeeType {

  @Override
  int getTypeCode() {
    return EmployeeType.MANAGER;
  }
}

public class Saleman extends EmployeeType {

  @Override
  int getTypeCode() {
    return EmployeeType.SALESMAN;
  }
}

```


##refactor
1,将employee的计算逻辑搬移动employeeType中
```java
public abstract class EmployeeType {

  /**
   * 获取类型码
   *
   * @return 返回类型信息
   */
  abstract int getTypeCode();

  static final int ENGINEER = 0;

  static final int SALESMAN = 1;

  static final int MANAGER = 2;

  public static EmployeeType newType(int typeArgs) {
    switch (typeArgs) {
      case ENGINEER:
        return new Engineer();
      case SALESMAN:
        return new Saleman();
      case MANAGER:
        return new Manager();
      default:
        throw new IllegalArgumentException("Incorrect Employee code");
    }
  }

  int payAmount(Employee emp) {
    switch (emp.getType()) {
      case EmployeeType.ENGINEER:
        return emp.getMonthlySalary();
      case EmployeeType.SALESMAN:
        return emp.getMonthlySalary() + emp.getCommission();
      case EmployeeType.MANAGER:
        return emp.getMonthlySalary() + emp.getBonus();
      default:
        throw new RuntimeException("Incorrect Employee");
    }
  }
}


public class Employee {

  private EmployeeType type;

  /** 岗位基本工资 */
  private final int MONTHLY_SALARY = 100;

  /** */
  private final int COMMISSION = 30;

  /** */
  private final int BONUS = 50;

  Employee(int type) {
    this.setType(type);
  }

  int payAmount() {
    return type.payAmount(this);
  }

  public int getMonthlySalary() {
    return MONTHLY_SALARY;
  }

  public int getCommission() {
    return COMMISSION;
  }

  public int getBonus() {
    return BONUS;
  }

  /**
   * 1，封装类型码字段
   *
   * @return
   */
  public int getType() {
    return type.getTypeCode();
  }

  public void setType(int typeArgs) {
    type = EmployeeType.newType(typeArgs);
  }
}

```

2,处理swatch/case语句
```java
public class Engineer extends EmployeeType {

  @Override
  int getTypeCode() {
    return EmployeeType.ENGINEER;
  }

  @Override
  int payAmount(Employee emp) {
    return emp.getMonthlySalary();
  }
}

public abstract class EmployeeType {

  /**
   * 获取类型码
   *
   * @return 返回类型信息
   */
  abstract int getTypeCode();

  static final int ENGINEER = 0;

  static final int SALESMAN = 1;

  static final int MANAGER = 2;

  public static EmployeeType newType(int typeArgs) {
    switch (typeArgs) {
      case ENGINEER:
        return new Engineer();
      case SALESMAN:
        return new Saleman();
      case MANAGER:
        return new Manager();
      default:
        throw new IllegalArgumentException("Incorrect Employee code");
    }
  }

  int payAmount(Employee emp) {
    switch (emp.getType()) {
      case EmployeeType.ENGINEER:
        throw new RuntimeException("shold be being overridden");
      case EmployeeType.SALESMAN:
        return emp.getMonthlySalary() + emp.getCommission();
      case EmployeeType.MANAGER:
        return emp.getMonthlySalary() + emp.getBonus();
      default:
        throw new RuntimeException("Incorrect Employee");
    }
  }
}
```

3,重复上述过程
```java
public abstract class EmployeeType {

  /**
   * 获取类型码
   *
   * @return 返回类型信息
   */
  abstract int getTypeCode();

  static final int ENGINEER = 0;

  static final int SALESMAN = 1;

  static final int MANAGER = 2;

  public static EmployeeType newType(int typeArgs) {
    switch (typeArgs) {
      case ENGINEER:
        return new Engineer();
      case SALESMAN:
        return new Saleman();
      case MANAGER:
        return new Manager();
      default:
        throw new IllegalArgumentException("Incorrect Employee code");
    }
  }

  int payAmount(Employee emp) {
    switch (emp.getType()) {
      case EmployeeType.ENGINEER:
        throw new RuntimeException("shold be being overridden");
      case EmployeeType.SALESMAN:
        throw new RuntimeException("shold be being overridden");
      case EmployeeType.MANAGER:
        throw new RuntimeException("shold be being overridden");
      default:
        throw new RuntimeException("Incorrect Employee");
    }
  }
}


public class Manager extends EmployeeType {

  @Override
  int getTypeCode() {
    return EmployeeType.MANAGER;
  }

  @Override
  int payAmount(Employee emp) {
    return emp.getMonthlySalary() + emp.getBonus();
  }
}

public class Saleman extends EmployeeType {

  @Override
  int getTypeCode() {
    return EmployeeType.SALESMAN;
  }

  @Override
  int payAmount(Employee emp) {
    return emp.getMonthlySalary() + emp.getCommission();
  }
}
```

4，将父类函数payAmount改为抽象函数，必须在子类中生重写
```java
public abstract class EmployeeType {

  /**
   * 获取类型码
   *
   * @return 返回类型信息
   */
  abstract int getTypeCode();

  static final int ENGINEER = 0;

  static final int SALESMAN = 1;

  static final int MANAGER = 2;

  public static EmployeeType newType(int typeArgs) {
    switch (typeArgs) {
      case ENGINEER:
        return new Engineer();
      case SALESMAN:
        return new Saleman();
      case MANAGER:
        return new Manager();
      default:
        throw new IllegalArgumentException("Incorrect Employee code");
    }
  }

  abstract int payAmount(Employee emp);
}
```