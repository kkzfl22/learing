#Replace Inheritance with Delegation (以委托取代继承)

某个类只使用超类接口中的一部分，或者根本不需要继承而来的数据，
在子类中新建一个字段用于保存超类，然后调整子函数；
令它改而委托超类。然后去掉两者的继承关系。


#原因
继承是个好东西，但有时候，它并不是你想要的。你常常会遇到这样的情况：
一开始继承了一个类， 随后发现超类中的许多操作并不适用子子类。
这种情况下你所拥有的接口并未真正反应出子类的功能。
或者可能发现从超类继承你一大堆并不需要的数据。
或者超类中的某此protected函数对比类没有什么意义。

你可选择容忍，并接受父传统的说法：子类可以只使用超类的一部分功能。但这样做的结果是：
代码传递的意图与你的意图南辕北辙。这是一种混淆，你应该将它去除。

如果以委托取代继承，你可以清楚地表明：你只需要受插类的一部分功能。
接口中的哪一部分功能应该被使会，哪一部分应该被忽略，完全由你主导。
这样的成本就是需要额外写出委托函数，但函数都非常的简单，极少可能出错


##做法
<ul>
    <li>1，在子类中新建一个字段，使用引用超类的一个实例，并将它初始化为this.</li>
    <li>2,修改子类的所有函数，让它们不再使用超类。转而使用受委托的字段。再次修改，编译并测试。</li>
    <li>3,去除两具类之间的继承关系，新建一个受托类的对象赋给受托字段。</li>
    <li>4,针对客户端所用的每一个超类函数，为它添加一个简单的委托函数。</li>
    <li>5,编译，测试。</li>
</ul>


继承被滥用就范例就是Stack类，继承Vector类
详见java的java.util.stack类
class Stack<E> extends Vector<E> 
以下样例代码
##Src

```java
public class MyStack extends Vector {

  public void push(Object element) {
    insertElementAt(element, 0);
  }

  public Object pop() {
    Object result = firstElement();
    removeElement(result);
    return result;
  }
}
```


##Refactor
要把这里的继承关系改为委托关系，
1，在MyStack中新建一个字段。用于保存Vector对象
```java
public class MyStack extends Vector {

  private Vector vector = this;

  public void push(Object element) {
    vector.insertElementAt(element, 0);
  }

  public Object pop() {
    Object result = vector.firstElement();
    vector.removeElement(result);
    return result;
  }
}
```

2,修改完成后，去险继承关系 。
对于Stack的函数，添加简单的委托函数。
```java
public class MyStack {

  private Vector vector = new Vector();

  public void push(Object element) {
    vector.insertElementAt(element, 0);
  }

  public Object pop() {
    Object result = vector.firstElement();
    vector.removeElement(result);
    return result;
  }

  public int size() {
    return this.vector.size();
  }

  public boolean isEmpty() {
    return vector.isEmpty();
  }
}
```

