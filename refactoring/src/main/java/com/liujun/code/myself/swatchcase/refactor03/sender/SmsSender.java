package com.liujun.code.myself.swatchcase.refactor03.sender;

import com.liujun.code.myself.swatchcase.refactor03.bean.MsgData;
import com.liujun.code.myself.swatchcase.src.common.MessageData;

/**
 * 短信发送
 *
 * @author liujun
 * @version 0.0.1
 */
public class SmsSender implements MessageSenderInf {

  /**
   * 给单个手机号，发送短信
   *
   * @param msg 发送消息的内容
   */
  @Override
  public void sender(MsgData msg) {
    System.out.println("短信的发送开始.....");
    System.out.println(
        "短信发送: " + ",sendToMobileNos:" + msg.getToId() + ",smsContent:" + msg.getContext());
    System.out.println("短信的发送结束.....");
  }

  @Override
  public MsgData parse(MessageData msgData) {
    return new MsgData(msgData.getNotifyNumber(), msgData.getContent());
  }
}
