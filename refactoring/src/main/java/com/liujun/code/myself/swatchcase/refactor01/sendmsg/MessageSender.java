package com.liujun.code.myself.swatchcase.refactor01.sendmsg;

import com.liujun.code.myself.swatchcase.src.common.MessageData;
import com.liujun.code.myself.swatchcase.src.constant.MessageTypeEnum;

/**
 * 执行单元测试
 *
 * @author liujun
 * @version 0.0.1
 */
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



}
