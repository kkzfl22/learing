## 4.重构-策略模式-添加转换方法

由于这是一个长文，分成了好几章来介绍如何重构。

1. [原始代码及准备](messageSender.md)
2. [职责独立](refactor01.md)
3. [去除static关键字](refactor02.md)
4. [接口添加转换方法](refactor03.md)
5. [方法合并](refactor04.md)
6. [使用策略进行重构-Map方式](refactor05.md)
7. [使用策略进行重构-枚举方式](refactor06.md)


>
       经过前两轮的重构之后，已经提取了抽象接口，并将其static去掉。经过这两轮重构后，有没有什么设计是不合理的呢？再次回收代码中:
>

```java
public class MessageSender {

  public void sendMessage(String msgType, MessageData notify) {
    MessageTypeEnum typeEnum = MessageTypeEnum.getEnumType(msgType);
    switch (typeEnum) {
        // 短信发送
      case SMS:
        sendSms(notify.getFunCode(), notify.getNotifyNumber(), notify.getContent());
        break;
        // email的发送
      case EMAIL:
        sendMail(
            notify.getFunCode(), notify.getNotifyNumber(), notify.getTitle(), notify.getContent());
        break;
        // 微信的发送
      case WECHAT:
        sendWxMsg(notify.getFunCode(), notify.getNotifyNumber(), notify.getContent());
        break;
        // 钉钉的消息发送
      case DINGDING:
        sendDingDingMsg(notify.getFunCode(), notify.getNotifyNumber(), notify.getContent());
        break;
        // 企业钉钉的消息发送
      case WORKWECHAT:
        sendWorkWeChatMsg(notify.getFunCode(), notify.getNotifyNumber(), notify.getContent());
        break;
        // 其他不做处理
      case OTHER:
        break;
      default:
        break;
    }
  }

  /**
   * 给一个邮箱，发送邮件
   *
   * @param funCode 功能类型编码
   * @param sendToMail 地址
   * @param subject 主题
   * @param content 内容
   * @return 返回发送结果
   */
  public boolean sendMail(String funCode, String sendToMail, String subject, String content) {
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
  public void sendSms(String funCode, String sendToMobileNos, String smsContent) {
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
  public void sendWxMsg(String funCode, String toUserOpenId, String context) {
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
  public void sendDingDingMsg(String funCode, String toUserOpenId, String context) {
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
  public void sendWorkWeChatMsg(String funCode, String toUserOpenId, String context) {
    FunCodeMsgData dingDingData = new FunCodeMsgData(toUserOpenId, context, funCode);
    MessageSenderInf workWeChatSender = new DingDingSender();
    workWeChatSender.sender(dingDingData);
  }
}
```

通过对MessageSender类的观察，可以发现，这每个方法做的事情都是一样的，都是先将参数封装为目标所需的对象，再将参数传递给发送程序，既然如此
参数的封装也应该在每个渠道发送之前做，由于每个渠道的发送的消息不同，但它们都需要经过转换为目标对象，再才能进行发送。再来看看这个代码，每个
发送渠道的参数都传递了单独的参数。但参数都是一样的，将MessageData折开再发送，这是明显不合理的。

### 4.1 操作步骤

1. 明确修改点。

2. 修改MessageSender的单元测试。

3. 修改MessageSender发送调用。

4. 修改消息按类型发送消息的单元测试。

5. 修改接口，增加转换方法。

6. 对实现添加单元测试。

7. 对原有发送渠道的类增加转换方法。

8. 单元测试运行通过。

9. 修改客户端的对于各渠道发送代码的调用。

10. 运行单元测试以验证全部功能。

### 4.2 明确修改点。

    a. 是MessageSender的参数不合理，统一修改为MessageData对象。
    b. 每个接口都需要做消息的转换。再发送，针对这个消息转换方法来说，是需要放到公共的接口中去的。
    明确这几点后，开始干吧。

### 4.3 修改MessageSender的单元测试


