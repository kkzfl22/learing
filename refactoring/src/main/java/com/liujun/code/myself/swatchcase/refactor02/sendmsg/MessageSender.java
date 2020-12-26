package com.liujun.code.myself.swatchcase.refactor02.sendmsg;

import com.liujun.code.myself.swatchcase.refactor02.bean.EmailMsgData;
import com.liujun.code.myself.swatchcase.refactor02.bean.FunCodeMsgData;
import com.liujun.code.myself.swatchcase.refactor02.bean.MsgData;
import com.liujun.code.myself.swatchcase.refactor02.sender.DingDingSender;
import com.liujun.code.myself.swatchcase.refactor02.sender.MessageSenderInf;
import com.liujun.code.myself.swatchcase.refactor02.sender.SmsSender;
import com.liujun.code.myself.swatchcase.refactor02.sender.WeChatSender;
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
