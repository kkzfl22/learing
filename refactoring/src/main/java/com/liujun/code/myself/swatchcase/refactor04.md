## 5 重构-策略模式-方法合并

由于这是一个长文，分成了好几章来介绍如何重构。

1. [原始代码及准备](messageSender.md)
2. [职责独立](refactor01.md)
3. [去除static关键字](refactor02.md)
4. [接口添加转换方法](refactor03.md)
5. [方法合并](refactor04.md)
6. [使用策略进行重构-Map方式](refactor05.md)
7. [使用策略进行重构-枚举方式](refactor06.md)



> 经过前两轮的重构之后，已经提取了抽象接口，并将其static去掉。还为消息发送接口添加了转换方法。经过这几轮的修改，代码已经看起来整洁、
      清晰了许多。那还有槽点吗？
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

>
    仔细检查了上面的代码后，发现各消息的发送看起来代码都差不多。而且就这么两行代码，单独一个方法，有点多余。那就合并吧
>

### 5.1 合并步骤
1. 修改单元测试。去掉对于发送渠道的的调用。
2. 将代码进行合并操作。
3. 删除空方法
4. 运行单元测试，确保修改正确。

### 5.2 修改单元测试。
修改单元测试。去掉对于发送渠道的的调用。
同时还删除了单元测试按顺序执行的相关内容。
```java
public class TestMockMessageSender {

  private static final String ERROR_MSG = "invoke";

  @Test
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

### 5.3 MessageSender将代码进行合并操作。

```java
public class MessageSender {

  public void sendMessage(String msgType, MessageData notify) {
    MessageTypeEnum typeEnum = MessageTypeEnum.getEnumType(msgType);
    switch (typeEnum) {
        // 短信发送
      case SMS:
        MessageSenderInf smsSender = new SmsSender();
        smsSender.sender(smsSender.parse(notify));
        break;
        // email的发送
      case EMAIL:
        MessageSenderInf emailSender = new EmailSender();
        emailSender.sender(emailSender.parse(notify));
        break;
        // 微信的发送
      case WECHAT:
        MessageSenderInf weChatSender = new WeChatSender();
        weChatSender.sender(weChatSender.parse(notify));
        break;
        // 钉钉的消息发送
      case DINGDING:
        MessageSenderInf dingDingSender = new DingDingSender();
        dingDingSender.sender(dingDingSender.parse(notify));
        break;
        // 企业钉钉的消息发送
      case WORKWECHAT:
        MessageSenderInf workWeChatSender = new WorkWeChatSender();
        workWeChatSender.sender(workWeChatSender.parse(notify));
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

  }

  /**
   * 给单个手机号，发送短信
   *
   * @param smsMsg 短信消息
   */
  public void sendSms(MessageData smsMsg) {

  }

  /**
   * 给单个微信openID，发送模板消息（类似银行交易提醒通知）
   *
   * @param weChatMsg 微信消息
   */
  public void sendWxMsg(MessageData weChatMsg) {

  }

  /**
   * 发送钉钉消息
   *
   * @param dingDingMsg 钉钉消息信息
   */
  public void sendDingDingMsg(MessageData dingDingMsg) {

  }

  /**
   * 企业微信消息的发送
   *
   * @param workWeChatMsg 企业微信消息
   */
  public void sendWorkWeChatMsg(MessageData workWeChatMsg) {

  }
}

```

### 5.4 删除空方法

```java
public class MessageSender {

  public void sendMessage(String msgType, MessageData notify) {
    MessageTypeEnum typeEnum = MessageTypeEnum.getEnumType(msgType);
    switch (typeEnum) {
        // 短信发送
      case SMS:
        MessageSenderInf smsSender = new SmsSender();
        smsSender.sender(smsSender.parse(notify));
        break;
        // email的发送
      case EMAIL:
        MessageSenderInf emailSender = new EmailSender();
        emailSender.sender(emailSender.parse(notify));
        break;
        // 微信的发送
      case WECHAT:
        MessageSenderInf weChatSender = new WeChatSender();
        weChatSender.sender(weChatSender.parse(notify));
        break;
        // 钉钉的消息发送
      case DINGDING:
        MessageSenderInf dingDingSender = new DingDingSender();
        dingDingSender.sender(dingDingSender.parse(notify));
        break;
        // 企业钉钉的消息发送
      case WORKWECHAT:
        MessageSenderInf workWeChatSender = new WorkWeChatSender();
        workWeChatSender.sender(workWeChatSender.parse(notify));
        break;
        // 其他不做处理
      case OTHER:
        break;
      default:
        break;
    }
  }
}
```

### 5.5 运行单元测试，确保修改正确。

单元测试运行成功，至此合并代码部分完成。