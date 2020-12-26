## 2. 重构-策略模式-职责独立

由于这是一个长文，分成了好几章来介绍如何重构。

1. [原始代码及准备](messageSender.md)
2. [职责独立](refactor01.md)
3. [去除static关键字](refactor02.md)
4. [接口添加转换方法](refactor03.md)
5. [方法合并](refactor04.md)
6. [使用策略进行重构-Map方式](refactor05.md)
7. [使用策略进行重构-枚举方式](refactor06.md)





**现在来分析下代码的问题**

先从入口MessageSender开始吧，这个类第一眼看上去，觉得很简单吧，就是按类型判断下，执行NotifySendUtils类中
的发送逻辑代码。这样子看这个类没有什么大的问题么，再说NotifySendUtils吧，它封装了各种发送渠道的调用方法，还做成了静态方法。这里明显就
存在问题了， 首先从职责上来说，NotifySendUtils类承担了过多的职责，它封装了发送短信，还封装了发送邮件，以及其他几种渠道的发送代码，
职责过多。再者NotifySendUtils的方法设计成静态方法我觉得也是存在问题的，因为在现实情况中它是有依赖的，它依赖于各种消息发送渠道的物理调用。
这样子是不适合做静态方法的，要做静态方法，首先的原则就是不要有依赖，比如工具类等。

**针对这个职责过多怎么重构呢？**
通常我的做法将发送渠道的相关代码都迁移到独立类中。这样每个发送渠道都可以单独管理，每次修改代码，也只会影响到修改的，不会影响到其他的代
码，但如果都在一个类中，当修改代码时，不小心误删了其他的代码，所造成的问题，很可能是难以察觉的。如果是一个团队维护的代码，那所造成的问
题将进一步放大，问题定位的成本更高。

### 2.1 迁移方法
将原本NotifySendUtils的5个方法进行复制
#### 2.1.1 将sendSms复制至SmsSender类中

```java
public class SmsSender {

  /**
   * 给单个手机号，发送短信
   *
   * @param funCode 功能类型编码
   * @param sendToMobileNos 接收的号码
   * @param smsContent 短信内容
   */
  public static void sendSms(String funCode, String sendToMobileNos, String smsContent) {
    System.out.println("短信的发送开始.....");
    System.out.println(
            "短信发送: " + ",sendToMobileNos:" + sendToMobileNos + ",smsContent:" + smsContent);
    System.out.println("短信的发送结束.....");
  }
}
```
#### 2.1.2 将sendMail复制至EmailSender类中

```java
public class EmailSender {

  /**
   * 给一个邮箱，发送邮件
   *
   * @param funCode 功能类型编码
   * @param sendToMail 地址
   * @param subject 主题
   * @param content 内容
   * @return 返回发送结果
   */
  public static boolean sendMail(
          String funCode, String sendToMail, String subject, String content) {
    System.out.println("邮件的发送开始.....");
    System.out.println(
            "邮件发送: " + ",sendToMail:" + sendToMail + ",subject:" + subject + ",content:" + content);
    System.out.println("短信的发送结束.....");
    return true;
  }
}
```
#### 2.1.3 将sendWxMsg复制至WeChatSender类中

```java
public class WeChatSender {

    /**
     * 给单个微信openID，发送模板消息（类似银行交易提醒通知）
     *
     * @param funCode 功能类型编码
     * @param toUserOpenId 发送的目标用户（微信openId)
     * @param context 内容
     */
    public static void sendWxMsg(String funCode, String toUserOpenId, String context) {
        System.out.println("微信消息发送开始.....");
        System.out.println(
                "微信消息:funCode" + funCode + ",toUserOpenId:" + toUserOpenId + "，context:" + context);
        System.out.println("微信消息发送结束.....");
    }
}
```
#### 2.1.4 将sendDingDingMsg复制至DingDingSender类中

