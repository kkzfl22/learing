## 添加特性(add function)

>
    不管我们采取什么样的设计方案或者面对什么样的特定约束，总还是有一些技术可以使我们的工作变得轻松。
    1，测试驱动开发。
    2. 差异式编程。——（LSP原则)
>


## 测试驱动开发
最为强大的特性添加技术便是测试驱动开发(TDD)
简单地说，测试驱动开发(TDD)开发工过程是这样的：
设想有一这么一个方法，能够帮我们解决问题的某个部分；接下来我们为这个设想中的方法编写一个失败测试用例。
此时该方法尚不存在，但既然我们能够为它编写测试，我们就对接下来将要编写的代码做什么有一个确实的认识。

测试驱动开发使用的算法如下：
1. 编写一个失败的测试用例。
2. 让它通过编译。
3. 让它测试通过。
4. 消除重复。
5. 重复上述步骤。



## 需求
>
    以财务系统，我们需要一个能够利用某些高性能数学方法来验证某些商品是否应该被买卖的类。
>

### 1. 编写一个失败的测试用例
```java

public class TestInstrumentCalculator {

  @Test
  public void testFirstMoment() {
    InstrumentCalculator calculator = new InstrumentCalculator();
    calculator.addElement(1.0);
    calculator.addElement(2.0);

    Assert.assertEquals(-0.5, calculator.firstMomentAbout(2.0), TOLERANCE);
  }
}

```

### 2. 让它编译通过
```java

public class TestInstrumentCalculator {

  private static final double TOLERANCE = 0.0;

  @Test
  public void testFirstMoment() {
    InstrumentCalculator calculator = new InstrumentCalculator();
    calculator.addElement(1.0);
    calculator.addElement(2.0);

    Assert.assertEquals(-0.5, calculator.firstMomentAbout(2.0), TOLERANCE);
  }
}


public class InstrumentCalculator {

  public void addElement(double value) {}

  public double firstMomentAbout(double value) {
    return Double.NaN;
  }
}

```


### 3. 让测试通过
```java

public class TestInstrumentCalculator {

  private static final double TOLERANCE = 0.0;

  @Test
  public void testFirstMoment() {
    InstrumentCalculator calculator = new InstrumentCalculator();
    calculator.addElement(1.0);
    calculator.addElement(2.0);

    Assert.assertEquals(-0.5, calculator.firstMomentAbout(2.0), TOLERANCE);
  }
}

public class InstrumentCalculator {

  private List<Double> elements = new ArrayList<>(8);

  public void addElement(double value) {
    this.elements.add(value);
  }

  public double firstMomentAbout(double point) {

    double numerator = 0.0;
    for (Double element : elements) {
      double elementValue = element.doubleValue();
      numerator += elementValue - point;
    }

    return numerator / elements.size();
  }
}

```

### 4. 消除重复
这在个例子中存在重复吗？ 没有。

## 提示
>
    在测试驱动开发当中，对于仅仅为了令测试通过这一目的来说，以上代码代码已经已经算是异常多的了。
    通常情况下我们的步骤要细得多。不过，如果你对自己需要使用算法有足够的信心，也可以采取像上面这种做法。
>



### 继续一个用例。
刚刚的测试用例并未覆盖所有的情况。
例如，在返回语句那行，可以出现除零的情况。对于这种异常

以下使用junit5的语法

### 1. 编写一个失败测试用例
```java

public class TestInstrumentCalculator {

  @Test
  @DisplayName("大小为0的异常")
  public void testFirstMoment2() {
    InstrumentCalculator calculator = new InstrumentCalculator();
    Exception exception =
        assertThrows(InvalidBasisException.class, () -> calculator.firstMomentAbout(0.0));
    assertEquals(exception, InvalidBasisException.class);
  }
}

```

### 2. 让它编译通过
```java

public class InstrumentCalculator {

  private List<Double> elements = new ArrayList<>(8);

  public void addElement(double value) {
    this.elements.add(value);
  }

  public double firstMomentAbout(double point) throws InvalidBasisException {
    double numerator = 0.0;
    for (Double element : elements) {
      double elementValue = element.doubleValue();
      numerator += elementValue - point;
    }

    return numerator / elements.size();
  }
}


public class InvalidBasisException extends Exception {

    public InvalidBasisException(String message) {
        super(message);
    }
}



```

目前仅编译通过，但在声明了InvalidBasisException，还没有检查
```java

public class InstrumentCalculator {

  private List<Double> elements = new ArrayList<>(8);

  public void addElement(double value) {
    this.elements.add(value);
  }

  public double firstMomentAbout(double point) throws InvalidBasisException {

    if (null == elements || elements.isEmpty()) {
      throw new InvalidBasisException("no elements");
    }

    double numerator = 0.0;
    for (Double element : elements) {
      double elementValue = element.doubleValue();
      numerator += elementValue - point;
    }

    return numerator / elements.size();
  }
}

```

