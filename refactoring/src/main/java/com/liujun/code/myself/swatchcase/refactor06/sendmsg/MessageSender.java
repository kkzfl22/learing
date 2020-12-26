package com.liujun.code.myself.swatchcase.refactor06.sendmsg;

import com.liujun.code.myself.swatchcase.refactor06.bean.MessageData;
import com.liujun.code.myself.swatchcase.refactor06.sender.MessageSenderInf;

/**
 * 执行单元测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class MessageSender {

  /**
   * 进行策略消息的发送
   *
   * @param msgType 消息类型
   * @param notify 消息信息
   */
  public void sendMessage(String msgType, MessageData notify) {
    MessageSenderInf msgInstance = MsgSenderInstanceEnum.getInstanceByType(msgType);
    if (null == msgInstance) {
      return;
    }
    msgInstance.sender(msgInstance.parse(notify));
  }
}
