package com.liujun.code.myself.swatchcase.refactor02.sender;

import com.liujun.code.myself.swatchcase.refactor02.bean.FunCodeMsgData;
import com.liujun.code.myself.swatchcase.refactor02.bean.MsgData;

/**
 * 钉钉消息发送
 *
 * @author liujun
 * @version 0.0.1
 */
public class DingDingSender implements MessageSenderInf {

  /**
   * 发送钉钉消息
   *
   * @param msg 消息信息
   */
  @Override
  public void sender(MsgData msg) {
    System.out.println("钉钉消息发送开始.....");
    FunCodeMsgData codeMsgData = (FunCodeMsgData) msg;
    System.out.println(
        "钉钉消息:funCode"
            + codeMsgData.getFunCode()
            + ",toUserOpenId:"
            + codeMsgData.getToId()
            + ",tplData:"
            + codeMsgData.getContext());
    System.out.println("钉钉消息发送结束.....");
  }
}
