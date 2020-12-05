## 差异式编程 (programming by difference)


测试驱动开发并不仅仅是面向对象开发领域的东西。实际上，在上节的例子其实只不过是一段包裹在类之下的过程式代码。
只不过在面向对象系统中我们还有另一个选择：借助助于类的继承，我们可在不直接改动一个类的前提下引入新的特性。
在添加完特性之后，我们便可以弄清楚想要如何添加新特性。
要做到以上这些，关键的技术，还是所谓的"差异式编程(programming by difference)" 

### 示例
```java

public class MailForwarded {

  /**
   * 提取发件人的地址
   *
   * @param message
   * @return
   * @throws MessagingException
   */
  private InternetAddress getFromAddress(Message message) throws MessagingException {
    InternetAddress[] from = message.getFrom();
    if (from != null && from.length > 0) {
      return new InternetAddress(from[0].getFromAddress());
    }
    return new InternetAddress(getDefaultFrom());
  }

  private String getDefaultFrom() {
    return "127.0.0.1";
  }

  public void forwardMessage(Message message) throws MessagingException {
    MimeMessage forward = new MimeMessage();
    forward.setFrom(getFromAddress(message));
  }
}

@Getter
@Setter
@ToString
public class InternetAddress {

  private String fromAddress;

  public InternetAddress(String fromAddress) {
    this.fromAddress = fromAddress;
  }
}

@Getter
@Setter
@ToString
public class Message {

  /** 来源地址 */
  private InternetAddress[] from;
}

public class MessagingException extends Exception {

  public MessagingException(String message) {
    super(message);
  }
}

@Getter
@Setter
@ToString
public class MimeMessage {

  private InternetAddress from;
}

```


## 新需求
>
    现在假设我们面临一个新的需求，需要支持匿名邮件列表！
>


### 1。 使用tdd的模型，先写测试用例
```java
public class TestMessageForwarder {

  @Test
  @DisplayName("匿名邮件")
  public void testAnonymous() throws Exception {
    MessageForwarder forwarder = new MessageForwarder();
    forwarder.forwardMessage(makeFakeMessage());
    assertEquals("anon-method@" + forwarder.getDomain(), cepectedMessage.getFrom()[0].toString());
  }
}

```

### 2. 思考
为了添加这一功能，必须修改MessageForward吗？有没有其他的办法呢？
可以从MessageForwarder派生一个新类AnonymousMessageForwarder,并将测试中原先/使用MessageForwarder地方改为创建/使用
AnonymousMessageForwarder，再来改造下单元测试
```java

```


#
>
    有旅行重构手法都是相当强大的，但重命名类(rename class)是其中最为强大的一项，
    它能能够改变人们看待代码的方式，并使他们注意一些一些以前可能从未考虑过的可能性。
>

差异式编程是一项有用的技术，它能使用快速的做出改动，事后还可以再靠测试的帮助来抱更干净的设计。但想正确的运用该技术，
得注意一些小陷阱，其中之一便是小心别违反liskov转换原则(LSP)


liskov置换原则
在使用继承时我们可能会犯一些细微的错误。
参见代码

