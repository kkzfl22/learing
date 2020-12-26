package com.liujun.code.myself.swatchcase.refactor05.sendmsg;

import java.util.HashMap;
import java.util.Map;

import com.liujun.code.myself.swatchcase.refactor05.sender.DingDingSender;
import com.liujun.code.myself.swatchcase.refactor05.sender.EmailSender;
import com.liujun.code.myself.swatchcase.refactor05.sender.MessageSenderInf;
import com.liujun.code.myself.swatchcase.refactor05.sender.SmsSender;
import com.liujun.code.myself.swatchcase.refactor05.sender.WeChatSender;
import com.liujun.code.myself.swatchcase.refactor05.sender.WorkWeChatSender;
import com.liujun.code.myself.swatchcase.src.common.MessageData;
import com.liujun.code.myself.swatchcase.src.constant.MessageTypeEnum;

/**
 * 执行单元测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class MessageSender {

  /** 按类型存储发送相关实例 */
  private static final Map<MessageTypeEnum, MessageSenderInf> MSG_INSTANCE = new HashMap<>(5);

  static {
    // 1，SMS发送
    MSG_INSTANCE.put(MessageTypeEnum.SMS, new SmsSender());
    // 2. EMAIL发送
    MSG_INSTANCE.put(MessageTypeEnum.EMAIL, new EmailSender());
    // 3. dingding发送
    MSG_INSTANCE.put(MessageTypeEnum.DINGDING, new DingDingSender());
    // 4. wechat发送
    MSG_INSTANCE.put(MessageTypeEnum.WECHAT, new WeChatSender());
    // 5. 工作微信
    MSG_INSTANCE.put(MessageTypeEnum.WORKWECHAT, new WorkWeChatSender());
  }

  /**
   * 消息发送
   *
   * @param msgType 消息类型
   * @param notify 消息信息
   */
  public void sendMessage(String msgType, MessageData notify) {
    MessageTypeEnum typeEnum = MessageTypeEnum.getEnumType(msgType);
    MessageSenderInf msgSender = MSG_INSTANCE.get(typeEnum);
    if (null == msgSender) {
      return;
    }

    // 消息的发送操作
    msgSender.sender(msgSender.parse(notify));
  }
}