```java
public class TestMockMessageSender {
......
  @Test
  @DisplayName("测试发送短信通知")
  public void testSmsSender() {
    MessageSender instance = new MessageSender();
    MessageData smsData = new MessageData();
    smsData.setNotifyNumber("13412345678");
    smsData.setContent("测试短信息的内容信息");
    instance.sendSms(smsData);
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("邮件发送邮件通知")
  public void testEmailSender() {
    MessageSender instance = new MessageSender();
    MessageData emailData = new MessageData();
    emailData.setNotifyNumber("134@163.com");
    emailData.setTitle("测试邮件通知");
    emailData.setContent("测试邮件的内容信息");
    instance.sendMail(emailData );
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("微信发送通知")
  public void testWechatSender() {
    MessageSender instance = new MessageSender();
    MessageData weChatata = new MessageData();
    weChatata.setFunCode("dataTest");
    weChatata.setNotifyNumber("131234567");
    weChatata.setContent("微信消息内容");
    instance.sendWxMsg(weChatata);
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("钉钉发送通知")
  public void testDingDingSender() {
    MessageSender instance = new MessageSender();
    MessageData dingdingData = new MessageData();
    dingdingData.setFunCode("dataTest");
    dingdingData.setNotifyNumber("131234567");
    dingdingData.setContent("钉钉发送通知");
    instance.sendDingDingMsg(dingdingData);
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("微信企业发送通知")
  public void testWorkWechatSender() {
    MessageSender instance = new MessageSender();
    MessageData workWeChatData = new MessageData();
    workWeChatData.setFunCode("dataTest");
    workWeChatData.setNotifyNumber("1231231");
    workWeChatData.setContent("企业钉钉通知");
    instance.sendWorkWeChatMsg(workWeChatData);
    // 无异常即为成功
    assertTrue(true);
  }
}

```

### 4.4 修改MessageSender发送消息调用。

```java
public class MessageSender {

  public void sendMessage(String msgType, MessageData notify) {
    MessageTypeEnum typeEnum = MessageTypeEnum.getEnumType(msgType);
    switch (typeEnum) {
        // 短信发送
      case SMS:
        sendSms(notify.getFunCode(), notify.getNotifyNumber(), notify.getContent());
        break;
        // email的发送
      case EMAIL:
        sendMail(
            notify.getFunCode(), notify.getNotifyNumber(), notify.getTitle(), notify.getContent());
        break;
        // 微信的发送
      case WECHAT:
        sendWxMsg(notify.getFunCode(), notify.getNotifyNumber(), notify.getContent());
        break;
        // 钉钉的消息发送
      case DINGDING:
        sendDingDingMsg(notify.getFunCode(), notify.getNotifyNumber(), notify.getContent());
        break;
        // 企业钉钉的消息发送
      case WORKWECHAT:
        sendWorkWeChatMsg(notify.getFunCode(), notify.getNotifyNumber(), notify.getContent());
        break;
        // 其他不做处理
      case OTHER:
        break;
      default:
        break;
    }
  }

  /**
   * 给一个邮箱，发送邮件
   *
   * @param emailMsg email消息
   * @return 返回发送结果
   */
  public void sendMail(MessageData emailMsg) {
    EmailMsgData msgData =
        new EmailMsgData(emailMsg.getNotifyNumber(), emailMsg.getTitle(), emailMsg.getContent());
    MessageSenderInf emailSender = new SmsSender();
    emailSender.sender(msgData);
  }

  /**
   * 给单个手机号，发送短信
   *
   * @param smsMsg 短信消息
   */
  public void sendSms(MessageData smsMsg) {
    MsgData smsData = new MsgData(smsMsg.getNotifyNumber(), smsMsg.getContent());
    MessageSenderInf smsSender = new SmsSender();
    smsSender.sender(smsData);
  }

  /**
   * 给单个微信openID，发送模板消息（类似银行交易提醒通知）
   *
   * @param weChatMsg 微信消息
   */
  public void sendWxMsg(MessageData weChatMsg) {
    FunCodeMsgData weChatData =
        new FunCodeMsgData(
            weChatMsg.getNotifyNumber(), weChatMsg.getContent(), weChatMsg.getFunCode());
    MessageSenderInf weChatSender = new WeChatSender();
    weChatSender.sender(weChatData);
  }

  /**
   * 发送钉钉消息
   *
   * @param dingDingMsg 钉钉消息信息
   */
  public void sendDingDingMsg(MessageData dingDingMsg) {
    FunCodeMsgData dingDingData =
        new FunCodeMsgData(
            dingDingMsg.getNotifyNumber(), dingDingMsg.getContent(), dingDingMsg.getFunCode());
    MessageSenderInf dingDingSender = new DingDingSender();
    dingDingSender.sender(dingDingData);
  }

  /**
   * 企业微信消息的发送
   *
   * @param workWeChatMsg 企业微信消息
   */
  public void sendWorkWeChatMsg(MessageData workWeChatMsg) {
    FunCodeMsgData dingDingData =
        new FunCodeMsgData(
            workWeChatMsg.getNotifyNumber(),
            workWeChatMsg.getContent(),
            workWeChatMsg.getFunCode());
    MessageSenderInf workWeChatSender = new DingDingSender();
    workWeChatSender.sender(dingDingData);
  }
}

```

