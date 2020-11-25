#remove setting method(移除设值函数)

类中的某个字段应该在创建对象时被设置值，然后不再改变
去掉该 字段的所有设置函数

##原因
如果你为某个字段提供了设值函数，这就暗示这个字段值可以被改变，如果你不希望这个对象创建之后此字段还有机会被改变，
那就不要为它提供设置值（同时将该字段设为final）函数,这样意图会更加清晰，并且可以排除其值被修改的可能性--这种可能性往往是非常大的。

##做法
<ul>
    <li>1,检查设置值函数被使用的情况，看它是否被构造函数调用，或者被构建函数所调用的别一个函数调用</li>
    <li>2,修改构建函数，使用直接访问设值函数所针对的那个变量。</li>
    <li>3,编译，测试</li>
    <li>4,移除这个调值函数，将它所针对字段设为final。</li>
    <li>5,编译，测试.</li>
</ul>


##src
最简单的形式
```java
public class Account {

  private String id;

  public Account(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
```

需要进行运算
```java
public class AccountCount {

  private String id;

  public AccountCount(String id) {
    this.initializedId(id);
  }

  public void initializedId(String id) {
    this.id = "ZZ" + id;
  }

  public String getId() {
    return id;
  }
}
```

在继承关系中
```java
public class AccountInterest extends AccountData {

  private double interestRate;

  public AccountInterest(String id, double interestRate) {
    setId(id);
    this.interestRate = interestRate;
  }
}

class AccountData {
  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}

```


##refactor:

最简形式重构
```java
public class Account {

  private final String id;

  public Account(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}

```

需要运算的形式
```java
public class AccountCount {

  private final String id;

  public AccountCount(String id) {
    this.id = this.initializedIdValue(id);
  }

  public String initializedIdValue(String id) {
    return "ZZ" + id;
  }

  public String getId() {
    return id;
  }
}
```


继承关系中,使用构建函数来传递参数
```java
public class AccountInterest extends AccountData {

  private double interestRate;

  public AccountInterest(String id, double interestRate) {
    super(id);
    this.interestRate = interestRate;
  }
}

class AccountData {
  private String id;

  public AccountData(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}

```


继承关系中，如果不能使用构建函数，使用一个命名良好的函数也是最好的选择。
```java
public class AccountInterest2 extends AccountData2 {

  private double interestRate;

  public AccountInterest2(String id, double interestRate) {
    this.initializedIdValue(id);
    this.interestRate = interestRate;
  }
}

class AccountData2 {
  private String id;

  public String getId() {
    return id;
  }

  public void initializedIdValue(String id) {
    this.id = "ZZ" + id;
  }
}
```

使用集合的设值
将设置函数使用add和remove操作替换。
