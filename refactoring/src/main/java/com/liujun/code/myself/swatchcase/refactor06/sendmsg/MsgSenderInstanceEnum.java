package com.liujun.code.myself.swatchcase.refactor06.sendmsg;

import com.liujun.code.myself.swatchcase.refactor06.sender.DingDingSender;
import com.liujun.code.myself.swatchcase.refactor06.sender.EmailSender;
import com.liujun.code.myself.swatchcase.refactor06.sender.MessageSenderInf;
import com.liujun.code.myself.swatchcase.refactor06.sender.SmsSender;
import com.liujun.code.myself.swatchcase.refactor06.sender.WeChatSender;
import com.liujun.code.myself.swatchcase.refactor06.sender.WorkWeChatSender;
import com.liujun.code.myself.swatchcase.src.constant.MessageTypeEnum;

/**
 * 消息类型与与相绑定的实例
 *
 * @author liujun
 * @version 0.0.1
 */
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
