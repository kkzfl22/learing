package com.liujun.code.myself.swatchcase.refactor03.sendmsg;

import com.liujun.code.myself.swatchcase.refactor03.bean.EmailMsgData;
import com.liujun.code.myself.swatchcase.refactor03.bean.FunCodeMsgData;
import com.liujun.code.myself.swatchcase.refactor03.bean.MsgData;
import com.liujun.code.myself.swatchcase.refactor03.sender.DingDingSender;
import com.liujun.code.myself.swatchcase.refactor03.sender.EmailSender;
import com.liujun.code.myself.swatchcase.refactor03.sender.MessageSenderInf;
import com.liujun.code.myself.swatchcase.refactor03.sender.SmsSender;
import com.liujun.code.myself.swatchcase.refactor03.sender.WeChatSender;
import com.liujun.code.myself.swatchcase.refactor03.sender.WorkWeChatSender;
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
