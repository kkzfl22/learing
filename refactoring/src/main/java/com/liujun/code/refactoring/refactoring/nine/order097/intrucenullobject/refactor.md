#Introduce Null Object(引入null对象)
你需要再三的检查某对象是否为null，将null值替换为null对象

##原因：
多态的最根本的好处在于：你不必再向对象询问“你是什么类型”而后根据得到的答案调用对象的某个行为--你只管调用就是了
其他一切多态机制会为你安排妥当，当某个字段内容是null时，多态可以扮演别一个较不直观的用途。

##做法
1，为源类建立一个子类，使其行为说法像是源类的null版本。在源类和null子类中都加上isNull函数，前进的isNull返回false，后者的isNull返回true
2，编译
3，找出所有“索示源对象却获理一个null”的地方，修改这些地方，使它们获得一个空对象
4，找出所有“将源对象与null做比较”的地方，修改这些地方，使它们调用isNull函数。
5，编译、测试
6，找出这样的程序点：如果对象不是null，做A动作，否则执行B动作。
7，对于每个上述地点，在null类中覆写A动作，使用其行为和B动作相同，
8，使用上述被覆写的动作，然后删除“对象是否等于null”的条件测试，编译并测试。


##示例：
###src
```java
public class BillingPlan {

  public static BillingPlan basic() {
    return new BillingPlan();
  }
}

public class Customer {

  private BillingPlan plan;

  private PaymentHistory history;

  public String getName() {
    return String.valueOf(ThreadLocalRandom.current().nextInt());
  }

  public BillingPlan getPlan() {
    return null;
  }

  public void setPlan(BillingPlan plan) {
    this.plan = plan;
  }

  public PaymentHistory getHistory() {
    return history;
  }

  public void setHistory(PaymentHistory history) {
    this.history = history;
  }
}


public class PaymentHistory {

  int getWeekDelinquentInLastYear() {
    return ThreadLocalRandom.current().nextInt();
  }
}


public class Site {

  private Customer customer;

  public Customer getCustomer() {
    return customer;
  }
}

public class SiteClient {

  public void doInvoke(Site site) {
    Customer customer = site.getCustomer();
    BillingPlan plan;
    if (customer == null) {
      plan = BillingPlan.basic();
    } else {
      plan = customer.getPlan();
    }

    String customerName;
    if (customer == null) {
      customerName = "occupant";
    } else {
      customerName = customer.getName();
    }

    int weeksdelinquent;
    if (customer == null) {
      weeksdelinquent = 0;
    } else {
      weeksdelinquent = customer.getHistory().getWeekDelinquentInLastYear();
    }

    System.out.println(plan);
    System.out.println(customerName);
    System.out.println(weeksdelinquent);
  }
}
```

###refactor:
1,新建一个Nullable接口，用于昭示“这里使用了空对象”
```java
public interface Nullable {

  boolean isNull();
}
```

2，新建一个NullCustomer，并修改Customer使其支持“对象是否为空”的检查
```java
public class NullCustomer extends Customer implements Nullable {

  @Override
  public boolean isNull() {
    return true;
  }
}

```

3,修改Customer，将空的返回改为false
```java
public class Customer implements Nullable {
  
  private BillingPlan plan;

  private PaymentHistory history;

  public static Customer newNull() {
    return new NullCustomer();
  }

  public String getName() {
    return String.valueOf(ThreadLocalRandom.current().nextInt());
  }

  public BillingPlan getPlan() {
    return plan;
  }

  public void setPlan(BillingPlan plan) {
    this.plan = plan;
  }

  public PaymentHistory getHistory() {
    return history;
  }

  public void setHistory(PaymentHistory history) {
    this.history = history;
  }    

  @Override
  public boolean isNull() {
    return false;
  }
}
```

4,加入工厂函数，专门用来创建NullCustomer对象
```java
public class Customer implements Nullable {

  private BillingPlan plan;

  private PaymentHistory history;

  public static Customer newNull() {
    return new NullCustomer();
  }
  
  public static Customer newNull() {
    return new NullCustomer();
  }

  public String getName() {
    return String.valueOf(ThreadLocalRandom.current().nextInt());
  }

  public BillingPlan getPlan() {
    return plan;
  }

  public void setPlan(BillingPlan plan) {
    this.plan = plan;
  }

  public PaymentHistory getHistory() {
    return history;
  }

  public void setHistory(PaymentHistory history) {
    this.history = history;
  }

  @Override
  public boolean isNull() {
    return false;
  }
}
```

5,对于所有"返回null"的地方，将它修改为“返回空对象”。此外将==null的检查替换为.isNull()
```java
public class Site {

  private Customer customer;

  public Customer getCustomer() {
    return (customer == null) ? Customer.newNull() : customer;
  }
}

public class SiteClient {

  public void doInvoke(Site site) {
    Customer customer = site.getCustomer();
    BillingPlan plan;
    if (customer.isNull()) {
      plan = BillingPlan.basic();
    } else {
      plan = customer.getPlan();
    }

    String customerName;
    if (customer.isNull()) {
      customerName = "occupant";
    } else {
      customerName = customer.getName();
    }

    int weeksdelinquent;
    if (customer.isNull()) {
      weeksdelinquent = 0;
    } else {
      weeksdelinquent = customer.getHistory().getWeekDelinquentInLastYear();
    }

    System.out.println(plan);
    System.out.println(customerName);
    System.out.println(weeksdelinquent);
  }
}

```

6,编译测试


7,将空值情况下的值设置从客户判断逻辑移动到nullCustomer对象中
```java
public class NullCustomer extends Customer implements Nullable {

  @Override
  public boolean isNull() {
    return true;
  }

  @Override
  public String getName() {
    return "occupant";
  }
}


public class SiteClient {

  public void doInvoke(Site site) {
    Customer customer = site.getCustomer();
    BillingPlan plan;
    if (customer.isNull()) {
      plan = BillingPlan.basic();
    } else {
      plan = customer.getPlan();
    }

    // 去掉条件判断后的代码
    String customerName = customer.getName();

    int weeksdelinquent;
    if (customer.isNull()) {
      weeksdelinquent = 0;
    } else {
      weeksdelinquent = customer.getHistory().getWeekDelinquentInLastYear();
    }

    System.out.println(plan);
    System.out.println(customerName);
    System.out.println(weeksdelinquent);
  }
}
```

8,以相同的手法处理其他两个判空