#Introduce Assertion (引入断言)

某一段代码需要对程序状态做出某种假设，以断言方式明确表现出这种假设。

##原因
在程序中，会这样的情况，当条件为真，该代码才能正常的运行。
这样的假设通常在代码中没有明确的表现出来，必须阅读整个算法才能看明白，
使用断言后，可明确标明这些假设。

断言是一个条件表达式，应该总是真，它如果失败了，表示程序员犯了错误，因此断言的失败应该个非受控的异常。
断言绝对不能被系统的其他部分使用，实际上，程序最后的成员往往将断言统统删除
因此，记识“某些东西是个断言”是很重要的

断言可以做为交流与调试的辅助，在交流的角度上，断言可以帮助程序阅读者理解代码所做的假设；
在调试的角度上，断言可以在距离bug最近的地方抓住它们，
当我编写自我测试代码的时候发现，断言在调试方面的帮忙变得不那么重要了。
但我们仍然非常看重它们在交流方面的价值。

做法：
如果程序员不犯错，断言就不应该对系统运行造成任务影响，所以加入断言永远不会影响程序的行为。
注意：不要滥用断言，请不要使它来检查“你认为应该为真”的条件，请只使用它来检查“一定必须为真”的条件 。
滥用断言可能会造成难以维护的重复逻辑。

可以问自己这样一个问题：
如果断言所提示的约束条件不能满足，代码是否仍能正常运行，如果可以把断言拿掉。

还需要注意断言中的重复代码，它们和其他任何地方的重复代码一样不好，可以大胆使用Extract Method去掉那些重复的代码。


推荐使用java的断言
assert关键字，默认情况下不启用，使用-ea可以打开，


#src
```java
public class Employee {

  private double expenseLimit = ThreadLocalRandom.current().nextDouble();

  private static final double ERROR = 0.0d;

  private static final double DEFAULT_VALUE = 0.01d;

  public double getExpenseLimit() {
    return (expenseLimit != ERROR) ? ERROR : DEFAULT_VALUE;
  }
}

```

#refactor
```java

```