```java
public class DingDingSender {

  /**
   * 发送钉钉消息
   *
   * @param funCode 功能类型编码
   * @param toUserOpenId 接收的id
   * @param context 发送的内容
   */
  public static void sendDingDingMsg(String funCode, String toUserOpenId, String context) {
    System.out.println("钉钉消息发送开始.....");
    System.out.println(
        "钉钉消息:funCode" + funCode + ",toUserOpenId:" + toUserOpenId + ",tplData:" + context);
    System.out.println("钉钉消息发送结束.....");
  }
}
```
#### 2.1.5 将sendWorkWeChatMsg复制至WorkWeChatSender类中

```java
public class WorkWeChatSender {

  /**
   * 企业微信消息的发送
   *
   * @param funCode 功能类型编码
   * @param toUserOpenId 接收消息的id
   * @param context 发送的内容
   */
  public static void sendWorkWeChatMsg(String funCode, String toUserOpenId, String context) {
    System.out.println("企业微信消息发送开始.....");
    System.out.println(
        "企业微信消息:funCode" + funCode + ",toUserOpenId:" + toUserOpenId + ",tplData:" + context);
    System.out.println("企业微信消息发送结束.....");
  }
}
```
至此，第一步将代码复制到各个类的工作完成了。是不是觉得很不可思议，第一步居然是拷贝代码，而且还是直接拷贝一样的代码，难道不能直接把代码迁移到
目标类中吗？一般是不建议这么做的,有以下原因：

1. 直接迁移代码，必然导致所依赖的所有类都编译报错，而一旦依赖此类文件的特别多，必然导致一改改一片的情况。
2. 在没有将旧代码重构完成之前就修改原来的代码，可能会导致程序客户端被反复的修改。
3. 由于自己提交的文件还可能被其他人调用，如果将文件修改到一半就提交了，其他人的引用必须报错，也会影响其他人的工作。

  

基于以上三点考量，正确的修改姿势：

1. 将原先的代码复制一份出来，用于做重构的代码基线。
2. 将单元测试同样的也拷贝到对应的文件中。
3. 重构一点代码，运行一遍单元测试。确保修改是一点一点进行的，不要步幅过大。
4. 重构完成后，考量原先的代码的处理，自己给出修改方案。并加以实施。
5. 运行单元测试。确保代码还能正确的运行。





### 2.2 老规矩，开始重构之前先写单元测试.
sms的单元测试 
```java
public class TestSmsSender {
  @Test
  @DisplayName("测试短信发送")
  public void testSmsSender() {
    SmsSender.sendSms("datTest", "1341231241", "this is test");
    // 无异常即为成功
    assertTrue(true);
  }
}
```
email的单元测试
```java
public class TestEmailSender {

  @Test
  @DisplayName("邮件发送")
  public void testSenderEmail() {
    EmailSender.sendMail("data", "123@163.com", "this is test", "context");
    // 无异常即为成功
    assertTrue(true);
  }
}
```

dingding的单元测试
```java
public class TestDingDingSender {

  @Test
  @DisplayName("发送钉钉测试")
  public void senderDingDing() {
    DingDingSender.sendDingDingMsg("test", "1231", "data test");
    // 无异常即为成功
    assertTrue(true);
  }
}
```

wechat的单元测试
```java
public class TestWeChatSender {

  @Test
  @DisplayName("测试微信发送消息")
  public void senderWeChat() {
    WeChatSender.sendWxMsg("dataFun", "1231", "this is test");
    // 无异常即为成功
    assertTrue(true);
  }
}
```

workWechat的单元测试
```java
public class TestWorkWeChatSender {

  @Test
  @DisplayName("测试企业微信")
  public void senderWorkWeChat() {
    WorkWeChatSender.sendWorkWeChatMsg("dataFun", "1231", "this is test Count");
    // 无异常即为成功
    assertTrue(true);
  }
}
```

