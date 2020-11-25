#Pull Up Constructor Body (构建函数本体上移)
你在各个子类中拥有一些构建函数，它们的体体几乎完全一致。
在超类中新建一个构建函数，并在子类构建函数中调用它。

#原因
构建函数是很奇妙的东西，它们不是普通函数，使用它们比使用普通函数受到更多的限制。

#做法
<ul>
    <li>1，在超类中定义一个构造函数。</li>
    <li>2,将子类构造函数中的共同代码搬移到超类构建函数中</li>
    <li>3,将子类构建函数中的共同代码删掉，改而调用新建的超类构建函数。</li>
    <li>4,编译，测试 </li>
</ul>



#src
```java
public class Employee {

  protected String name;

  protected String id;
}

public class Manager extends Employee {

  private int grade;

  public Manager(String name, String id, int grade) {
    this.name = name;
    this.id = id;
    this.grade = grade;
  }
}

```

#refactor:
```java
public class Employee {

  protected String name;

  protected String id;

  protected Employee(String name, String id) {
    this.name = name;
    this.id = id;
  }
}

public class Manager extends Employee {

  private int grade;

  public Manager(String name, String id, int grade) {
    super(name, id);
    this.grade = grade;
  }
}

```