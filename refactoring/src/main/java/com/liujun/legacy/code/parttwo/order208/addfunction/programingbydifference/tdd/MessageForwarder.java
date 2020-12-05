package com.liujun.legacy.code.parttwo.order208.addfunction.programingbydifference.tdd;

import com.liujun.legacy.code.parttwo.order208.addfunction.programingbydifference.common.InternetAddress;
import com.liujun.legacy.code.parttwo.order208.addfunction.programingbydifference.common.Message;

/**
 * 用于匿名邮件发送的
 *
 * @author liujun
 * @version 0.0.1
 */
public class MessageForwarder {

  private Message msg;

  public MessageForwarder() {}

  public void processMessage(Message message) {}

  public void forwardMessage(Message message) {
    this.msg = message;
  }

  protected InternetAddress getFromAddress(Message message) {
    return new InternetAddress(message.getFrom()[0].getFromAddress());
  }

  public String getDomain() {
    return this.msg.getFrom()[0].getFromAddress();
  }
}
