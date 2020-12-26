# 1. 重构-策略模式-原始代码及准备

由于这是一个长文，分成了好几章来介绍如何重构。

1. [原始代码及准备](messageSender.md)
2. [职责独立](refactor01.md)
3. [去除static关键字](refactor02.md)
4. [接口添加转换方法](refactor03.md)
5. [方法合并](refactor04.md)
6. [使用策略进行重构-Map方式](refactor05.md)
7. [使用策略进行重构-枚举方式](refactor06.md)




## 1.1 原始代码

>这是来自于我所经历的项目中一个消息发送的程序，调用者告诉发送程序：类型、接收者及消息的内容，发送程序会根据发送的类型调用不同的发送程序，将待发送的消息发送出去。 目前已知的发送渠道包括：短信、email、微信、钉钉、企业微信， 由于短信、email、微信、钉钉、企业微信这些发送渠道的调用方式非本程序所关心的重点，帮将对这些具体的发送程序进行简化，使用system.out替代。    

先还是来看下类uml类图
![图片](\D:\doc\博客\重构\策略模型的使用\重构前的UML-类图.png)


### MessageSender(消息发送)
MessageSender是消息发送类的处理逻辑，按消息的类型调用各种消息的发送方法。
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
        NotifySendUtils.sendWorkWebchatMsg(
            notify.getFunCode(), notify.getNotifyNumber(), notify.getContent());
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

### NotifySendUtils(各种消息的发送操作)
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
    System.out.println("邮件的发送开始.....");
    System.out.println(
        "邮件发送: " + ",sendToMail:" + sendToMail + ",subject:" + subject + ",content:" + content);
    System.out.println("短信的发送结束.....");
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
    System.out.println("短信的发送开始.....");
    System.out.println(
        "短信发送: " + ",sendToMobileNos:" + sendToMobileNos + ",smsContent:" + smsContent);
    System.out.println("短信的发送结束.....");
  }

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

  /**
   * 企业微信消息的发送
   *
   * @param funCode 功能类型编码
   * @param toUserOpenId 接收消息的id
   * @param context 发送的内容
   */
  public static void sendWorkWebchatMsg(String funCode, String toUserOpenId, String context) {
    System.out.println("企业微信消息发送开始.....");
    System.out.println(
        "企业微信消息:funCode" + funCode + ",toUserOpenId:" + toUserOpenId + ",tplData:" + context);
    System.out.println("企业微信消息发送结束.....");
  }
}

```

### MessageData(发送消息的内容信息)
MessageData表示一条要发送的消息的内容
```java
public class MessageData {

  /** 功能类型编码 */
  private String funCode;

  /** 通知对象（邮箱，手机号，钉钉号） */
  private String notifyNumber;

  /** 主题 */
  private String title;

  /** 短信内容 */
  private String content;

  /** 发送状态（1：未发送 | 2：发送完成 | 3：发送失败） */
  private Integer status;


  public String getNotifyNumber() {
    return notifyNumber;
  }

  public void setNotifyNumber(String notifyNumber) {
    this.notifyNumber = notifyNumber;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getFunCode() {
    return funCode;
  }

  public void setFunCode(String funCode) {
    this.funCode = funCode;
  }


  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("MessageData{");
    sb.append("funCode='").append(funCode).append('\'');
    sb.append(", notifyNumber='").append(notifyNumber).append('\'');
    sb.append(", title='").append(title).append('\'');
    sb.append(", content='").append(content).append('\'');
    sb.append(", status=").append(status);
    sb.append('}');
    return sb.toString();
  }
}
```

### MessageTypeEnum 消息类型信息
MessageTypeEnum表示消息的类型信息
```java
public enum MessageTypeEnum {

  /** 短信方式 */
  SMS("sms"),

  /** 邮件发送 */
  EMAIL("email"),

  /** 微信 */
  WECHAT("wechat"),

  /** 钉钉 */
  DINGDING("dingding"),

  /** 企业微信 */
  WORKWECHAT("workwechat"),

  /** 其他方式 */
  OTHER("other");

  /** 消息类型 */
  private String msgType;

  MessageTypeEnum(String msgType) {
    this.msgType = msgType;
  }

  /**
   * 获取消息的枚举类型
   *
   * @param type 字符串表示
   * @return 枚举的类型
   */
  public static MessageTypeEnum getEnumType(String type) {
    for (MessageTypeEnum typeEnum : values()) {
      if (typeEnum.getMsgType().equals(type)) {
        return typeEnum;
      }
    }

    return MessageTypeEnum.OTHER;
  }