### 4.5 修改消息按类型发送消息的单元测试。

由于之前的单元测试中是以是否调用检查的，那还有没有其他的办法呢？这是我同样使用了mock技术，但是我对代码进行重过改进。
可以更方便的进行单元测试。
我使用按顺序排序来执行单元测试。由于jmockit使用ASM字节码修改技术将类的字节码进行了修改，必须放到后原生单元测试之后。否则单元测试将无法通过。
而在方法调用时使用了mock技术，当方法被调用，则抛出异常。在执行时后将异常进行检查。当做正常逻辑的一部分，则就可以进宪返回值的判断。是不是更
好呢？

```java
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestMockMessageSender {

  private static final String ERROR_MSG = "invoke";

  @Test
  @Order(1)
  @DisplayName("测试发送短信通知")
  public void testSmsSender() {
    MessageSender instance = new MessageSender();
    MessageData smsData = new MessageData();
    smsData.setNotifyNumber("13412345678");
    smsData.setContent("测试短信息的内容信息");
    instance.sendSms(smsData);
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @Order(20)
  @DisplayName("邮件发送邮件通知")
  public void testEmailSender() {
    MessageSender instance = new MessageSender();
    MessageData emailData = new MessageData();
    emailData.setNotifyNumber("134@163.com");
    emailData.setTitle("测试邮件通知");
    emailData.setContent("测试邮件的内容信息");
    instance.sendMail(emailData);
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @Order(30)
  @DisplayName("微信发送通知")
  public void testWechatSender() {
    MessageSender instance = new MessageSender();
    MessageData weChatata = new MessageData();
    weChatata.setFunCode("dataTest");
    weChatata.setNotifyNumber("131234567");
    weChatata.setContent("微信消息内容");
    instance.sendWxMsg(weChatata);
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @Order(40)
  @DisplayName("钉钉发送通知")
  public void testDingDingSender() {
    MessageSender instance = new MessageSender();
    MessageData dingdingData = new MessageData();
    dingdingData.setFunCode("dataTest");
    dingdingData.setNotifyNumber("131234567");
    dingdingData.setContent("钉钉发送通知");
    instance.sendDingDingMsg(dingdingData);
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @Order(50)
  @DisplayName("微信企业发送通知")
  public void testWorkWechatSender() {
    MessageSender instance = new MessageSender();
    MessageData workWeChatData = new MessageData();
    workWeChatData.setFunCode("dataTest");
    workWeChatData.setNotifyNumber("1231231");
    workWeChatData.setContent("企业钉钉通知");
    instance.sendWorkWeChatMsg(workWeChatData);
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @Order(100)
  @DisplayName("测试发送短信mock")
  public void testSmsSenderMock() {
    new MockUp<SmsSender>(SmsSender.class) {
      @Mock
      void sender(MsgData msg) {
        throw new IllegalArgumentException(ERROR_MSG);
      }
    };
    MessageSender instance = new MessageSender();
    MessageData data = new MessageData();
    data.setFunCode("dataTest");
    data.setNotifyNumber("13412345678");
    data.setTitle("测试短信息通知");
    data.setStatus(0);
    data.setContent("测试短信息的内容信息..");

    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              instance.sendMessage(MessageTypeEnum.SMS.getMsgType(), data);
            });
    assertEquals(ERROR_MSG, exception.getMessage());
  }

  @Test
  @Order(110)
  @DisplayName("邮件发送邮件通知Mock")
  public void testEmailSenderMock() {
    new MockUp<EmailSender>(EmailSender.class) {
      @Mock
      void sender(MsgData msg) {
        throw new IllegalArgumentException(ERROR_MSG);
      }
    };
    MessageSender instance = new MessageSender();
    MessageData data = new MessageData();
    data.setFunCode("dataTest");
    data.setNotifyNumber("13412345678@163.com");
    data.setTitle("测试邮件息通知");
    data.setStatus(0);
    data.setContent("测试邮件的内容信息..");

    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              instance.sendMessage(MessageTypeEnum.EMAIL.getMsgType(), data);
            });
    assertEquals(ERROR_MSG, exception.getMessage());
  }

  @Test
  @Order(120)
  @DisplayName("微信发送通知mock")
  public void testWechatSenderMock() {
    new MockUp<WeChatSender>() {
      @Mock
      void sender(MsgData msg) {
        throw new IllegalArgumentException(ERROR_MSG);
      }
    };
    MessageSender instance = new MessageSender();

    MessageData data = new MessageData();
    data.setFunCode("dataTest");
    data.setNotifyNumber("1234567");
    data.setTitle("测试微信息通知");
    data.setStatus(0);
    data.setContent("测试微信的内容信息..");

    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              instance.sendMessage(MessageTypeEnum.WECHAT.getMsgType(), data);
            });
    assertEquals(ERROR_MSG, exception.getMessage());
  }

  @Test
  @Order(130)
  @DisplayName("钉钉发送通知mock")
  public void testDingDingSenderMock() {
    new MockUp<DingDingSender>() {
      @Mock
      void sender(MsgData msg) {
        throw new IllegalArgumentException(ERROR_MSG);
      }
    };

    MessageSender instance = new MessageSender();
    MessageData data = new MessageData();
    data.setFunCode("dataTest");
    data.setNotifyNumber("1234567");
    data.setTitle("测试钉钉通知");
    data.setStatus(0);
    data.setContent("测试钉钉的内容信息..");
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              instance.sendMessage(MessageTypeEnum.DINGDING.getMsgType(), data);
            });
    assertEquals(ERROR_MSG, exception.getMessage());
  }

  @Mocked private WorkWeChatSender workWeChatSender;

  @Test
  @Order(140)
  @DisplayName("微信企业发送通知mock")
  public void testWorkWechatSenderMock() {
    new MockUp<WorkWeChatSender>() {
      @Mock
      void sender(MsgData msg) {
        throw new IllegalArgumentException(ERROR_MSG);
      }
    };

    MessageSender instance = new MessageSender();
    MessageData data = new MessageData();
    data.setFunCode("dataTest");
    data.setNotifyNumber("1234567");
    data.setTitle("测试企业微信通知");
    data.setStatus(0);
    data.setContent("测试企业微信的内容信息..");
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              instance.sendMessage(MessageTypeEnum.WORKWECHAT.getMsgType(), data);
            });
    assertEquals(ERROR_MSG, exception.getMessage());
  }
}
```

