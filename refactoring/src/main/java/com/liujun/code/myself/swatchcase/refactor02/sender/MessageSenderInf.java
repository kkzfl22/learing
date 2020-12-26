package com.liujun.code.myself.swatchcase.refactor02.sender;

import com.liujun.code.myself.swatchcase.refactor02.bean.MsgData;

/**
 * 消息发送接口
 *
 * @author liujun
 * @version 0.0.1
 */
public interface MessageSenderInf {

  /**
   * 消息发送
   *
   * @param msg 发送消息的内容
   */
  void sender(MsgData msg);
}