  public String getMsgType() {
    return msgType;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("MessageTypeEnum{");
    sb.append("msgType='").append(msgType).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
```

调用序列图
![图片](\D:\doc\博客\重构\策略模型的使用\重构前的UML-调用序列图.png)


### 对此程序的评价
> 
   我相信这样的代码在项目的开发中屡见不鲜，可能会说我这代码写得不好，不够面向 对象，判断逻辑都写死在了判断中，职责分离做的不好。  
   单就这样一个小程序而言这是可以接受的，但如果这是在一个复杂的应用系统中的一部分呢？那还能接受吗？单就这个程序而言，本身是可以运行的。是的没有错，其实只要是个学编程的，能写机器可以运行的代码这不是一件很难的事情，机器是死的，它不关注设计的够不够好，只要能够运行即可，但现实情况真的是这样吗？只需要能够机器运行即可吗？复杂的系统通常需要一个团队来维护，而这个时候，还只是机器能运行就可以了吗？当然不是，这个时候的
   重点就从机器转移到人，需要人来维护，需要人来加入新的功能。有的时候可能这个人写的代码需要另外的人来继续扩展功能，人是关注代码的可读性的，是否能够容易修改的。
>




##  1.2 准备工作-单元测试

**这是一个必要的工作。在没有单元测试的情况下，请不要重构。**

**如果没有单元测试，必须先写单元测试。并且必须将相关的功能都测试到，否则无法保证重构后的代码质量。**

>这里存在两层的单元测试，底层发送消息的测试，上层发送逻辑的测试。
    第一层可以称物理层的测试。主要做用就是测试与真实的物理消息发送的交互。

首先进行消息通道相关的发送测试
```java
public class TestNotifySendUtils {

  @Test
  @DisplayName("测试发送短信通知")
  public void testSmsSender() {
    NotifySendUtils.sendSms("datatest", "13412345678", "测试短信息的内容信息");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("邮件发送邮件通知")
  public void testEmailSender() {
    NotifySendUtils.sendMail("dataTest", "134@163.com", "测试邮件通知", "测试邮件的内容信息");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("微信发送通知")
  public void testWechatSender() {
    NotifySendUtils.sendWxMsg("dataTest", "131234567", "微信消息内容");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("钉钉发送通知")
  public void testDingDingSender() {
    NotifySendUtils.sendDingDingMsg("dataTest", "134123456", "钉钉发送通知");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("微信企业发送通知")
  public void testWorkWechatSender() {
    NotifySendUtils.sendWorkWebchatMsg("dataTest", "1231231", "企业钉钉通知");
    // 无异常即为成功
    assertTrue(true);
  }
}

```

>
    第二层发送逻辑的测试
    在这里已经屏蔽了物理层，只做发送的逻辑测试，
    此处使用jmockit
>

```java
public class TestMockMessageSender {

  private MessageSender instance = new MessageSender();

  @Test
  @DisplayName("测试发送短信mock")
  public void testSmsSenderMock() {

    new Expectations(NotifySendUtils.class) {
      {
        // 将方法给mock掉，无需与真实的操作进行交互
        NotifySendUtils.sendSms(anyString, anyString, anyString);
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
        NotifySendUtils.sendSms(anyString, anyString, anyString);
        minTimes = 1;
      }
    };
  }

  @Test
  @DisplayName("邮件发送邮件通知Mock")
  public void testEmailSender() {

    new Expectations(NotifySendUtils.class) {
      {
        // 将方法给mock掉，无需与真实的操作进行交互
        NotifySendUtils.sendMail(anyString, anyString, anyString, anyString);
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
        NotifySendUtils.sendMail(anyString, anyString, anyString, anyString);
        minTimes = 1;
      }
    };
  }

  @Test
  @DisplayName("微信发送通知mock")
  public void testWechatSender() {

    new Expectations(NotifySendUtils.class) {
      {
        // 将方法给mock掉，无需与真实的操作进行交互
        NotifySendUtils.sendWxMsg(anyString, anyString, anyString);
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
        NotifySendUtils.sendWxMsg(anyString, anyString, anyString);
        minTimes = 1;
      }
    };
  }

  @Test
  @DisplayName("钉钉发送通知mock")
  public void testDingDingSender() {

    new Expectations(NotifySendUtils.class) {
      {
        // 将方法给mock掉，无需与真实的操作进行交互
        NotifySendUtils.sendDingDingMsg(anyString, anyString, anyString);
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
        NotifySendUtils.sendDingDingMsg(anyString, anyString, anyString);
        minTimes = 1;
      }
    };
  }

  @Test
  @DisplayName("微信企业发送通知mock")
  public void testWorkWechatSender() {

    new Expectations(NotifySendUtils.class) {
      {
        // 将方法给mock掉，无需与真实的操作进行交互
        NotifySendUtils.sendWorkWebchatMsg(anyString, anyString, anyString);
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
        NotifySendUtils.sendWorkWebchatMsg(anyString, anyString, anyString);
        minTimes = 1;
      }
    };
  }
}
```