### 4.6 修改接口，增加转换方法。

单元测试已经安全的修改完成，可以安全的进行重构。来将接口中增加转换方法吧吧。

```java
public interface MessageSenderInf {

  /**
   * 消息发送
   *
   * @param msg 发送消息的内容
   */
  void sender(MsgData msg);

  /**
   * 消息转换方法
   *
   * @param msgData 发送的消息类型
   * @return 当前转换的类型
   */
  MsgData parse(MessageData msgData);
}
```

### 4.7 优先对实现添加单元测试。 

sms消息转换的单元测试添加

```java
public class TestSmsSender {
  ......
  @Test
  @DisplayName("测试短信的转换")
  public void testSmsParse() {
    SmsSender instance = new SmsSender();
    MessageData smsData = new MessageData();
    smsData.setNotifyNumber("13451913228");
    smsData.setContent("短信通知...");
    MsgData smsParse = instance.parse(smsData);
    // 检查发送结果
    assertEquals(smsData.getContent(), smsParse.getContext());
    assertEquals(smsData.getNotifyNumber(), smsParse.getToId());
  }
}
```
email的转换的单元测试添加
```java
public class TestEmailSender {
......
  @Test
  @DisplayName("测试email的转换")
  public void testSmsParse() {
    EmailSender instance = new EmailSender();
    MessageData smsData = new MessageData();
    smsData.setNotifyNumber("134@163.com");
    smsData.setContent("邮件通知...");
    MsgData smsParse = instance.parse(smsData);
    // 检查发送结果
    assertEquals(smsData.getContent(), smsParse.getContext());
    assertEquals(smsData.getNotifyNumber(), smsParse.getToId());
  }
}
```
dingding发送的转换的单元测试添加
```java
public class TestDingDingSender {
......
  @Test
  @DisplayName("钉钉的转换")
  public void testParseDingDing() {
    DingDingSender instance = new DingDingSender();
    MessageData dingMsg = new MessageData();
    dingMsg.setNotifyNumber("123464");
    dingMsg.setContent("钉钉的内容");
    MsgData dingParse = instance.parse(dingMsg);
    // 检查转换的结果
    assertEquals(dingMsg.getContent(), dingParse.getContext());
    assertEquals(dingMsg.getNotifyNumber(), dingParse.getToId());
  }
}
```
wechat的消息转换的单元测试添加
```java
public class TestWeChatSender {
......
  @Test
  @DisplayName("测试微信的转换")
  public void weChatParse()
  {
    WeChatSender instance = new WeChatSender();
    MessageData wechatData = new MessageData();
    wechatData.setFunCode("dataTest");
    wechatData.setNotifyNumber("123456");
    wechatData.setContent("微信通知...");
    FunCodeMsgData weChatParse = (FunCodeMsgData) instance.parse(wechatData);
    assertEquals(wechatData.getFunCode(),weChatParse.getFunCode());
    assertEquals(wechatData.getNotifyNumber(),weChatParse.getToId());
    assertEquals(wechatData.getContent(),weChatParse.getContext());
  }
}
```
workwechat的消息转换的单元测试添加
```java
public class TestWorkWeChatSender {
....
  @Test
  @DisplayName("测试工作微信消息的转换")
  public void workWeChatSender() {
    WorkWeChatSender instance = new WorkWeChatSender();

    MessageData workWechatData = new MessageData();
    workWechatData.setFunCode("dataTest");
    workWechatData.setNotifyNumber("123456");
    workWechatData.setContent("工作微信通知...");

    FunCodeMsgData workWeChatParse = (FunCodeMsgData) instance.parse(workWechatData);
    assertEquals(workWechatData.getNotifyNumber(), workWeChatParse.getToId());
    assertEquals(workWechatData.getFunCode(), workWeChatParse.getFunCode());
    assertEquals(workWechatData.getContent(), workWeChatParse.getContext());
  }
}
```

