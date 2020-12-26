package com.liujun.code.myself.swatchcase.refactor04.sender;

import com.liujun.code.myself.swatchcase.refactor04.bean.MsgData;
import com.liujun.code.myself.swatchcase.src.common.MessageData;

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

  /**
   * 消息转换方法
   *
   * @param msgData 发送的消息类型
   * @return 当前转换的类型
   */
  MsgData parse(MessageData msgData);
}
