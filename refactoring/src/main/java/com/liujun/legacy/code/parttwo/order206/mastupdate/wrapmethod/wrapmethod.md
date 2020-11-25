## 外覆方法

### 场景
>
    当需要向类添加行为时，可以考滤不那么“纠缠”的方式，可使用技术之一就是新生方法，
    还有一项技术有些时候也很有用。这就是外覆方法。
>


## 基本代码
```java

public class Employee {

  private PayDispatcher payDispatcher;

  private List<Long> payCard = new ArrayList<>();

  public Employee(PayDispatcher payDispatcher) {
    this.payDispatcher = payDispatcher;
  }

  /** 执行工资的计算操作 */
  public void pay() {
    long date = this.getDate();
    Memory amount = new Memory();
    for (TimeCard timeCard : getTimeCards()) {
      if (payCard.contains(date)) {
        amount.add(timeCard.getHours() * date);
      }
    }

    payDispatcher.pay(date, amount);
  }

  private long getDate() {
    return System.currentTimeMillis();
  }

  public List<TimeCard> getTimeCards() {
    List<TimeCard> dataList = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      TimeCard timeItem = new TimeCard();
      timeItem.setHours(ThreadLocalRandom.current().nextInt());
      dataList.add(timeItem);
    }

    return dataList;
  }
}

@Getter
@Setter
@ToString
public class Memory {

  private long memory;

  public void add(long value) {
    this.memory += value;
  }
}


public class PayDispatcher {

  public void pay(long dateTime, Memory amount) {
    System.out.println("执行工资的支付操作");
    System.out.println(dateTime);
    System.out.println(amount);
  }
}

@Getter
@Setter
@ToString
public class TimeCard {

  /** 工作的小时数 */
  private int hours;

  /** 薪水时间 */
  private List<Integer> amount = new ArrayList<>();

  public void add(int amount) {
    this.amount.add(amount);
  }
}

```

## 现在需求来了，需要为支付添加一个日志记录，以便日志给某个报表系统。

## 传统的做法
```java
public class Employee {

  private PayDispatcher payDispatcher;

  private List<Long> payCard = new ArrayList<>();

  public Employee(PayDispatcher payDispatcher) {
    this.payDispatcher = payDispatcher;
  }

  /** 执行工资的计算操作 */
  public void pay() {

    // 添加日志的功能
    addLog();

    long date = this.getDate();
    Memory amount = new Memory();
    for (TimeCard timeCard : getTimeCards()) {
      if (payCard.contains(date)) {
        amount.add(timeCard.getHours() * date);
      }
    }

    payDispatcher.pay(date, amount);
  }

  public void addLog() {
    System.out.println("添加日志的功能");
  }

  private long getDate() {
    return System.currentTimeMillis();
  }

  public List<TimeCard> getTimeCards() {
    List<TimeCard> dataList = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      TimeCard timeItem = new TimeCard();
      timeItem.setHours(ThreadLocalRandom.current().nextInt());
      dataList.add(timeItem);
    }

    return dataList;
  }
}


```

## 使用外覆方法的方式

```java
public class Employee {

  private PayDispatcher payDispatcher;

  private List<Long> payCard = new ArrayList<>();

  public Employee(PayDispatcher payDispatcher) {
    this.payDispatcher = payDispatcher;
  }

  /** 执行工资的计算操作 */
  public void pay() {

    // 添加日志的功能
    addLog();

    // 支付操作
    this.payMoney();
  }

  public void addLog() {
    System.out.println("添加日志的功能");
  }

  public void payMoney() {
    long date = this.getDate();
    Memory amount = new Memory();
    for (TimeCard timeCard : getTimeCards()) {
      if (payCard.contains(date)) {
        amount.add(timeCard.getHours() * date);
      }
    }

    payDispatcher.pay(date, amount);
  }

  private long getDate() {
    return System.currentTimeMillis();
  }

  public List<TimeCard> getTimeCards() {
    List<TimeCard> dataList = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      TimeCard timeItem = new TimeCard();
      timeItem.setHours(ThreadLocalRandom.current().nextInt());
      dataList.add(timeItem);
    }

    return dataList;
  }
}


```

>
    这是外覆方法的使用方式1 
    创建一个与原方法同名的新方法，并在新方法中调用更名后的原方法，当想为原方法的既有行为添加行为时，就可采用这种做法
>


## 外覆方法还有另外一种情形。
如果只是想增加一个尚未有任何调用的新方法，就可以采用这一形式。

还是前面的例子，如果想要将日志显示暴露出来，则可以增加一个makeLoggedPayment方法
```java
public class Employee {

  private PayDispatcher payDispatcher;

  private List<Long> payCard = new ArrayList<>();

  public Employee(PayDispatcher payDispatcher) {
    this.payDispatcher = payDispatcher;
  }

  /** 创建日志并支付工资 */
  public void makeLoggedPayment() {
    this.addLog();
    this.pay();
  }

  /** 执行工资的计算操作 */
  public void pay() {
    long date = this.getDate();
    Memory amount = new Memory();
    for (TimeCard timeCard : getTimeCards()) {
      if (payCard.contains(date)) {
        amount.add(timeCard.getHours() * date);
      }
    }

    payDispatcher.pay(date, amount);
  }

  public void addLog() {
    System.out.println("添加日志的功能");
  }

  private long getDate() {
    return System.currentTimeMillis();
  }

  public List<TimeCard> getTimeCards() {
    List<TimeCard> dataList = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      TimeCard timeItem = new TimeCard();
      timeItem.setHours(ThreadLocalRandom.current().nextInt());
      dataList.add(timeItem);
    }

    return dataList;
  }
}

```

这两种方式自由选择。

## 方式1
### 外覆方法的实施
>
    1. 确实待修改的方法。
    2. 如果你的修改可以在一处地方以单块连续的语句出现，那么将修改方法重命名，并使用原先的名字和签名创建一个新方法。
    在这么做的时候要记住要保持签名
    3. 在新方法中调用重命名后的原方法。
    4. 为欲添加的新特性编写一个方法，并在第二步创建的新方法中调用这个方法。
>

## 方式2
### 外覆方法的第二种运用形式

>
    1. 确实待修改的方法
    2. 如果你的修改可以在一处地方以单块连续的语句出现，那么用测试驱动的开发方法编写一个新方法来容纳新的特性。
    3. 创建另一个函数来调用新旧两个方法.
>

## 优点
>
    1. 当我们没法为调用代码编写测试时，外覆方法是将新的、经过测试的功能添加进应用的好途径。新生方法和亲生类都会添加到现有方法中，
    至少增加一行，而外覆方法则不会增加现有方法的体积。
    2. 外覆方法的另一个好处就是它显示的使新功能独立于现有功能。 为某一目的而作的代码不会跟另一意图的代码相互纠缠在一起。
>


## 缺点
>
    它可能会导致糟糕的命名。
>