### 4.8 对原有发送渠道的类增加转换方法。

sms发送的消息转换

```java
public class SmsSender implements MessageSenderInf {
......
  @Override
  public MsgData parse(MessageData msgData) {
    return new MsgData(msgData.getNotifyNumber(), msgData.getContent());
  }
}
```
email发送的消息转换
````java
public class EmailSender implements MessageSenderInf {
......
  @Override
  public MsgData parse(MessageData emailMsg) {
    return new EmailMsgData(emailMsg.getNotifyNumber(), emailMsg.getTitle(), emailMsg.getContent());
  }
}
````
dingding发送消息的转换
```java
public class DingDingSender implements MessageSenderInf {
......
  @Override
  public MsgData parse(MessageData dingDingMsg) {
    return new FunCodeMsgData(
        dingDingMsg.getNotifyNumber(), dingDingMsg.getContent(), dingDingMsg.getFunCode());
  }
}
```
wechat发送消息转换
```java
public class WeChatSender implements MessageSenderInf {
......
  @Override
  public MsgData parse(MessageData weChatMsg) {
    return new FunCodeMsgData(
        weChatMsg.getNotifyNumber(), weChatMsg.getContent(), weChatMsg.getFunCode());
  }
}
```
workwechat发送消息转换
```java
public class WorkWeChatSender implements MessageSenderInf {
......
  @Override
  public MsgData parse(MessageData workWeChatMsg) {
    return new FunCodeMsgData(
        workWeChatMsg.getNotifyNumber(), workWeChatMsg.getContent(), workWeChatMsg.getFunCode());
  }
}
```

