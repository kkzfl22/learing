#Form Template Method(塑造模板函数)

你有一些子类，其中相应的某些函数以相同的顺序执行类似的操作。但各操作的细节有所不同
将这些操作分别放进放进独立的函数中。并保持它们都有独立的签名。于是原函数就变得相同了。然后将原函数移至超类。


##原因
继承是避免重复行为的一个强大工具。无论何时，只要你看见两个子类有相类似的函数，就可以把他们提升至超类中。
常见的一种情况：两个函数以相同的顺序执行大致相近的操作。但操作不完全相同。
这种情况下我们就可以将执行操作序列移至父类，但借助多态保持各操作的差异。

#做法
<ul>
    <li>1,在各子类中分解目标函数，使分解后的函数要不完全相同，要不完全不同。</li>
    <li>2,运用Pull up method将各子类完合相同的函数移到超类。</li>
    <li>3,那些剩余完全不同的函数，运用Rename Method使这些函数的签名完全相同。</li>
    <li>4,修改上述所有签名后，编译并测试。</li>
    <li>5,运行Pull up Method将所有函数逐一上移至超类，在超类中将那些代码不同操作的函数定义为抽象函数。</li>
    <li>6,编译并测试.</li>
    <li>7,移除其他子类中的原函数，每删除一个，编译并测试。</li>
</ul>

还是以电影完与顾客为例
#src
```java
public class Customer {

  /** 顾客的名称 */
  private String name;

  /** 租借信息 */
  private List<Rental> rentals = new ArrayList<>();

  public Customer(String name) {
    this.name = name;
  }

  /**
   * 租借信息
   *
   * @param arg
   */
  public void addRental(Rental arg) {
    rentals.add(arg);
  }

  public String getName() {
    return name;
  }

  /**
   * 生成详单的函数
   *
   * @return
   */
  public String statement() {
    int frequentRenterPoints = 0;
    String result = "Rental Record for " + getName() + "\n";
    for (Rental each : rentals) {
      // 积分计算的方法
      frequentRenterPoints += each.frequentRenterCount();

      result += "\t" + each.getMovie().getTitle() + "\t" + each.countAmount() + "\n";
    }

    result += " Amount owed is " + countTotalAmount() + "\n";
    result += " You earned " + frequentRenterPoints + " frequent renter points";
    return result;
  }


  /**
   * 生成详单的函数
   *
   * @return
   */
  public String htmlStatement() {
    int frequentRenterPoints = 0;
    String result = "<H1>Rental Record for <EM> " + getName() + "</EM><H1><P>n";
    for (Rental each : rentals) {
      // 积分计算的方法
      frequentRenterPoints += each.frequentRenterCount();

      result += "\t" + each.getMovie().getTitle() + "\t" + each.countAmount() + "<BR>\n";
    }

    result += "<P> Amount owed is <EM>" + countTotalAmount() + "</EM><P>\n";
    result += " You earned <EM>" + frequentRenterPoints + " </EM> frequent renter points <P>";
    return result;
  }


  /**
   * 提取计算方法
   *
   * @return 值的计算
   */
  private double countTotalAmount() {
    double totalAmount = 0;

    for (Rental each : rentals) {
      // 调用方法进行积分的计算
      totalAmount += each.countAmount();
    }

    return totalAmount;
  }
}

```

#Refactor:
1,创建空的子类
```java
public abstract class Statement{

}


public class HtmlStatement extends Statement {
    
}

public class TextStatement extends Statement {

}

```

2,通过Method Down Method将函数挺移到子类中
,并修改方法名及参数进行统一。
```java

public class HtmlStatement extends Statement {

  /**
   * 生成详单的函数
   *
   * @return
   */
  public String value(Customer customer) {
    int frequentRenterPoints = 0;
    String result = "<H1>Rental Record for <EM> " + getName() + "</EM><H1><P>n";
    for (Rental each : customer.getRentals()) {
      // 积分计算的方法
      frequentRenterPoints += each.frequentRenterCount();

      result += "\t" + each.getMovie().getTitle() + "\t" + each.countAmount() + "<BR>\n";
    }

    result += "<P> Amount owed is <EM>" + customer.countTotalAmount() + "</EM><P>\n";
    result += " You earned <EM>" + frequentRenterPoints + " </EM> frequent renter points <P>";
    return result;
  }
}


public class TextStatement extends Statement {


    public String value(Customer customer) {
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";
        for (Rental each : customer.getRentals()) {
            // 积分计算的方法
            frequentRenterPoints += each.frequentRenterCount();

            result += "\t" + each.getMovie().getTitle() + "\t" + each.countAmount() + "\n";
        }

        result += " Amount owed is " + customer.countTotalAmount() + "\n";
        result += " You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }
}
```

