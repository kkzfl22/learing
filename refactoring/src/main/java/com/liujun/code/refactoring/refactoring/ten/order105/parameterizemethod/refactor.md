#Parameterize method(令函数携带参数)

若干函数做了类似的工作，但在函数本体中却包含了不同的值
建立一个函数，以参数表达那些不同的值

原因：
有时可能发现两个函数，它们做着类似的工作，但因为少数几个值至使行为略有不同。
这种情况下，可以将这些各自分离的函数统一一起，并通过参数处理那些变化情况，用以简化问题
这样的修改可以去除重复的代码。并提供高代码的灵活性。因为你可以用这个参数处理更多的变化情况。

#做法
<ul>
<li>1，新建一个带有参数的函数，使用可以替换先前所有的重复性函数。</li>
<li>2,编译</li>
<li>3,将调用旧函数的代码改为调用新函数</li>
<li>4,对所有旧代码重复以上步骤，每次替换后，修改并测试。</li>
</ul>



##src:
```java
public class Employee {

  private float salary = ThreadLocalRandom.current().nextFloat();

  void tenPercentRaise() {
    salary *= 1.1;
  }

  void fivePercentRaise() {
    salary *= 1.05;
  }


}
```


##refactor
```java
public class Employee {

  private float salary = ThreadLocalRandom.current().nextFloat();

  void raise(double factor) {
    salary *= (1 + factor);
  }
}
```