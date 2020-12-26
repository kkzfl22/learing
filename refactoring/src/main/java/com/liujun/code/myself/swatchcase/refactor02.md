## 3. 重构-策略模式-去除static关键字

由于这是一个长文，分成了好几章来介绍如何重构。

1. [原始代码及准备](messageSender.md)
2. [职责独立](refactor01.md)
3. [去除static关键字](refactor02.md)
4. [接口添加转换方法](refactor03.md)
5. [方法合并](refactor04.md)
6. [使用策略进行重构-Map方式](refactor05.md)
7. [使用策略进行重构-枚举方式](refactor06.md)





现在来思考下NotifySendUtils中直接调用发送代码的做法，是否合理呢？或者说在我们原来的代码中，增加了一层调用，有必要吗？我觉得这个问题得分场景
来看，如果是一个大系统中的一部分，NotifySendUtils被几十甚至上百的文件引用，那么这样的改动是相当合理的，毕竟这样子我们改动相对较小。但如果
像本例中NotifySendUtils仅被很少的客户端使用，或者说只有一个客户端在用，那么这样的改动还合理吗？这显然是不合理的。这仅增加了类。但却是没有
任何的用处，这样的类就应该被拿掉。

既然发现了问题，那就直接改掉吧。

### 3.1 操作步骤
1. 将单元测试从NotifySendUtils中方法移动到MessageSender中。
2. 将NotifySendUtils中方法复制到MessageSender中。
3. 让编译通过.
4. 让单元测试通过。
5. 删除空单元测试文件TestNotifySendUtils。
6. 修改单元测试，为去除static关键字准备
7. 去除方法的static关键字。
8. 删除空的NotifySendUtils文件。

### 3.2 将单元测试从NotifySendUtils中方法移动到MessageSender中。

```java
public class TestMockMessageSender {

  private MessageSender instance = new MessageSender();

  .......

  @Test
  @DisplayName("测试发送短信通知")
  public void testSmsSender() {
    MessageSender.sendSms("datatest", "13412345678", "测试短信息的内容信息");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("邮件发送邮件通知")
  public void testEmailSender() {
    MessageSender.sendMail("dataTest", "134@163.com", "测试邮件通知", "测试邮件的内容信息");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("微信发送通知")
  public void testWechatSender() {
    MessageSender.sendWxMsg("dataTest", "131234567", "微信消息内容");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("钉钉发送通知")
  public void testDingDingSender() {
    MessageSender.sendDingDingMsg("dataTest", "134123456", "钉钉发送通知");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("微信企业发送通知")
  public void testWorkWechatSender() {
    MessageSender.sendWorkWeChatMsg("dataTest", "1231231", "企业钉钉通知");
    // 无异常即为成功
    assertTrue(true);
  }

}
```



### 3.3 将NotifySendUtils中方法复制到MessageSender中。

```java
public class MessageSender {

  public void sendMessage(String msgType, MessageData notify) {
    MessageTypeEnum typeEnum = MessageTypeEnum.getEnumType(msgType);
    switch (typeEnum) {
        // 短信发送
      case SMS:
        NotifySendUtils.sendSms(notify.getFunCode(), notify.getNotifyNumber(), notify.getContent());
        break;
        // email的发送
      case EMAIL:
        NotifySendUtils.sendMail(
            notify.getFunCode(), notify.getNotifyNumber(), notify.getTitle(), notify.getContent());
        break;
        // 微信的发送
      case WECHAT:
        NotifySendUtils.sendWxMsg(
            notify.getFunCode(), notify.getNotifyNumber(), notify.getContent());
        break;
        // 钉钉的消息发送
      case DINGDING:
        NotifySendUtils.sendDingDingMsg(
            notify.getFunCode(), notify.getNotifyNumber(), notify.getContent());
        break;
        // 企业钉钉的消息发送
      case WORKWECHAT:
        NotifySendUtils.sendWorkWeChatMsg(
            notify.getFunCode(), notify.getNotifyNumber(), notify.getContent());
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

### 3.4 让编译通过。

````java
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
````

### 3.5 修改单元测试，让单元测试可以通过

```java
public class TestMockMessageSender {

  @Test
  @DisplayName("测试发送短信mock")
  public void testSmsSenderMock() {
    MessageSender instance = new MessageSender();
    new Expectations(instance) {
      {
        // 将方法给mock掉，无需与真实的操作进行交互
        instance.sendSms(anyString, anyString, anyString);
      }
    };
    MessageData data = new MessageData();
    data.setFunCode("dataTest");
    data.setNotifyNumber("13412345678");
    data.setTitle("测试短信息通知");
    data.setStatus(0);
    data.setContent("测试短信息的内容信息..");
    instance.sendMessage(MessageTypeEnum.SMS.getMsgType(), data);
    new Verifications() {
      {
        instance.sendSms(anyString, anyString, anyString);
        minTimes = 1;
      }
    };
  }

  @Test
  @DisplayName("邮件发送邮件通知Mock")
  public void testEmailSenderMock() {
    MessageSender instance = new MessageSender();
    new Expectations(instance) {
      {
        // 将方法给mock掉，无需与真实的操作进行交互
        MessageSender.sendMail(anyString, anyString, anyString, anyString);
      }
    };
    MessageData data = new MessageData();
    data.setFunCode("dataTest");
    data.setNotifyNumber("13412345678@163.com");
    data.setTitle("测试邮件息通知");
    data.setStatus(0);
    data.setContent("测试邮件的内容信息..");
    instance.sendMessage(MessageTypeEnum.EMAIL.getMsgType(), data);
    // 无异常即为成功
    assertTrue(true);
    new Verifications() {
      {
        // 验证邮件是否被成功调用
        MessageSender.sendMail(anyString, anyString, anyString, anyString);
        minTimes = 1;
      }
    };
  }

