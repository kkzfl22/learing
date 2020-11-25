## 外覆类


## 实现1
```java
public class LoggingEmployee extends Employee {

  private Employee employee;

  public LoggingEmployee(Employee employee) {
    super(employee.getPayDispatcher());
    this.employee = employee;
  }

  @Override
  public void pay() {
    this.logPayment();
    employee.pay();
  }

  private void logPayment() {
    System.out.println("记录日志的功能");
  }
}

```

## 外覆类
外覆类，在设计模式中称为装饰模式。


## 实现2
```java

public class LoggingEmployee {

  private Employee employee;

  public LoggingEmployee(Employee employee) {
    this.employee = employee;
  }

  public void pay() {
    this.logPayment();
    employee.pay();
  }

  private void logPayment() {
    System.out.println("记录日志的功能");
  }
}

```


## 外覆类的操作步骤，或者叫装饰器模式的步骤
>
    1. 确定修改点。
    2. 如果你的修改可以在一处地方以单块连续的语句序列出现，则新建一个类，该类的构建函数接受被外覆的类的对象作为参数。
    如果无法在测试用例中创建外覆类的实例的话，你可能需要先对被覆类使用实现提取或接口提取技术，以便能够实例化外覆类。
    3. 使用测试驱动开发的方式为你的外覆类编写一个方法，该方法负责完成你想添加进系统中去的工作。编写另一个方法。
    这个方法负责调用刚才创建的那个方法以及被覆类中的那个旧方法。
    4. 在系统中需要使用新行为的地方创建并使用外覆对象。
>
关于新生方法与外覆方法的区别
通常，如果现有方法中的代码将一个清晰的算法传达给了读者，就建议使用新方法。
而如果欲添加的新特性的重要性跟已经存在的特性不相上下，就建议转而使用外覆方法。针对这种情况，完成外覆方法之后，通常会得到一个新的高层算法。


## 外覆类的选择问题。
外覆类的阈值更高一些。
1. 欲添加的行为是完全独立的，并且不希望让低层或者不相关的行为污染现有类。
2. 原类已经够大了，我实在不能想像将它撑得更大会如何. 遇到这种情况，外覆类只是相当于在地上插个木桩，为后面的修改下一个标识。

