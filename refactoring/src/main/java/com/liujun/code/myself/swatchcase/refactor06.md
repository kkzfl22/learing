## 7 重构-策略模式-使用策略进行重构-枚举方式

由于这是一个长文，分成了好几章来介绍如何重构。

1. [原始代码及准备](messageSender.md)
2. [职责独立](refactor01.md)
3. [去除static关键字](refactor02.md)
4. [接口添加转换方法](refactor03.md)
5. [方法合并](refactor04.md)
6. [使用策略进行重构-Map方式](refactor05.md)
7. [使用策略进行重构-枚举方式](refactor06.md)


>
    经过上一次的重构我想你基本理解了使用map的策略模式，我们再来看使用策略模式的另一种实现list
>

### 7.1 重构步骤
这个还是规矩
1. 检查单元测试。
2. 使用枚举将类型与实例相绑定。
3. 进行策略的实现。
4. 执行单元测试。

### 7.2 检查单元测试
这个单元测试没有变化，可跳过

### 7.3 使用枚举将类型与实例相绑定。
```java
public enum MsgSenderInstanceEnum {
  /** 短信 */
  SMS(MessageTypeEnum.SMS.getMsgType(), new SmsSender()),

  /** email */
  EMAIL(MessageTypeEnum.EMAIL.getMsgType(), new EmailSender()),

  /** dingding */
  DINGDING(MessageTypeEnum.DINGDING.getMsgType(), new DingDingSender()),

  /** 微信 */
  WECHAT(MessageTypeEnum.WECHAT.getMsgType(), new WeChatSender()),

  /** 工作微信 */
  WORKWECHAT(MessageTypeEnum.WORKWECHAT.getMsgType(), new WorkWeChatSender());

  private String msgType;

  private MessageSenderInf msSender;

  MsgSenderInstanceEnum(String msgType, MessageSenderInf msSender) {
    this.msgType = msgType;
    this.msSender = msSender;
  }

  public String getMsgType() {
    return msgType;
  }

  public MessageSenderInf getMsSender() {
    return msSender;
  }

  /**
   * 按类型获取实例
   *
   * @param type 类型信息
   * @return 实例信息
   */
  public static MessageSenderInf getInstanceByType(String type) {
    for (MsgSenderInstanceEnum senderItem : values()) {
      if (senderItem.getMsgType().equals(type)) {
        return senderItem.getMsSender();
      }
    }
    return null;
  }
}
```


### 7.4 策略的实现。
```java
public class MessageSender {

  /**
   * 进行策略消息的发送
   *
   * @param msgType 消息类型
   * @param notify 消息信息
   */
  public void sendMessage(String msgType, MessageData notify) {
    MessageSenderInf msgInstance = MsgSenderInstanceEnum.getInstanceByType(msgType);
    if (null == msgInstance) {
      return;
    }
    msgInstance.sender(msgInstance.parse(notify));
  }
}
```


### 7.5 单元测试的验证
经过此之后，使用枚举的方式来进行按类型执行的方案已经完成。