### 4.9 单元测试运行通过。

运行单元测试以检查代码编写中是否存在问题。

### 4.10 修改客户端的对于各渠道发送代码的调用。

```java

public class MessageSender {

  public void sendMessage(String msgType, MessageData notify) {
    MessageTypeEnum typeEnum = MessageTypeEnum.getEnumType(msgType);
    switch (typeEnum) {
        // 短信发送
      case SMS:
        sendSms(notify);
        break;
        // email的发送
      case EMAIL:
        sendMail(notify);
        break;
        // 微信的发送
      case WECHAT:
        sendWxMsg(notify);
        break;
        // 钉钉的消息发送
      case DINGDING:
        sendDingDingMsg(notify);
        break;
        // 企业钉钉的消息发送
      case WORKWECHAT:
        sendWorkWeChatMsg(notify);
        break;
        // 其他不做处理
      case OTHER:
        break;
      default:
        break;
    }
  }

  /**
   * 给一个邮箱，发送邮件
      *
   * @param emailMsg email消息
   * @return 返回发送结果
      */

    public void sendMail(MessageData emailMsg) {
    
    MessageSenderInf emailSender = new EmailSender();
    emailSender.sender(emailSender.parse(emailMsg));
  }

  /**
   * 给单个手机号，发送短信
      *
   * @param smsMsg 短信消息
      */

    public void sendSms(MessageData smsMsg) {
    
    MessageSenderInf smsSender = new SmsSender();
    smsSender.sender(smsSender.parse(smsMsg));
  }

  /**
   * 给单个微信openID，发送模板消息（类似银行交易提醒通知）
      *
   * @param weChatMsg 微信消息
      */

    public void sendWxMsg(MessageData weChatMsg) {
    
    MessageSenderInf weChatSender = new WeChatSender();
    weChatSender.sender(weChatSender.parse(weChatMsg));
  }

  /**
   * 发送钉钉消息
      *
   * @param dingDingMsg 钉钉消息信息
      */

    public void sendDingDingMsg(MessageData dingDingMsg) {
    
    MessageSenderInf dingDingSender = new DingDingSender();
    dingDingSender.sender(dingDingSender.parse(dingDingMsg));
  }

  /**
   * 企业微信消息的发送
      *
   * @param workWeChatMsg 企业微信消息
      */

    public void sendWorkWeChatMsg(MessageData workWeChatMsg) {
    
    MessageSenderInf workWeChatSender = new WorkWeChatSender();
    workWeChatSender.sender(workWeChatSender.parse(workWeChatMsg));
  }
}

```

### 4.11 运行单元测试以验证全部功能。

至此对于在接口中添加转换方法的重构结束。
