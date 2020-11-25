#Preserve Whole Object(保持对象完整)

你从某个对象中取出若干值，将它们作为某一次函数调用时的参数，改为传递整个对象。

##原因
有时你会用同一个对象的若干参数项数据作为参数，传递给某个函数，这样做的问题在于：万一将来被调用函数需要添加数据项。
就必须查找并修改对此函数的所有调用。如果把这些数据项所依赖的整个对象传递给函数，就可以避免这种情况。
除了可以使函数更稳固之外，往往还能提高代码处境。

##作法
<ul>
    <li>1,对你的目标函数添加一个参数项，用于表示原数据所在的完整对象。</li>
    <li>2,编译，测试</li>
    <li>3,判断哪些参数可被包含在新添的完整对象中</li>
    <li>4,选择上述参数之一，将调用函数中原来引用参数的地方，改为调用新参数对象的相应取值函数。</li>
    <li>5,删除该项参数</li>
    <li>6,编译，测试</li>
    <li>7,针对所有可从完整对象中获得的参数，重复上述过程。</li>
    <li>8,删除调用端中那些带有被删除参数的代码。</li>
    <li>9,编译测试。</li>    
</ul>

##src
```java
public class Room {

  private TempRange daysTempRange;

  boolean withinPlan(HeatingPlan plan) {
    int low = daysTempRange.getLow();
    int high = daysTempRange.getHight();
    return plan.withinRang(low, high);
  }
}

public class HeatingPlan {

  private TempRange range;

  boolean withinRang(int low, int high) {
    return low >= range.getLow() && high <= range.getHight();
  }
}

@Getter
@Setter
@ToString
public class TempRange {

  private int low;

  private int hight;
}
```

##refactor
1,在参数列表中添加参数项,并修改调用
```java
public class HeatingPlan {

  private TempRange range;

  /**
   * 添加参数项
   * @param roomRange
   * @param low
   * @param high
   * @return
   */
  boolean withinRang(TempRange roomRange,int low, int high) {
    return low >= range.getLow() && high <= range.getHight();
  }
}

public class Room {

  private TempRange daysTempRange;

  boolean withinPlan(HeatingPlan plan) {
    int low = daysTempRange.getLow();
    int high = daysTempRange.getHight();
    return plan.withinRang(daysTempRange,low, high);
  }
}
```

2,以对象取值取代参数
```java
public class HeatingPlan {

  private TempRange range;

  /**
   * 添加参数项
   *
   * @param roomRange
   * @param high
   * @return
   */
  boolean withinRang(TempRange roomRange, int high) {
    return roomRange.getLow() >= range.getLow() && high <= range.getHight();
  }
}

public class Room {

  private TempRange daysTempRange;

  boolean withinPlan(HeatingPlan plan) {
    int high = daysTempRange.getHight();
    return plan.withinRang(daysTempRange, high);
  }
}
```

3,重复至到所有参数都被替换
```java
public class HeatingPlan {

  private TempRange range;

  /**
   * 添加参数项
   *
   * @param roomRange
   * @return
   */
  boolean withinRang(TempRange roomRange) {
    return roomRange.getLow() >= range.getLow() && roomRange.getHight() <= range.getHight();
  }
}

public class Room {

  private TempRange daysTempRange;

  boolean withinPlan(HeatingPlan plan) {
    return plan.withinRang(daysTempRange);
  }
}
```

4,将某些函数移到TempRange对象中，使它更容易被使用。
```java
@Getter
@Setter
@ToString
public class TempRange {

  private int low;

  private int hight;

  /**
   * 添加参数项
   *
   * @param roomRange
   * @return
   */
  boolean includes(TempRange roomRange) {
    return roomRange.getLow() >= this.getLow() && roomRange.getHight() <= this.getHight();
  }
}

public class HeatingPlan {

  private TempRange range;

  /**
   * 添加参数项
   *
   * @param roomRange
   * @return
   */
  boolean withinRang(TempRange roomRange) {
    return range.includes(roomRange);
  }
}
```