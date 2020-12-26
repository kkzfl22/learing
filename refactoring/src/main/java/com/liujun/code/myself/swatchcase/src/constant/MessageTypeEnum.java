package com.liujun.code.myself.swatchcase.src.constant;

/**
 * 消息发送的枚举
 *
 * @author liujun
 * @version 0.0.1
 */
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
