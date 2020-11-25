#Extract SuperClass(提练超类) 

两个类具有相似的特性。
为这两个类建立一个超类，将相同的特性迁移至超类。

#原因：
重复代码是世界是最糟糕的东西之一。如果你在不同的地方做同一件事情，一旦你需要要修改那些动作，你就得做更多的修改。
重复代码的某种形式：两个类以相同的式方做类似的事情，或者不同的方式做类似的事件。

面向对象为这种重复代码提供了简化机制：
1，使用继承。在建立共同性之前，你往往无法发现这样的共通性。因为经常会是在具有共通性的类出现之后，再开始建立其间的继承结构。
2，Extract Class,
这两种方案的选择其实就是继承与委托之间的选择。
如果两个类可以共享行为，也可以共享接口，那么继承是比较简单的做法，
如果选错了，Replace Inheritance with Delegation这并后悔药可以吃.


##做法
<ul>
    <li>1,为原本的类，新建一个空白抽象类。</li>
    <li>2,运用Pull up Method、Pull up Field、Pull up Constructor,逐一将子类的共同元素上移至超类。</li>
    <li>3,每次上移后，编译并测试。</li>
    <li>4,检查留在子类中的函数，看它们是否还有共通成分，如果有，可以使用Extract Method将共通部分提练出来，
    然后使用Pull Up Method将提练出的函数上移至超类。如果各个子类中的某个函数的整体流程很相似，你也许可以使用Form Template Method.
    </li>
    <li>5,将所有共通元素都上移到超类之后，将子类的所有用户。如果它们只使用共同接口，你就可以把他们请求的对象类型改为超类。</li>
</ul>

##src
```java
public class Employee {

  private String name;

  private String id;

  private int annualCost;

  public Employee(String name, String id, int annualCost) {
    this.name = name;
    this.id = id;
    this.annualCost = annualCost;
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }

  public int getAnnualCost() {
    return annualCost;
  }
}

public class Department {

  private String name;

  private List<Employee> staff = new ArrayList<>();

  public Department(String name) {
    this.name = name;
  }

  public int getTotalAnnualCost() {
    int result = 0;

    Iterator<Employee> dataIter = this.getStaff();

    while (dataIter.hasNext()) {
      Employee empInfo = dataIter.next();
      result += empInfo.getAnnualCost();
    }

    return result;
  }

  public Iterator<Employee> getStaff() {
    return staff.iterator();
  }

  public void addStaff(Employee arg) {
    staff.add(arg);
  }
}

```

#refactor
共同特性：
员工与部门都有名称；
它们都有年度成本，只不过计算方式不同。
1，提练一个空白超类，让两个都继承自此类
```java
public abstract class Party {}

public class Employee extends Party {

  private String name;

  private String id;

  private int annualCost;

  public Employee(String name, String id, int annualCost) {
    this.name = name;
    this.id = id;
    this.annualCost = annualCost;
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }

  public int getAnnualCost() {
    return annualCost;
  }
}

public class Department extends Party {

  private String name;

  private List<Employee> staff = new ArrayList<>();

  public Department(String name) {
    this.name = name;
  }

  public int getTotalAnnualCost() {
    int result = 0;

    Iterator<Employee> dataIter = this.getStaff();

    while (dataIter.hasNext()) {
      Employee empInfo = dataIter.next();
      result += empInfo.getAnnualCost();
    }

    return result;
  }

  public Iterator<Employee> getStaff() {
    return staff.iterator();
  }

  public void addStaff(Employee arg) {
    staff.add(arg);
  }
}

```

2，把公共属性上移。
```java
public abstract class Party {

  private String name;

  public Party(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}


public class Department extends Party {

  private List<Employee> staff = new ArrayList<>();

  public Department(String name) {
    super(name);
  }

  public int getTotalAnnualCost() {
    int result = 0;

    Iterator<Employee> dataIter = this.getStaff();

    while (dataIter.hasNext()) {
      Employee empInfo = dataIter.next();
      result += empInfo.getAnnualCost();
    }

    return result;
  }

  public Iterator<Employee> getStaff() {
    return staff.iterator();
  }

  public void addStaff(Employee arg) {
    staff.add(arg);
  }
}

public class Employee extends Party {

  private String id;

  private int annualCost;

  public Employee(String name, String id, int annualCost) {
    super(name);
    this.id = id;
    this.annualCost = annualCost;
  }

  public String getId() {
    return id;
  }

  public int getAnnualCost() {
    return annualCost;
  }
}
```

3,函数上移，将年度成本的方法上移,但由于计算方式的不同，所以使用抽象方法
```java
public abstract class Party {

  private String name;

  public Party(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  /**
   * 上移公共方法
   *
   * @return
   */
  public abstract int getAnnualCost();
}

public class Department extends Party {

  private List<Employee> staff = new ArrayList<>();

  public Department(String name) {
    super(name);
  }

  @Override
  public int getAnnualCost() {
    int result = 0;

    Iterator<Employee> dataIter = this.getStaff();

    while (dataIter.hasNext()) {
      Employee empInfo = dataIter.next();
      result += empInfo.getAnnualCost();
    }

    return result;
  }

  public Iterator<Employee> getStaff() {
    return staff.iterator();
  }

  public void addStaff(Employee arg) {
    staff.add(arg);
  }
}

public class Employee extends Party {

  private String id;

  private int annualCost;

  public Employee(String name, String id, int annualCost) {
    super(name);
    this.id = id;
    this.annualCost = annualCost;
  }

  public String getId() {
    return id;
  }


  @Override
  public int getAnnualCost() {
    return annualCost;
  }
}

```