### 2.3 首先修改单元测试，然后再去掉static关键字。
单元测试修改
```java
public class TestSmsSender {
  @Test
  @DisplayName("测试短信发送")
  public void testSmsSender() {
    SmsSender instance = new SmsSender();
    instance.sendSms("datTest", "1341231241", "this is test");
    // 无异常即为成功
    assertTrue(true);
  }
}
```
类的修改
```java
public class SmsSender {

  /**
   * 给单个手机号，发送短信
   *
   * @param funCode 功能类型编码
   * @param sendToMobileNos 接收的号码
   * @param smsContent 短信内容
   */
  public void sendSms(String funCode, String sendToMobileNos, String smsContent) {
    System.out.println("短信的发送开始.....");
    System.out.println(
        "短信发送: " + ",sendToMobileNos:" + sendToMobileNos + ",smsContent:" + smsContent);
    System.out.println("短信的发送结束.....");
  }
}
```

其他类与此类似修改掉单元测试。再去除static关键字

### 2.4 共性抽取。
这几个类他们都是在做发消息，所不同的是发送渠道。所有的操作都是围饶着发消息而进行的，而不同点在于不同的发送渠道所需参数有所不同。针对这一类
情况，我会采用抽取接口，可能有人会说了，使用抽象类也可以啊！是的没错，采用抽象类也可以，但我一般不推荐使用抽象类，抽象类我一搬推荐在模板这
一类方法中，逻辑固定，子类只重写必须的方法。在这一类受限的场景中，抽象类才真正的发挥作用。而在java中采用的又是单根继承，只能继承一个父类，
这样就可能导致继承的层级非常的深。接口在这方面就没有限制，一个类可以实现多个接口，这样就可以任意组合不同接口，为程序提供了更大的灵活性。

到了该抽取接口的时候了，首先我们定义一个发送消息的接口MessageSenderInf,参数为所有渠道共用的参数。

```java
public interface MessageSenderInf {

  /**
   * 消息发送
   *
   * @param msg 发送消息的内容
   */
  void sender(MsgData msg);
}

```
消息参数基础类
```java
public class MsgData {

  /** 接收的id */
  private String toId;

  /** 发送的消息的内容 */
  private String context;

  public MsgData(String toId, String context) {
    this.toId = toId;
    this.context = context;
  }

  public String getToId() {
    return toId;
  }

  public void setToId(String toId) {
    this.toId = toId;
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("MsgData{");
    sb.append("toId='").append(toId).append('\'');
    sb.append(", context='").append(context).append('\'');
    sb.append('}');
    return sb.toString();
  }
}

```

消息带功能编码的类，由于功能编码非所有渠道所共有，一般建议拆开，但如果就一个参数合到一起，也是可以接受的，但我一般都是拆开，我不想这一两个
参数去污染公共类，公共是所有都有的，非所有都有的，那就是需要额外定义。这样不会因为一个参数而不论不类。而后期再做修改的时候，也更加的容易，
还有一个原因就，坏习惯会传染，如果自己就打破了这个类只封装公有的原则，其他的人也肯定会跟着效仿。

消息扩展类
```java
public class FunCodeMsgData extends MsgData {

  /** 功能类型编码 */
  private String funCode;

  public FunCodeMsgData(String toId, String context, String funCode) {
    super(toId, context);
    this.funCode = funCode;
  }

  public String getFunCode() {
    return funCode;
  }

  public void setFunCode(String funCode) {
    this.funCode = funCode;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("FunCodeMsgData{");
    sb.append("funCode='").append(funCode).append('\'');
    sb.append('}');
    sb.append(super.toString());
    return sb.toString();
  }
}
```

先对消息的发送的单元测试进行改造，再来修改原代码。
sms通知的单元测试的修改
```java
public class TestSmsSender {
  @Test
  @DisplayName("测试短信发送")
  public void testSmsSender() {
    SmsSender instance = new SmsSender();
    MsgData msgData = new MsgData("1341345678", "this is test");
    instance.sender(msgData);
    // 无异常即为成功
    assertTrue(true);
  }
}
```
sms发送实现的修改
```java
public class SmsSender implements MessageSenderInf {

  /**
   * 给单个手机号，发送短信
   *
   * @param msg 发送消息的内容
   */
  @Override
  public void sender(MsgData msg) {
    System.out.println("短信的发送开始.....");
    System.out.println(
        "短信发送: " + ",sendToMobileNos:" + msg.getToId() + ",smsContent:" + msg.getContext());
    System.out.println("短信的发送结束.....");
  }
}

```

