#Replace Constructor with Factory Method(以工厂函数取代构建函数)

当希望在做创建对象时，不仅仅是简单的构建动作，将构建函数替换为工厂函数。

##原因
1，在派生子类的过程中以工厂函数取代类型码，你可能常常需要根据类型码创建相应的对象。
现在创建名单中还得加上子类，那些子类也是根据类型码来创建

2，如果构建函数的功能不能满足你的需要，也可以使用工厂函数来替代它。

##做法
<ul>
    <li>1,新建一个工厂函数，让它调用现有的构造函数。</li>
    <li>2,将调用构建函数的代码改为调用工厂函数。</li>
    <li>3,每次替换后，编译并测试.</li>
    <li>4,将构建函数声明为private.</li>
    <li>5,编译</li>
</ul>

##src1
根据类型码来创建对象
```java
public class Employee {

  private int type;

  static final int ENGINEER = 0;

  static final int SALESMAN = 1;

  static final int MANAGER = 2;

  Employee(int type) {
    this.type = type;
  }
}

```

##src2
根据字符串创建子类对象
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


##src3
```java
public abstract class Person {

  abstract boolean isMale();

  abstract char getCode();
}


public class Female extends Person {

  @Override
  boolean isMale() {
    return false;
  }

  @Override
  char getCode() {
    return 'F';
  }
}

public class Male extends Person {

  @Override
  boolean isMale() {
    return true;
  }

  @Override
  char getCode() {
    return 'M';
  }
}

```

##refactor1
根据类型码来创建对象

1,创建工厂函数
```java
public class Employee {

  private int type;

  static final int ENGINEER = 0;

  static final int SALESMAN = 1;

  static final int MANAGER = 2;

  Employee(int type) {
    this.type = type;
  }

  /**
   * 创建工厂函数
   *
   * @param type
   * @return
   */
  public static Employee create(int type) {
    return new Employee(type);
  }
}

```

2,调用点改为使用构造函数，及将构造函数声明为private
```java
public class Employee {

  private int type;

  static final int ENGINEER = 0;

  static final int SALESMAN = 1;

  static final int MANAGER = 2;

  /**
   * 构建函数改为private
   *
   * @param type
   */
  private Employee(int type) {
    this.type = type;
  }

  /**
   * 创建工厂函数
   *
   * @param type
   * @return
   */
  public static Employee create(int type) {
    return new Employee(type);
  }

  public static void main(String[] args) {
    Employee emp = Employee.create(Employee.ENGINEER);
  }
}

```

##refactor2
根据字符串创建子类对象
1，使用class.forname来加载加载实例。
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

  /**
   * 添加创建函数
   *
   * @param name
   * @return
   */
  private static EmployeeType create(String name) {
    try {
      return (EmployeeType) Class.forName(name).newInstance();
    } catch (Exception e) {
      throw new IllegalArgumentException("unable to instantiate " + name);
    }
  }

  abstract int payAmount(Employee emp);
}

```

2,让newtype调用新的创建函数
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
        return create("Engineer");
      case SALESMAN:
        return create("Saleman");
      case MANAGER:
        return create("Manager");
      default:
        throw new IllegalArgumentException("Incorrect Employee code");
    }
  }

  /**
   * 添加创建函数
   *
   * @param name
   * @return
   */
  private static EmployeeType create(String name) {
    try {
      return (EmployeeType) Class.forName(name).newInstance();
    } catch (Exception e) {
      throw new IllegalArgumentException("unable to instantiate " + name);
    }
  }

  abstract int payAmount(Employee emp);
}
```

3, 修改调用者，使用create函数，然后移除newType函数
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

  /**
   * 添加创建函数
   *
   * @param name
   * @return
   */
  private static EmployeeType create(String name) {
    try {
      return (EmployeeType) Class.forName(name).newInstance();
    } catch (Exception e) {
      throw new IllegalArgumentException("unable to instantiate " + name);
    }
  }

  abstract int payAmount(Employee emp);

  public static void main(String[] args) {
    EmployeeType employee = EmployeeType.create("Engineer");
    System.out.println(employee);
  }
}
```


4,去除魔鬼数字
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

  static final String ENGINEER_TYPE = "Engineer";

  static final String SALESMAN_TYPE = "Saleman";

  static final String MANAGER_TYPE = "Manager";

  /**
   * 添加创建函数
   *
   * @param name
   * @return
   */
  private static EmployeeType create(String name) {
    try {
      return (EmployeeType) Class.forName(name).newInstance();
    } catch (Exception e) {
      throw new IllegalArgumentException("unable to instantiate " + name);
    }
  }

  abstract int payAmount(Employee emp);

  public static void main(String[] args) {
    EmployeeType employee = EmployeeType.create(EmployeeType.ENGINEER_TYPE);
    System.out.println(employee);
  }
}
```

##refactor3
1,以明确函数创建子类
```java
public abstract class Person {

  abstract boolean isMale();

  abstract char getCode();

  static Person createMale() {
    return new Male();
  }

  static Person createFemale() {
    return new Female();
  }
}
```

2,调用的替换
```java
public abstract class Person {

  abstract boolean isMale();

  abstract char getCode();

  static Person createMale() {
    return new Male();
  }

  static Person createFemale() {
    return new Female();
  }

  public static void main(String[] args) {
    //将这类调用进行替换
    Person kent = new Male();
    //替换为
    Person kents1 = Person.createMale();
  }
}
```

但这于使得超类必须知晓子类。如果想避免这种情况，你需要一个更为复杂的设计。
但绝大多数情况下你并不需要如此复杂的设计，上面的做法已经绰绰有余。