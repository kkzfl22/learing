#replace Nested Conditional with guard clauses（以卫语句取代嵌套条件表达式）
函数中的条件逻辑使用难以看清正常的执行路径，使用卫语句表现所有的特殊情况。

##动机
#####条件表达式通常有两种表现形式
#####1，所有分支都属性正常行为
#####2，条件表达式中提供的答案中只有一种是正常的行为，其他都是不常见的情况。
如果某个条件极其罕见，就应该单独检查该条件，并在该条件为真时立刻从函数中返回。
这样的单独检查常常被称为“卫语句”

#####replace Nested Conditional with guard clauses精髓:
给某一条分支以特别的重视。如果使用if-then-else结构，你对if支持和else分支的重视是同等的。
这样的代码结构传递给阅读者的消息就是：各个分支有同样的重要性。
卫语句就不同了，它告诉阅读者“这种情况很罕见，如果它真地发生了，请做一些必要的整理工作，然后退出”。


##做法
对于第个检查，放进一个卫语句
每次检查条件检查替换成卫语句，编译并测试

#src:
```java
public class DataPayCount {

  double getPayAmount() {
    double result;

    if (isDead()) {
      result = deadAmount();
    } else {
      if (isSeparated()) {
        result = separatedAmount();
      } else {
        if (isRetired()) {
          result = retiredAmount();
        } else {
          result = normalPayAmount();
        }
      }
    }
    return result;
  }

  private boolean isDead() {
    return ThreadLocalRandom.current().nextBoolean();
  }

  private double deadAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private boolean isSeparated() {
    return ThreadLocalRandom.current().nextBoolean();
  }

  private double separatedAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private boolean isRetired() {
    return ThreadLocalRandom.current().nextBoolean();
  }

  private double retiredAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private double normalPayAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }
}
```


#refactor:
1，引入卫语句，对代码进行改造
```java
public class DataPayCount {

  double getPayAmount() {
    double result;

    // 使用卫语句，对代码进行改造
    if (isDead()) {
      return deadAmount();
    } else {
      if (isSeparated()) {
        result = separatedAmount();
      } else {
        if (isRetired()) {
          result = retiredAmount();
        } else {
          result = normalPayAmount();
        }
      }
    }
    return result;
  }

  private boolean isDead() {
    return ThreadLocalRandom.current().nextBoolean();
  }

  private double deadAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private boolean isSeparated() {
    return ThreadLocalRandom.current().nextBoolean();
  }

  private double separatedAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private boolean isRetired() {
    return ThreadLocalRandom.current().nextBoolean();
  }

  private double retiredAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private double normalPayAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }
}
```


2,将else替换为卫语句
```java
public class DataPayCount {

  double getPayAmount() {
    double result;

    // 使用卫语句，对代码进行改造
    if (isDead()) {
      return deadAmount();
    }
    // 去掉else进行检查
    if (isSeparated()) {
      return separatedAmount();
    } else {
      if (isRetired()) {
        result = retiredAmount();
      } else {
        result = normalPayAmount();
      }
    }

    return result;
  }

  private boolean isDead() {
    return ThreadLocalRandom.current().nextBoolean();
  }

  private double deadAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private boolean isSeparated() {
    return ThreadLocalRandom.current().nextBoolean();
  }

  private double separatedAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private boolean isRetired() {
    return ThreadLocalRandom.current().nextBoolean();
  }

  private double retiredAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private double normalPayAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }
}
```

3,将所有语句改成卫语句,
```java
public class DataPayCount {

  double getPayAmount() {
    double result;

    // 使用卫语句，对代码进行改造
    if (isDead()) {
      return deadAmount();
    }
    // 去掉else进行检查
    if (isSeparated()) {
      return separatedAmount();
    }

    // 继续检查
    if (isRetired()) {
      return retiredAmount();
    }

    result = normalPayAmount();

    return result;
  }

  private boolean isDead() {
    return ThreadLocalRandom.current().nextBoolean();
  }

  private double deadAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private boolean isSeparated() {
    return ThreadLocalRandom.current().nextBoolean();
  }

  private double separatedAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private boolean isRetired() {
    return ThreadLocalRandom.current().nextBoolean();
  }

  private double retiredAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private double normalPayAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }
}
```


