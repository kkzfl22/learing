#Replace Parameter with Methods (以函数取代参数)

对象调用某个函数，并将所得结果作为参数，传递给别一个函数。
而接受该函数的函数本身也能够调用前一个函数。
让函数接受者去除该参数函数，并直接调用前一个函数。

##原因
如果函数可以通过其他途径获得参数值，那么它就不应该通过参数取得该值。过长的参数列会增加程序阅读者的理解难度。
因此我们应该尽可能的缩短参数列的长度。

缩减参数列的办法之一就是：看看参数接受端是否可以通过与调用端相同的计算来取得参数值。
如果调用端通过其所属对象内部的别一个函数来计算参数，并在计算过程中未曾引用调用端的其他参数，
就应该可以将这个计算过程转移到被调用端内，从而去除该参数。


##做法
<ul>
    <li>1,如果有必要，将参数的计算过程提练到一个独立的函数中</li>
    <li>2,将函数本体内引用该参数的地方改为调用新建的函数。</li>
    <li>3,每次替换完成后，修改并测试</li>
    <li>4,全部替换完成后，使用remove Parameter将该参数去掉。</li>
</ul>



##src
```java
public class Price {

  private int quantity = ThreadLocalRandom.current().nextInt();
  private int itemPrice = ThreadLocalRandom.current().nextInt();

  public double getPrice() {

    int basePrice = quantity * itemPrice;
    int discountLevel;
    if (quantity > 100) {
      discountLevel = 2;
    } else {
      discountLevel = 1;
    }

    double finalPrice = discountedPrice(basePrice, discountLevel);
    return finalPrice;
  }

  private double discountedPrice(int basePrice, int discountLevel) {
    if (discountLevel == 2) {
      return basePrice * 0.1;
    } else {
      return basePrice * 0.05;
    }
  }
}
```



##refactor:
1,提练折扣函数的计算21
```java
public class Price {

  private int quantity = ThreadLocalRandom.current().nextInt();
  private int itemPrice = ThreadLocalRandom.current().nextInt();

  public double getPrice() {

    int basePrice = quantity * itemPrice;
    int discountLevel = getDiscountLevel();
    double finalPrice = discountedPrice(basePrice, discountLevel);
    return finalPrice;
  }

  /**
   * 提练折扣函数的计算
   *
   * @return
   */
  private int getDiscountLevel() {
    if (quantity > 100) {
      return 2;
    }
    return 1;
  }

  private double discountedPrice(int basePrice, int discountLevel) {
    if (discountLevel == 2) {
      return basePrice * 0.1;
    } else {
      return basePrice * 0.05;
    }
  }
}
```

2,然后把对参数的的引用点，替换为函数计算
```java
public class Price {

  private int quantity = ThreadLocalRandom.current().nextInt();
  private int itemPrice = ThreadLocalRandom.current().nextInt();

  public double getPrice() {

    int basePrice = quantity * itemPrice;
    int discountLevel = getDiscountLevel();
    double finalPrice = discountedPrice(basePrice, discountLevel);
    return finalPrice;
  }

  /**
   * 提练折扣函数的计算
   *
   * @return
   */
  private int getDiscountLevel() {
    if (quantity > 100) {
      return 2;
    }
    return 1;
  }

  private double discountedPrice(int basePrice, int discountLevel) {
    //替换为对函数的调用
    if (getDiscountLevel() == 2) {
      return basePrice * 0.1;
    } else {
      return basePrice * 0.05;
    }
  }
}
```

3,去掉多余参数
```java
public class Price {

  private int quantity = ThreadLocalRandom.current().nextInt();
  private int itemPrice = ThreadLocalRandom.current().nextInt();

  public double getPrice() {

    int basePrice = quantity * itemPrice;
    double finalPrice = discountedPrice(basePrice);
    return finalPrice;
  }

  /**
   * 提练折扣函数的计算
   *
   * @return
   */
  private int getDiscountLevel() {
    if (quantity > 100) {
      return 2;
    }
    return 1;
  }

  private double discountedPrice(int basePrice) {
    //替换为对函数的调用
    if (getDiscountLevel() == 2) {
      return basePrice * 0.1;
    } else {
      return basePrice * 0.05;
    }
  }
}
```



4,重复去掉参数，直接参数不能再去掉为止
```java
public class Price {

  private int quantity = ThreadLocalRandom.current().nextInt();
  private int itemPrice = ThreadLocalRandom.current().nextInt();

  public double getPrice() {
    return discountedPrice();
  }

  /**
   * 提练折扣函数的计算
   *
   * @return
   */
  private int getDiscountLevel() {
    if (quantity > 100) {
      return 2;
    }
    return 1;
  }

  private double discountedPrice() {
    // 替换为对函数的调用
    if (getDiscountLevel() == 2) {
      return basePrice() * 0.1;
    } else {
      return basePrice() * 0.05;
    }
  }

  private int basePrice() {
    return quantity * itemPrice;
  }
}

```