  @Test
  @DisplayName("微信发送通知mock")
  public void testWechatSenderMock() {
    MessageSender instance = new MessageSender();
    new Expectations(instance) {
      {
        // 将方法给mock掉，无需与真实的操作进行交互
        MessageSender.sendWxMsg(anyString, anyString, anyString);
      }
    };
    MessageData data = new MessageData();
    data.setFunCode("dataTest");
    data.setNotifyNumber("1234567");
    data.setTitle("测试微信息通知");
    data.setStatus(0);
    data.setContent("测试微信的内容信息..");
    instance.sendMessage(MessageTypeEnum.WECHAT.getMsgType(), data);
    // 无异常即为成功
    assertTrue(true);

    new Verifications() {
      {
        // 验证邮件是否被成功调用
        MessageSender.sendWxMsg(anyString, anyString, anyString);
        minTimes = 1;
      }
    };
  }

  @Test
  @DisplayName("钉钉发送通知mock")
  public void testDingDingSenderMock() {
    MessageSender instance = new MessageSender();
    new Expectations(instance) {
      {
        // 将方法给mock掉，无需与真实的操作进行交互
        MessageSender.sendDingDingMsg(anyString, anyString, anyString);
      }
    };
    MessageData data = new MessageData();
    data.setFunCode("dataTest");
    data.setNotifyNumber("1234567");
    data.setTitle("测试钉钉通知");
    data.setStatus(0);
    data.setContent("测试钉钉的内容信息..");
    instance.sendMessage(MessageTypeEnum.DINGDING.getMsgType(), data);
    // 无异常即为成功
    assertTrue(true);
    new Verifications() {
      {
        // 验证邮件是否被成功调用
        MessageSender.sendDingDingMsg(anyString, anyString, anyString);
        minTimes = 1;
      }
    };
  }

  @Test
  @DisplayName("微信企业发送通知mock")
  public void testWorkWechatSenderMock() {
    MessageSender instance = new MessageSender();
    new Expectations(instance) {
      {
        // 将方法给mock掉，无需与真实的操作进行交互
        MessageSender.sendWorkWeChatMsg(anyString, anyString, anyString);
      }
    };
    MessageData data = new MessageData();
    data.setFunCode("dataTest");
    data.setNotifyNumber("1234567");
    data.setTitle("测试企业微信通知");
    data.setStatus(0);
    data.setContent("测试企业微信的内容信息..");
    instance.sendMessage(MessageTypeEnum.WORKWECHAT.getMsgType(), data);
    // 无异常即为成功
    assertTrue(true);
    new Verifications() {
      {
        // 验证邮件是否被成功调用
        MessageSender.sendWorkWeChatMsg(anyString, anyString, anyString);
        minTimes = 1;
      }
    };
  }

  @Test
  @DisplayName("测试发送短信通知")
  public void testSmsSender() {
    MessageSender.sendSms("datatest", "13412345678", "测试短信息的内容信息");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("邮件发送邮件通知")
  public void testEmailSender() {
    MessageSender.sendMail("dataTest", "134@163.com", "测试邮件通知", "测试邮件的内容信息");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("微信发送通知")
  public void testWechatSender() {
    MessageSender.sendWxMsg("dataTest", "131234567", "微信消息内容");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("钉钉发送通知")
  public void testDingDingSender() {
    MessageSender.sendDingDingMsg("dataTest", "134123456", "钉钉发送通知");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("微信企业发送通知")
  public void testWorkWechatSender() {
    MessageSender.sendWorkWeChatMsg("dataTest", "1231231", "企业钉钉通知");
    // 无异常即为成功
    assertTrue(true);
  }
}

```



### 3.6 删除空单元测试文件TestNotifySendUtils。

TestNotifySendUtils单元测试类中的文件已经被移动到其他单元测试文件中，已经是一个空文件，直接删除即可。



### 3.7 修改单元测试，为去除static关键字准备

```java
public class TestMockMessageSender {
......

  @Test
  @DisplayName("测试发送短信通知")
  public void testSmsSender() {
    MessageSender instance = new MessageSender();
    instance.sendSms("datatest", "13412345678", "测试短信息的内容信息");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("邮件发送邮件通知")
  public void testEmailSender() {
    MessageSender instance = new MessageSender();
    instance.sendMail("dataTest", "134@163.com", "测试邮件通知", "测试邮件的内容信息");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("微信发送通知")
  public void testWechatSender() {
    MessageSender instance = new MessageSender();
    instance.sendWxMsg("dataTest", "131234567", "微信消息内容");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("钉钉发送通知")
  public void testDingDingSender() {
    MessageSender instance = new MessageSender();
    instance.sendDingDingMsg("dataTest", "134123456", "钉钉发送通知");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("微信企业发送通知")
  public void testWorkWechatSender() {
    MessageSender instance = new MessageSender();
    instance.sendWorkWeChatMsg("dataTest", "1231231", "企业钉钉通知");
    // 无异常即为成功
    assertTrue(true);
  }
}

```



### 3.8 去除方法的static关键字。

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



### 3.9 将NotifySendUtils删除

由于NotifySendUtils文件已经为空文件无任何用处，将其删除。 



### 3.10 运行单元测试

运行单元测试检查是否存在错误的修改。


