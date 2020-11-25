## 第六章，时间紧迫，但必须修改。

## 6.1 新生方法
>
    当需要往一个系统中添加新的特性且这个特性可以使用全新的代码来编写时，
    建议将这些代码放在新方法中，并在需要用到新功能的地方调用这一方法。
>

### 基础代码
```java
public class TransactionGate {

  /** 获取对象信息 */
  private TransactionBundle transactionBundle;

  public TransactionGate(TransactionBundle transactionBundle) {
    this.transactionBundle = transactionBundle;
  }

  public void postEntries(List<DataOperator> entries) {
    for (DataOperator item : entries) {
      item.postDate();
    }
    transactionBundle.getListManager().addAll(entries);
  }
}


public class TransactionBundle {

  private List<DataOperator> dataList;

  public TransactionBundle(int defSize) {
    this.dataList = new ArrayList<>();
  }

  public static TransactionBundle getInstance() {
    return new TransactionBundle(2);
  }

  public List<DataOperator> getListManager() {
    return dataList;
  }

  public boolean hashEntry(DataOperator operatorInstance) {
    return dataList.contains(operatorInstance);
  }
}

public class DataOperator {

  public void postDate() {
    System.out.println("post date");
  }
}

```

## 现在需求新功能，先检查是否存在，不存在，则添加
### 常规则操作
```java
public class TransactionGate {

  /** 获取对象信息 */
  private TransactionBundle transactionBundle;

  public TransactionGate(TransactionBundle transactionBundle) {
    this.transactionBundle = transactionBundle;
  }

  public void postEntries(List<DataOperator> entries) {
    List<DataOperator> entryDataList = new ArrayList<>(entries.size());
    for (DataOperator item : entries) {
      // 先检查是否存在,不存在，则添加
      if (!transactionBundle.hashEntry(item)) {
        item.postDate();
        entryDataList.add(item);
      }
    }
    transactionBundle.getListManager().addAll(entryDataList);
  }
}
```
这也是我脑子里首先想到的做法，平时也是这么干的。

### Sprout Method(新生方法技术) 
```java
public class TransactionGate {

  /** 获取对象信息 */
  private TransactionBundle transactionBundle;

  public TransactionGate(TransactionBundle transactionBundle) {
    this.transactionBundle = transactionBundle;
  }

  public void postEntries(List<DataOperator> entries) {
    List<DataOperator> entriesToAdd = this.uniqueEntries(entries);
    for (DataOperator item : entriesToAdd) {
      item.postDate();
    }
    transactionBundle.getListManager().addAll(entriesToAdd);
  }

  /**
   * 新生方法，将能独立出来的代码独立出来，不是直接修改修改，
   *
   * @param entries
   * @return
   */
  public List<DataOperator> uniqueEntries(List<DataOperator> entries) {
    List<DataOperator> result = new ArrayList<>(entries.size());
    for (DataOperator item : entries) {
      if (transactionBundle.hashEntry(item)) {
        result.add(item);
      }
    }

    return result;
  }
}
```

代码中依然出现了一个临时变量，但关键是现在代码清晰多了。


### 实施这一技术时实际需要的采取的步骤

>
    1. 确实修改点,
    2. 如果你的修改可以在一个方法中的一处地方以单块连续的语句序列出现，那么在修改点插入一个方法调用，
    而调用的就是下面我们编写的、用于完成有关工作的新方法。然后将这一代码先注释掉（我个人喜欢把这一步放在编写新方法前，
    因为这样我就能对新方法的调用的上下文中的样子有一个新的认识）
    3. 确定你需要原方法中的哪些局部变量，将他们作为实参传递给新方法调用。
    4. 确定新方法是否需要返回什么值给原方法。如果需要的话就得相应的修改对它的调用，使用一个变量来接收其返回值。
    5. 使用测试驱动的开发方式来开发新的方法。
    6. 使用原方法被注释掉的调用重新生效。
>

>
    任何时候，只要你发现待添加的功能可以写成一个独立的代码，或者暂时还没有测试来覆盖待修改的方法时，
    建议采用新生方法。这比直接往原方法中添加代码好多了。
>

### 新生方法的优点及缺点
缺点
>
    1. 当使用它时，效果上等于暂时放弃了原方法所属的类,
    也就是说暂时不打算将他们置于测试之下和改善之下，而只打算来写一个新方法来实现某个新功能。
    2. 有时候放弃一个类或者方法是迫于现实，但还是有点令人惋惜，因为有你的代码处于一个尴尬的境地。
    3. 原方法中包含了大量复杂的代码以及一个新生方法。
    4. 有时候事情并不明朗，为什么就偏那点工作要放到其他地方中去呢？况且又令原方法“身陷囹圄”。
    5. 它也提醒你，当你将原类置于测试之下时，可以回头做一点补救。
>

优点
>
    1. 新旧代码被清楚的隔离开。
    2. 即时暂时没有办法将旧代码置于测试下之，至少还能单独去关注所要做的改动，并在新旧代码之间建立清晰的接口。
    3. 你会看到所有被影响的变量，更容量确实新代码在上下文中是否正确的。
>