4,去掉多余的变量
```java

public class DataPayCount {

  double getPayAmount() {

    // 使用卫语句，对代码进行改造
    if (isDead()) {
      return deadAmount();
    }
    // 去掉else进行检查
    if (isSeparated()) {
      return separatedAmount();
    }

    // 继续检查
    if (isRetired()) {
      return retiredAmount();
    }

    return normalPayAmount();
  }

  private boolean isDead() {
    return ThreadLocalRandom.current().nextBoolean();
  }

  private double deadAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private boolean isSeparated() {
    return ThreadLocalRandom.current().nextBoolean();
  }

  private double separatedAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private boolean isRetired() {
    return ThreadLocalRandom.current().nextBoolean();
  }

  private double retiredAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private double normalPayAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }
}
```


#条件反转
src:
```java
public class DataCondition {

  private double income = ThreadLocalRandom.current().nextDouble();

  private double duration = ThreadLocalRandom.current().nextDouble();

  private static final double ADJ_FACTOR = 0.982;

  public double getAdjustedCapital() {
    double result = 0.0;

    if (capital() > 0.0) {
      if (intRate() > 0.0 && duration() > 0.0) {
        result = (income * duration) * ADJ_FACTOR;
      }
    }

    return result;
  }

  private double capital() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private double intRate() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private double duration() {
    return ThreadLocalRandom.current().nextDouble();
  }
}
```


refactor:
#将条件返转，去掉一个层判断
```java
public class DataCondition {

  private double income = ThreadLocalRandom.current().nextDouble();

  private double duration = ThreadLocalRandom.current().nextDouble();

  private static final double ADJ_FACTOR = 0.982;

  public double getAdjustedCapital() {
    double result = 0.0;

    // 将条件返转，去掉了一层判断
    if (capital() <= 0.0) {
      return result;
    }

    if (intRate() > 0.0 && duration() > 0.0) {
      result = (income * duration) * ADJ_FACTOR;
    }

    return result;
  }

  private double capital() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private double intRate() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private double duration() {
    return ThreadLocalRandom.current().nextDouble();
  }
}
```

#继续条件反转
```java
public class DataCondition {

  private double income = ThreadLocalRandom.current().nextDouble();

  private double duration = ThreadLocalRandom.current().nextDouble();

  private static final double ADJ_FACTOR = 0.982;

  public double getAdjustedCapital() {
    double result = 0.0;

    // 将条件返转，去掉了一层判断
    if (capital() <= 0.0) {
      return result;
    }
    // 再次将条件反转，返回
    if (!(intRate() > 0.0 && duration() > 0.0)) {
      return result;
    }

    result = (income * duration) * ADJ_FACTOR;
    return result;
  }

  private double capital() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private double intRate() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private double duration() {
    return ThreadLocalRandom.current().nextDouble();
  }
}

```


#3，取反这种操作不太直接，脑子容易乱，进行修改
```java
public class DataCondition {

  private double income = ThreadLocalRandom.current().nextDouble();

  private double duration = ThreadLocalRandom.current().nextDouble();

  private static final double ADJ_FACTOR = 0.982;

  public double getAdjustedCapital() {
    double result = 0.0;

    // 将条件返转，去掉了一层判断
    if (capital() <= 0.0) {
      return result;
    }
    // 去掉取反操作
    if (intRate() <= 0.0 || duration() <= 0.0) {
      return result;
    }

    result = (income * duration) * ADJ_FACTOR;
    return result;
  }

  private double capital() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private double intRate() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private double duration() {
    return ThreadLocalRandom.current().nextDouble();
  }
}
```

#4,去掉临时变量
```java
public class DataCondition {

  private double income = ThreadLocalRandom.current().nextDouble();

  private double duration = ThreadLocalRandom.current().nextDouble();

  private static final double ADJ_FACTOR = 0.982;

  public double getAdjustedCapital() {

    // 将条件返转，去掉了一层判断
    if (capital() <= 0.0) {
      return 0.0;
    }
    // 去掉取反操作
    if (intRate() <= 0.0 || duration() <= 0.0) {
      return 0.0;
    }

    return (income * duration) * ADJ_FACTOR;
  }

  private double capital() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private double intRate() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private double duration() {
    return ThreadLocalRandom.current().nextDouble();
  }
}
```