##3，子类进行重构，提取公有部分。
```java

public class HtmlStatement extends Statement {
   

  /**
   * 生成详单的函数
   *
   * @return
   */
  public String value(Customer customer) {

    String result = this.getHead(customer);
    for (Rental each : customer.getRentals()) {
      result += this.getBody(each);
    }
    result += getEnd(customer);
    return result;
  }

  /**
   * 积分计算
   *
   * @param customer
   * @return
   */
  public int frequentRenterPointCount(Customer customer) {
    int frequentRenterPoints = 0;
    for (Rental each : customer.getRentals()) {
      // 积分计算的方法
      frequentRenterPoints += each.frequentRenterCount();
    }

    return frequentRenterPoints;
  }

  public String getHead(Customer customer) {
    return "<H1>Rental Record for <EM> " + customer.getName() + "</EM><H1><P>n";
  }

  public String getBody(Rental each) {
    return "\t" + each.getMovie().getTitle() + "\t" + each.countAmount() + "<BR>\n";
  }

  public String getEnd(Customer customer) {
    int frequentRenterPoints = this.frequentRenterPointCount(customer);
    String result = "<P> Amount owed is <EM>" + customer.countTotalAmount() + "</EM><P>\n";
    result += " You earned <EM>" + frequentRenterPoints + " </EM> frequent renter points <P>";
    return result;
  }
}



public class TextStatement extends Statement {

  public String value(Customer customer) {
    String result = this.getHead(customer);
    for (Rental each : customer.getRentals()) {
      result += this.getBody(each);
    }

    result += getEnd(customer);

    return result;
  }

  /**
   * 计算积分
   *
   * @param customer
   * @return
   */
  public int frequentRenterPointCount(Customer customer) {
    int frequentRenterPoints = 0;

    for (Rental each : customer.getRentals()) {
      // 积分计算的方法
      frequentRenterPoints += each.frequentRenterCount();
    }

    return frequentRenterPoints;
  }

  public String getHead(Customer customer) {
    return "Rental Record for " + customer.getName() + "\n";
  }

  public String getBody(Rental each) {
    return "\t" + each.getMovie().getTitle() + "\t" + each.countAmount() + "\n";
  }

  public String getEnd(Customer customer) {
    // 执行积分计算
    int frequentRenterPoints = this.frequentRenterPointCount(customer);
    String result = " Amount owed is " + customer.countTotalAmount() + "\n";
    result += " You earned " + frequentRenterPoints + " frequent renter points";
    return result;
  }
}

```

##4,将公共方法上移
```java
public abstract class Statement {

  /**
   * 生成详单的函数
   *
   * @return
   */
  public String value(Customer customer) {

    String result = this.getHead(customer);
    for (Rental each : customer.getRentals()) {
      result += this.getBody(each);
    }
    result += getEnd(customer);
    return result;
  }

  /**
   * 积分计算
   *
   * @param customer
   * @return
   */
  protected int frequentRenterPointCount(Customer customer) {
    int frequentRenterPoints = 0;
    for (Rental each : customer.getRentals()) {
      // 积分计算的方法
      frequentRenterPoints += each.frequentRenterCount();
    }

    return frequentRenterPoints;
  }

  /**
   * 添加消息头的公共函数
   *
   * @param customer
   * @return
   */
  protected abstract String getHead(Customer customer);

  /**
   * 内容信息
   *
   * @param customer
   * @return
   */
  protected abstract String getBody(Rental customer);

  /**
   * 结果信息
   *
   * @param customer
   * @return
   */
  protected abstract String getEnd(Customer customer);
}



public class HtmlStatement extends Statement {

  @Override
  protected String getHead(Customer customer) {
    return "<H1>Rental Record for <EM> " + customer.getName() + "</EM><H1><P>n";
  }

  @Override
  protected String getBody(Rental each) {
    return "\t" + each.getMovie().getTitle() + "\t" + each.countAmount() + "<BR>\n";
  }

  @Override
  protected String getEnd(Customer customer) {
    int frequentRenterPoints = this.frequentRenterPointCount(customer);
    String result = "<P> Amount owed is <EM>" + customer.countTotalAmount() + "</EM><P>\n";
    result += " You earned <EM>" + frequentRenterPoints + " </EM> frequent renter points <P>";
    return result;
  }
}


public class TextStatement extends Statement {

  @Override
  public String getHead(Customer customer) {
    return "Rental Record for " + customer.getName() + "\n";
  }

  @Override
  public String getBody(Rental each) {
    return "\t" + each.getMovie().getTitle() + "\t" + each.countAmount() + "\n";
  }

  @Override
  public String getEnd(Customer customer) {
    // 执行积分计算
    int frequentRenterPoints = this.frequentRenterPointCount(customer);
    String result = " Amount owed is " + customer.countTotalAmount() + "\n";
    result += " You earned " + frequentRenterPoints + " frequent renter points";
    return result;
  }
}

```

4，编译，测试。