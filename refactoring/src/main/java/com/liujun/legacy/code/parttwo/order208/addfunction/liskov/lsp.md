## liskov (LSP置换原则)

这是（LSP置换原则的一个典型错误）

```java

public class Rectangle {

  private int x;

  private int y;

  private int width;

  private int height;

  public Rectangle(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getArea() {
    return width * height;
  }
}


public class Square extends Rectangle {

  public Square(int x, int y, int width) {
    super(x, y, width, width);
  }

  @Override
  public void setWidth(int width) {
    super.setWidth(width);
    super.setHeight(width);
  }

  @Override
  public void setHeight(int height) {
    super.setHeight(height);
    super.setWidth(height);
  }
}


```

对于这个类如果结果为12，这个正方形不能算是个真正的正方形了，于是，我们在Square类中重写了Rectangle的setWidth/setHeight方法，
以确保正方式的身份不被改变，例如，我们可以设置setHeight和setWidth,都去修改Square的宽,但这一来，就违反了直觉的结果：
如果长宽分别被设置为3和4之后，人们当然期望面积为12，然尔得到的结果却是16.

这是违反了Liskov置换原则(LSP)的经典案例之一。子类对象应当能够用于替换代码中出现的它们的父类的对象，
不管后者被用在什么地方。如果不能的话，代码中就有可能悄无声息地抛出一些错误。


Liskov置换原则意味着一个类的给定客户代码应当能够在毫不知情的情况下使用该类的任何子类对象。不存在任何“机械性”的方式来避免违反该原则。
一个类是否符合Liskov置换原则取决于它的客户代码，以及客户代码对代码行为或结果的期望。
不过存在着一些一般规则的：
（1）尽可能避免重写具体方法。
 (2) 倘若直接的重写了某个具体方法，那么看看能够在重写方法中调用被重写的那个方法。 