### 3,让测试通过
由于之前的单元测试判断存在问题，需要修改

```java

public class TestInstrumentCalculator {

  @Test
  @DisplayName("大小为0的异常")
  public void testFirstMoment2() {
    InstrumentCalculator calculator = new InstrumentCalculator();
    Exception exception =
        assertThrows(InvalidBasisException.class, () -> calculator.firstMomentAbout(0.0));
    assertEquals(exception.getMessage(), "no elements");
  }
}



public class InstrumentCalculator {

  private List<Double> elements = new ArrayList<>(8);

  public void addElement(double value) {
    this.elements.add(value);
  }

  public double firstMomentAbout(double point) throws InvalidBasisException {

    if (null == elements || elements.isEmpty()) {
      throw new InvalidBasisException("no elements");
    }

    double numerator = 0.0;
    for (Double element : elements) {
      double elementValue = element.doubleValue();
      numerator += elementValue - point;
    }

    return numerator / elements.size();
  }
}

```


### 4. 消除重复代码
本例中没有重复的代码.



## 3.新的用例
计算一个点的二阶矩阵的方法。

### 1. 编写一个失败的测试用例
```java
public class TestInstrumentCalculator {

  private static final double TOLERANCE = 0.0;

  @Test
  public void testSecondMoment() {
    InstrumentCalculator calculator = new InstrumentCalculator();
    calculator.addElement(1.0);
    calculator.addElement(2.0);
    assertEquals(0.5, calculator.secondMomentAbout(2.0), TOLERANCE);
  }
}

```

### 2. 让它编译通过
```java

public class InstrumentCalculator {

  private List<Double> dataList = new ArrayList<>();

  public void addElement(double infos) {
    dataList.add(infos);
  }

  public Double secondMomentAbout(double value) {
    return Double.NaN;
  }
}

```



### 3. 让测试通过
```java
public class InstrumentCalculator {

  private List<Double> dataList = new ArrayList<>();

  public void addElement(double infos) {
    dataList.add(infos);
  }

  public Double secondMomentAbout(double point) throws InvalidBasisException {

    if (null == dataList || dataList.isEmpty()) {
      throw new InvalidBasisException("no elements");
    }

    double numerator = 0.0;
    for (Double item : dataList) {
      double dataValue = item.doubleValue();
      numerator += Math.pow(dataValue - point, 2.0);
    }

    return numerator / dataList.size();
  }
}

public class InvalidBasisException extends Exception {

    public InvalidBasisException(String message) {
        super(message);
    }
}

```


### 执行最后一步，消除重复。

做法1. 将secondMomentAbout的函数体完全提取出来，重新命名为nthMomentAbout,并添加一个参数N
```java

public class InstrumentCalculator {

  private List<Double> dataList = new ArrayList<>();

  public void addElement(double infos) {
    dataList.add(infos);
  }

  public Double secondMomentAbout(double point) throws InvalidBasisException {
    return this.nthMomentAbout(point, 2.0);
  }

  private double nthMomentAbout(double point, double n) throws InvalidBasisException {
    if (null == dataList || dataList.isEmpty()) {
      throw new InvalidBasisException("no elements");
    }

    double numerator = 0.0;
    for (Double item : dataList) {
      double dataValue = item.doubleValue();
      numerator += Math.pow(dataValue - point, 2.0);
    }

    return numerator / dataList.size();
  }
}

```
另外一处的高调用也修改即可

>
    这是最后一步，即消除重复，是非常重要的。我们可以通过诸如复制粘贴整块代码这样的方式来快速但粗暴地往即有代码中添加新特性，
    但如果事情不消除重复代码的话，无异会带来麻烦和维护的负担。
    另一方面，有测试的帮助可以很容易的消除重复代码。
    在修改遗留代码过程中，当我们使用测试驱动开发时，为既有代码编写的那些测试是非常重要的。
    有了这些测试做后盾，便可以放手云编写新特性的实现代码了，而且最后我们可以妥善安全的把这些新代码安置到其余代码当中。
>


测试驱动开发与遗留代码
>
    测试驱动开发的最有价值的一个方面是它使得我们可以同一时间只关注一件事情。要么在编码，要么在重构；永远不也不会在同一时刻做两件事情。
    这一好处对付遗留代码的人们来说显得尤其有价值，因为它使我们能够独立地编写新代码。
    在编写完一些新代码之后，我们便可以通过重构来消除新旧代码之彰的任何重复。
>


测试驱动开发的算法过程
1. 将想要修改的类置于测试之下
2. 编写一个失败的测试用例。
3. 让它编译通过。
4. 让测试通过(在这一步尽量不要修改既有代码)
5. 消除重复。
6. 重复上述步骤。