email的通知的单元测试的修改

在进行修改的时候发现，对于email是存在主题的，这个需要由发送通知时进行设置，所以增加一个email的消息的类

```java
public class EmailMsgData extends MsgData {

  /** 主题 */
  private String subject;

  public EmailMsgData(String toId,  String subject,String context) {
    super(toId, context);
    this.subject = subject;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("EmailMsgData{");
    sb.append("subject='").append(subject).append('\'');
    sb.append('}');
    sb.append(super.toString());
    return sb.toString();
  }
}
```


```java
public class TestEmailSender {

  @Test
  @DisplayName("邮件发送")
  public void testSenderEmail() {
    EmailSender instance = new EmailSender();
    MsgData msg = new MsgData("123@163.com", "this context");
    instance.sender(msg);
    // 无异常即为成功
    assertTrue(true);
  }
}
```

email的发送实现的修改


```java
public class EmailSender implements MessageSenderInf {

  /**
   * 给一个邮箱，发送邮件
   *
   * @param msg 发送的消息
   */
  @Override
  public void sender(MsgData msg) {
    System.out.println("邮件的发送开始.....");
    System.out.println(
        "邮件发送: "
            + ",sendToMail:"
            + msg.getToId()
            + ",subject: notify, content:"
            + msg.getContext());
    System.out.println("短信的发送结束.....");
  }
}
```



dingding的单元测试的修改
```java
public class TestDingDingSender {

  @Test
  @DisplayName("发送钉钉测试")
  public void senderDingDing() {
    DingDingSender instance = new DingDingSender();
    FunCodeMsgData funCodeMsgData = new FunCodeMsgData("12312", "ding ding msg test", "funcode");
    instance.sender(funCodeMsgData);
    // 无异常即为成功
    assertTrue(true);
  }
}

```

dingding的实现的修改
```java
public class DingDingSender implements MessageSenderInf {

  /**
   * 发送钉钉消息
   *
   * @param msg 消息信息
   */
  @Override
  public void sender(MsgData msg) {
    System.out.println("钉钉消息发送开始.....");
    FunCodeMsgData codeMsgData = (FunCodeMsgData) msg;
    System.out.println(
        "钉钉消息:funCode"
            + codeMsgData.getFunCode()
            + ",toUserOpenId:"
            + codeMsgData.getToId()
            + ",tplData:"
            + codeMsgData.getContext());
    System.out.println("钉钉消息发送结束.....");
  }
}
```

wechat的单元测试修改
```java
public class TestWeChatSender {

  @Test
  @DisplayName("测试微信发送消息")
  public void senderWeChat() {
    WeChatSender instance = new WeChatSender();
    FunCodeMsgData funCodeMsg = new FunCodeMsgData("1231231", "this is test", "datafun");
    instance.sender(funCodeMsg);
    // 无异常即为成功
    assertTrue(true);
  }
}
```

wechat的单元测试修改
```java
public class TestWeChatSender {

  @Test
  @DisplayName("测试微信发送消息")
  public void senderWeChat() {
    WeChatSender instance = new WeChatSender();
    FunCodeMsgData funCodeMsg = new FunCodeMsgData("1231231", "this is test", "datafun");
    instance.sender(funCodeMsg);
    // 无异常即为成功
    assertTrue(true);
  }
}
```

workwechat的单元测试修改
```java
public class TestWorkWeChatSender {

  @Test
  @DisplayName("测试企业微信")
  public void senderWorkWeChat() {
    WorkWeChatSender instance = new WorkWeChatSender();
    FunCodeMsgData codeMsgData = new FunCodeMsgData("12312", "this is test", "datafun");
    instance.sender(codeMsgData);
    // 无异常即为成功
    assertTrue(true);
  }
}
```

