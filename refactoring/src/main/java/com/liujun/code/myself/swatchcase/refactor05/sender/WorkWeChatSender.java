package com.liujun.code.myself.swatchcase.refactor05.sender;

import com.liujun.code.myself.swatchcase.refactor05.bean.FunCodeMsgData;
import com.liujun.code.myself.swatchcase.refactor05.bean.MsgData;
import com.liujun.code.myself.swatchcase.src.common.MessageData;

/**
 * 企业微信发送
 *
 * @author liujun
 * @version 0.0.1
 */
public class WorkWeChatSender implements MessageSenderInf {

  /**
   * 企业微信消息的发送
   *
   * @param msgData 消息内容
   */
  @Override
  public void sender(MsgData msgData) {
    System.out.println("企业微信消息发送开始.....");
    FunCodeMsgData funCodeMsg = (FunCodeMsgData) msgData;
    System.out.println(
        "企业微信消息:funCode"
            + funCodeMsg.getFunCode()
            + ",toUserOpenId:"
            + funCodeMsg.getToId()
            + ",context:"
            + funCodeMsg.getContext());
    System.out.println("企业微信消息发送结束.....");
  }

  @Override
  public MsgData parse(MessageData workWeChatMsg) {
    return new FunCodeMsgData(
        workWeChatMsg.getNotifyNumber(), workWeChatMsg.getContent(), workWeChatMsg.getFunCode());
  }
}
