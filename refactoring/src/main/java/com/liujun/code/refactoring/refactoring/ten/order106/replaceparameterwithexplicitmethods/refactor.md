#Replace Parameter with explicit Methods(以明确的函数取代参数)

如果存在一个函数，其中完全取决于参数值不同，则采取不同行为。
针对该函数的每一个可能值，建立一个独立函数。

#原因
如果某个参数有多种可能的值，而函数内又以这些条件表达式检查这些参数值，并根据不同参数值做出不同的行为，
那请使用本项重构。
为了获得一个清晰的接口，也是值得你使用本项重构的。

##做法
<ul>
<li>1,针对参数的每一种可能值，新建一个明确的函数。</li>
<li>2,修改条件表达式的每个分支，使其调用合适的新函数。</li>
<li>3,修改每个分支后，编译并测试</li>
<li>4,修改原函数的每一个被调用点，改而调用上述的某个合适的新函数</li>
<li>5,编译测试</li>
<li>6,所有调用端都修改完毕后，删除原函数。</li>
</ul>


##src
```java
public abstract class Employee {

  static final int ENGINEER = 0;

  static final int SALESMAN = 1;

  static final int MANAGER = 2;

  /**
   * 提供工厂方法，以执行类的创建
   *
   * @param type
   * @return
   */
  public static Employee create(int type) {
    if (type == ENGINEER) {
      return new Engineer();
    } else if (type == SALESMAN) {
      return new SalesMan();
    } else if (type == MANAGER) {
      return new Manager();
    } else {
      throw new IllegalArgumentException("new create error");
    }
  }

  /**
   * 封装类型码
   *
   * @return
   */
  public abstract int getType();
}

public class Engineer extends Employee {

  @Override
  public int getType() {
    return Employee.ENGINEER;
  }
}


public class Manager extends Employee {

  @Override
  public int getType() {
    return Employee.MANAGER;
  }
}

public class SalesMan extends Employee {

  @Override
  public int getType() {
    return Employee.SALESMAN;
  }
}


public class DataClient {
  void dataCreate() {
    Employee engineer = Employee.create(Employee.ENGINEER);
    Employee manager = Employee.create(Employee.MANAGER);
    Employee salesman = Employee.create(Employee.SALESMAN);
  }
}

```



##refactor

1,在每个子类中添加工厂函数，用于创建对象
```java
public class Engineer extends Employee {

  @Override
  public int getType() {
    return Employee.ENGINEER;
  }

  public static Employee createEngineer()
  {
    return new Engineer();
  }

}

public class Manager extends Employee {

  @Override
  public int getType() {
    return Employee.MANAGER;
  }

  public static Employee createManager() {
    return new Manager();
  }
}

public class SalesMan extends Employee {

  @Override
  public int getType() {
    return Employee.SALESMAN;
  }

  public static Employee createSalesMan() {
    return new SalesMan();
  }
}
```

2,针对创建函数进行修改，使用新函数调用
```java

public abstract class Employee {

  static final int ENGINEER = 0;

  static final int SALESMAN = 1;

  static final int MANAGER = 2;

  /**
   * 提供工厂方法，以执行类的创建
   *
   * @param type
   * @return
   */
  public static Employee create(int type) {
    if (type == ENGINEER) {
      return Engineer.createEngineer();
    } else if (type == SALESMAN) {
      return SalesMan.createSalesMan();
    } else if (type == MANAGER) {
      return Manager.createManager();
    } else {
      throw new IllegalArgumentException("new create error");
    }
  }

  /**
   * 封装类型码
   *
   * @return
   */
  public abstract int getType();
}



```

3，替换create掉用,使用Engineer.createEngineer();
```java
public class DataClient {

    void dataCreate()
    {
        Employee engineer = Engineer.createEngineer();
        Employee manager = Manager.createManager();
        Employee salesman = SalesMan.createSalesMan();
    }
}
```

4,完成之后删除create函数
```java
public abstract class Employee {

  static final int ENGINEER = 0;

  static final int SALESMAN = 1;

  static final int MANAGER = 2;

  /**
   * 封装类型码
   *
   * @return
   */
  public abstract int getType();
}

```