workwechat的发送实现的修改
```java
public class WorkWeChatSender implements MessageSenderInf {

  /**
   * 企业微信消息的发送
   *
   * @param msgData 消息内容
   */
  @Override
  public void sender(MsgData msgData) {
    System.out.println("企业微信消息发送开始.....");
    FunCodeMsgData funCodeMsg = (FunCodeMsgData) msgData;
    System.out.println(
        "企业微信消息:funCode"
            + funCodeMsg.getFunCode()
            + ",toUserOpenId:"
            + funCodeMsg.getToId()
            + ",context:"
            + funCodeMsg.getContext());
    System.out.println("企业微信消息发送结束.....");
  }
}
```

经过此所有的单元测试与实现都已经修改完成。

### 2.5 修改客户端调用
针对各种渠道的调用测试进行修改，重构前必须确保有单元测试，在之前各发送渠道的单元测试已经存在，便可直接重构。
```java
public class NotifySendUtils {
  /**
   * 给一个邮箱，发送邮件
   *
   * @param funCode 功能类型编码
   * @param sendToMail 地址
   * @param subject 主题
   * @param content 内容
   * @return 返回发送结果
   */
  public static boolean sendMail(
      String funCode, String sendToMail, String subject, String content) {
    EmailMsgData msgData = new EmailMsgData(sendToMail, subject, content);
    MessageSenderInf emailSender = new SmsSender();
    emailSender.sender(msgData);
    return true;
  }

  /**
   * 给单个手机号，发送短信
   *
   * @param funCode 功能类型编码
   * @param sendToMobileNos 接收的号码
   * @param smsContent 短信内容
   */
  public static void sendSms(String funCode, String sendToMobileNos, String smsContent) {
    MsgData smsData = new MsgData(sendToMobileNos, smsContent);
    MessageSenderInf smsSender = new SmsSender();
    smsSender.sender(smsData);
  }

  /**
   * 给单个微信openID，发送模板消息（类似银行交易提醒通知）
   *
   * @param funCode 功能类型编码
   * @param toUserOpenId 发送的目标用户（微信openId)
   * @param context 内容
   */
  public static void sendWxMsg(String funCode, String toUserOpenId, String context) {
    FunCodeMsgData weChatData = new FunCodeMsgData(toUserOpenId, context, funCode);
    MessageSenderInf weChatSender = new WeChatSender();
    weChatSender.sender(weChatData);
  }

  /**
   * 发送钉钉消息
   *
   * @param funCode 功能类型编码
   * @param toUserOpenId 接收的id
   * @param context 发送的内容
   */
  public static void sendDingDingMsg(String funCode, String toUserOpenId, String context) {
    FunCodeMsgData dingDingData = new FunCodeMsgData(toUserOpenId, context, funCode);
    MessageSenderInf dingDingSender = new DingDingSender();
    dingDingSender.sender(dingDingData);
  }

  /**
   * 企业微信消息的发送
   *
   * @param funCode 功能类型编码
   * @param toUserOpenId 接收消息的id
   * @param context 发送的内容
   */
  public static void sendWorkWeChatMsg(String funCode, String toUserOpenId, String context) {
    FunCodeMsgData dingDingData = new FunCodeMsgData(toUserOpenId, context, funCode);
    MessageSenderInf workWeChatSender = new DingDingSender();
    workWeChatSender.sender(dingDingData);
  }
}

```



### 2.6  运行单元测试。

运行单元测试确保修改后的代码都可以成功的运行。






### 2.7 关于我重构的一些方式及方法。
这里我重构了发送的代码，将原来的发送点，变成了发送的客户端。这样的改动是否合理呢？ 我觉得是有必须，重构我一直遵循的一个原则就是小步快走。
而不是一步到位，一步到位固然很爽，但是我们在重构的时候，经常涉及到的文件可能是很多个，几十，有时甚至上百个，而且改动的周期可能会很长。如果
我们改动不能做到每步都可测试，那么最终的结果出现意外的情况必然很多，意外情况将导致更长的改动周期。令重构进行的相当不顺，而改用小步快走，每
改动一步，都确保是可以测试的，可执行的，那么最终的结果肯定也是可以执行的。这样的改动周期也不会比原来一步到位时间的长，由于我们每步都做到了可测试
出现意义情况的几率将大大的降低，由于代码随时都是可以运行的，对他人造成的影响也更少。所以我一般采用的的是小步快走的策略。

