package com.liujun.code.myself.swatchcase.refactor05.sender;

import com.liujun.code.myself.swatchcase.refactor05.bean.FunCodeMsgData;
import com.liujun.code.myself.swatchcase.refactor05.bean.MsgData;
import com.liujun.code.myself.swatchcase.src.common.MessageData;

/**
 * 微信消息发送
 *
 * @author liujun
 * @version 0.0.1
 */
public class WeChatSender implements MessageSenderInf {

  /**
   * 给单个微信openID，发送模板消息（类似银行交易提醒通知）
   *
   * @param msgData 消息信息
   */
  @Override
  public void sender(MsgData msgData) {
    FunCodeMsgData funMsg = (FunCodeMsgData) msgData;
    System.out.println("微信消息发送开始.....");
    System.out.println(
        "微信消息:funCode"
            + funMsg.getFunCode()
            + ",toUserOpenId:"
            + funMsg.getToId()
            + "，context:"
            + funMsg.getContext());
    System.out.println("微信消息发送结束.....");
  }

  @Override
  public MsgData parse(MessageData weChatMsg) {
    return new FunCodeMsgData(
        weChatMsg.getNotifyNumber(), weChatMsg.getContent(), weChatMsg.getFunCode());
  }
}
