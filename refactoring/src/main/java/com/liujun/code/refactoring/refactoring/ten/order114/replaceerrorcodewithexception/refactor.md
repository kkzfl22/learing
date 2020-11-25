#Replace Error code with Exception(以异常取代错误码)

某个函数返回一个特定的代码，用于表示某种错误情况。改用异常。


#原因：
程序发中发现错误的地方，并不一定知道如何处理错误，当一段子程序发现错误时，它需要让它的调用者知道这个错误，
而调用者也可能将这引错误继续沿着调用链传递下去。
java有一种更好的错误处理方式：异常。之所以更好，因为它清楚的将“普通程序”和“错误处理”分开了，
这使得程序更容易理解。代码的可理解性应该是我们虔诚追求的目标。


##做法
<ul>
    <li>1,决定应该抛出受控(checked)异常还是非受控(unchecked)异常</li>
    <li>2,找到该函数的所有调用者，对它们进行相应的调整，让他们使用异常</li>
    <li>3,修改函数的答名，令它返应出新用法</li>
</ul>

如果函数的许多的调用者，上述的修改过程可能跨度太大。
<ul>
    <li>1,决定应该抛出受控还是非受控异常。</li>
    <li>2,新建一个函数，使用异常来表示错误状态。将旧函数的代码复制到新函数中，做适当的调整。</li>
    <li>3,修改旧的函数本体，让它调用上述新建函数。</li>
    <li>4,逐一修改函数调用者，令其修改为调用新函数，编译并测试。</li>
    <li>5,移除旧函数。</li>
</ul>


##1，非受控异常
```java
public class Account {

  private int balance = ThreadLocalRandom.current().nextInt();

  int withdraw(int amount) {
    if (amount > balance) {
      return -1;
    } else {
      balance -= amount;
      return 0;
    }
  }
}

public class ClientCheck {

  private Account dataAccount = new Account();

  public void use(int money) {
    if (dataAccount.withdraw(money) == -1) {
      handleOverdrawn();
    } else {
      doTheUsualThine();
    }
  }

  private void handleOverdrawn() {
    System.out.println("data out");
  }

  private void doTheUsualThine() {
    System.out.println("do usual thing");
  }
}

```

refactor:
1,修改代码，进行判断检查
```java
public class ClientCheck {

  private Account dataAccount = new Account();

  public void use(int money) {
    if (!dataAccount.canWithdraw(money)) {
      handleOverdrawn();
    } else {
      dataAccount.withdraw(money);
      doTheUsualThine();
    }
  }

  private void handleOverdrawn() {
    System.out.println("data out");
  }

  private void doTheUsualThine() {
    System.out.println("do usual thing");
  }
}
```

2,改为异常判断
```java
public class Account {

  private int balance = ThreadLocalRandom.current().nextInt();

  void withdraw(int amount) {
    if (amount > balance) {
      throw new IllegalArgumentException("Amount too large");
    }

    balance -= amount;
  }

  boolean canWithdraw(int amount) {
    if (amount < balance) {
      return true;
    }
    return false;
  }
}
```

由于这是程序员所犯的错误，使用断言更能清楚的指出这一点
```java
public class Assert {

  static void isTrue(String comment, boolean test) {
    if (!test) {
      throw new IllegalArgumentException("Assertion failed :" + comment);
    }
  }
}

public class Account {

  private int balance = ThreadLocalRandom.current().nextInt();

  void withdraw(int amount) {
    Assert.isTrue("sufficent founds :", amount <= balance);
    balance -= amount;
  }

  boolean canWithdraw(int amount) {
    if (amount < balance) {
      return true;
    }
    return false;
  }
}
```

##非受控异常
1，创建异常
```java
public class BalanceException extends Exception {}
```

2,针对不多的可以直接替换
```java

public class Account {

  private int balance = ThreadLocalRandom.current().nextInt();

  int withdraw(int amount) throws BalanceException {
    if (amount > balance) {
      throw new BalanceException();
    }

    balance -= amount;
    return 0;
  }


}


public class Client {

  private Account dataAccount = new Account();

  public void use(int money) {
    try {
      dataAccount.withdraw(money);
      doTheUsualThine();
    } catch (Exception e) {
      e.printStackTrace();
      handleOverdrawn();
    }
  }

  private void handleOverdrawn() {
    System.out.println("data out");
  }

  private void doTheUsualThine() {
    System.out.println("do usual thing");
  }
}


```

如果修改调者过多，可以借助一个中间函数
```java
public class Account {

  private int balance = ThreadLocalRandom.current().nextInt();

  int withdraw(int amount) {
    if (amount > balance) {
      return -1;
    }

    balance -= amount;
    return 0;
  }

  void newWithdraw(int amount) throws BalanceException {
    if (amount > balance) {
      throw new BalanceException();
    }

    balance -= amount;
  }
}
```
当所有调者都改造完成，旧函数便可移除。
