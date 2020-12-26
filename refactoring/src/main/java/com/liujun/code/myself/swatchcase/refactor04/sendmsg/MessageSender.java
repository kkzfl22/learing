package com.liujun.code.myself.swatchcase.refactor04.sendmsg;

import com.liujun.code.myself.swatchcase.refactor04.sender.DingDingSender;
import com.liujun.code.myself.swatchcase.refactor04.sender.EmailSender;
import com.liujun.code.myself.swatchcase.refactor04.sender.MessageSenderInf;
import com.liujun.code.myself.swatchcase.refactor04.sender.SmsSender;
import com.liujun.code.myself.swatchcase.refactor04.sender.WeChatSender;
import com.liujun.code.myself.swatchcase.refactor04.sender.WorkWeChatSender;
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
        MessageSenderInf smsSender = new SmsSender();
        smsSender.sender(smsSender.parse(notify));
        break;
        // email的发送
      case EMAIL:
        MessageSenderInf emailSender = new EmailSender();
        emailSender.sender(emailSender.parse(notify));
        break;
        // 微信的发送
      case WECHAT:
        MessageSenderInf weChatSender = new WeChatSender();
        weChatSender.sender(weChatSender.parse(notify));
        break;
        // 钉钉的消息发送
      case DINGDING:
        MessageSenderInf dingDingSender = new DingDingSender();
        dingDingSender.sender(dingDingSender.parse(notify));
        break;
        // 企业钉钉的消息发送
      case WORKWECHAT:
        MessageSenderInf workWeChatSender = new WorkWeChatSender();
        workWeChatSender.sender(workWeChatSender.parse(notify));
        break;
        // 其他不做处理
      case OTHER:
        break;
      default:
        break;
    }
  }
}
