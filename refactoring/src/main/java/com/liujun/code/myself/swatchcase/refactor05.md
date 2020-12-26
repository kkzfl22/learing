## 6 重构-策略模式-使用策略进行重构-Map方式

由于这是一个长文，分成了好几章来介绍如何重构。

1. [原始代码及准备](messageSender.md)
2. [职责独立](refactor01.md)
3. [去除static关键字](refactor02.md)
4. [接口添加转换方法](refactor03.md)
5. [方法合并](refactor04.md)
6. [使用策略进行重构-Map方式](refactor05.md)
7. [使用策略进行重构-枚举方式](refactor06.md)



## 6.1 问题分析

>
       经过前两轮的重构之后，已经提取了抽象接口，并将其static去掉。还为消息发送接口添加了转换方法,并对代码进行了方法的合并操作。
     经过这几轮的修改，代码已经看起来整洁、清晰了许多。但是还存在一个问题，那就是每当要添加一个新的渠道的时候，就得改动MessageSender类，
     在switch/case语句中添加一个判断，这样子确实可以实现功能，那有没有可能在不改动这个代码，或者改动很少的代码就能添加呢？
     这里可能会有好多人就不乐意了。可能会说，这里我已经通过switch/case判断了，直接添加一个不是也挺容易的么？为什么还要求在不改动代码
     或者更少改动代码呢？这不是强人所难么！这个确实没有问题，如果是一般的重构，到这里确实可以算做结束了，那为什么还要追求少改代码或者
     不改代码呢？这个其实还是人的问题,还是在原功能上增加新功能为例吧，如果采用直接修改代码的方式，那必然带来原先的有些功能可能会出现
     问题的风险；如果采用是直接在一个新的代码中完成相关功能，然后将新功能 注入到原先的代码中，这样是不是出现问题的风险被大大降低。
     出于这样的一个考量，如果能做到不改或者少改代码，那对于新功能的扩展，带来了更大的福音。
      来打开下上次重构完成的代码：
>
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



>
      来看看有没有可能实现呢！我觉得这个得从switch/case的本质出发，找到了问题的本质，才能有其他的解，那swatch/case本质是什么呢？脑补下,
    想想平时用switch/case是做什么呢？我觉得switch/case从已知的条件中，匹配一个条件，然后执行这个条件成立的逻辑。想想case SMS:，那就是当
    消息类型为sms时，执行sms的发送。其他的也可类比，那既然是匹配条件，也可以用其他的匹配手段啊！比如使用map进行key的匹配，或者集合，
    顺序遍历匹配，是的就是这样子。
    既然如此，来看看代码实现，如何使用集合对此代码过时行改造。我分为两种方案，1，使用map的式试，2 使用数组的方式。当然还有其他可扩展方式，
    那个就与此类似可自行实现。
>

## 6.2 使用map方案来重构。

### 6.2.1 重构步骤
1. 检查单元测试。
2. 对代码进行改造。
3. 运行单元测试，以验证修改的正确性。

### 6.2.2 检查单元测试
由于在之前做发送消息相关验证时，单元测试的相当相关功能已经覆盖，可直接使用，此步骤可以跳过。

### 6.2.3 对代码进行改造。
```java
public class MessageSender {

  /** 按类型存储发送相关实例 */
  private static final Map<MessageTypeEnum, MessageSenderInf> MSG_INSTANCE = new HashMap<>(5);

  static {
    // 1，SMS发送
    MSG_INSTANCE.put(MessageTypeEnum.SMS, new SmsSender());
    // 2. EMAIL发送
    MSG_INSTANCE.put(MessageTypeEnum.EMAIL, new EmailSender());
    // 3. dingding发送
    MSG_INSTANCE.put(MessageTypeEnum.DINGDING, new DingDingSender());
    // 4. wechat发送
    MSG_INSTANCE.put(MessageTypeEnum.WECHAT, new WeChatSender());
    // 5. 工作微信
    MSG_INSTANCE.put(MessageTypeEnum.WORKWECHAT, new WorkWeChatSender());
  }

  /**
   * 消息发送
   *
   * @param msgType 消息类型
   * @param notify 消息信息
   */
  public void sendMessage(String msgType, MessageData notify) {
    MessageTypeEnum typeEnum = MessageTypeEnum.getEnumType(msgType);
    MessageSenderInf msgSender = MSG_INSTANCE.get(typeEnum);
    if (null == msgSender) {
      return;
    }

    // 消息的发送操作
    msgSender.sender(msgSender.parse(notify));
  }
}
```
这里是直接硬编码在代码中的，这种方式在一般场景下对于更少改动代码，还是有较好的帮助了，它至少脱离了在逻辑中去修改修改。每次需要增加一个发送
类型，直接增加一个相应的实现类，然后注入至这个容器中即可。还有一种更好的，那就是与我们开发常使用的spring框架相结合，使用spring的框架的
依赖注入的特性，帮我们完成对实例的注入。这样真的做理想的程度。也才真的做到了对修改关闭，对扩展开放的一个原则。


### 6.2.4 运行单元测试。
当代码运行完成之后使用map重构的方案已经结束了


当这个重构完成后，我想你基本能理解策略模式了。这就是一个简单的策略模式的应用，可能好多人都没有想过吧，这就是策略模式，离我们如此之近？