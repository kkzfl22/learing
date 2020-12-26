package com.liujun.code.myself.swatchcase.refactor03.sender;

import com.liujun.code.myself.swatchcase.refactor03.bean.EmailMsgData;
import com.liujun.code.myself.swatchcase.refactor03.bean.MsgData;
import com.liujun.code.myself.swatchcase.src.common.MessageData;

/**
 * 邮件发送
 *
 * @author liujun
 * @version 0.0.1
 */
public class EmailSender implements MessageSenderInf {

  /**
   * 给一个邮箱，发送邮件
   *
   * @param msg 发送的消息
   */
  @Override
  public void sender(MsgData msg) {
    System.out.println("邮件的发送开始.....");
    EmailMsgData emailMsg = (EmailMsgData) msg;
    System.out.println(
        "邮件发送: "
            + ",sendToMail:"
            + emailMsg.getToId()
            + ",subject: "
            + emailMsg.getSubject()
            + " content:"
            + emailMsg.getContext());
    System.out.println("短信的发送结束.....");
  }

  @Override
  public MsgData parse(MessageData emailMsg) {
    return new EmailMsgData(emailMsg.getNotifyNumber(), emailMsg.getTitle(), emailMsg.getContent());
  }
}
