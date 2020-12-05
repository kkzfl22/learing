package com.liujun.legacy.code.parttwo.order208.addfunction.programingbydifference.base;

import com.liujun.legacy.code.parttwo.order208.addfunction.programingbydifference.common.InternetAddress;
import com.liujun.legacy.code.parttwo.order208.addfunction.programingbydifference.common.Message;
import com.liujun.legacy.code.parttwo.order208.addfunction.programingbydifference.common.MessagingException;
import com.liujun.legacy.code.parttwo.order208.addfunction.programingbydifference.common.MimeMessage;

/**
 * 邮件转发
 *
 * @author liujun
 * @version 0.0.1
 */
public class MailForwarded {

  /**
   * 提取发件人的地址
   *
   * @param message
   * @return
   * @throws MessagingException
   */
  private InternetAddress getFromAddress(Message message) throws MessagingException {
    InternetAddress[] from = message.getFrom();
    if (from != null && from.length > 0) {
      return new InternetAddress(from[0].getFromAddress());
    }
    return new InternetAddress(getDefaultFrom());
  }

  private String getDefaultFrom() {
    return "127.0.0.1";
  }

  public void forwardMessage(Message message) throws MessagingException {
    MimeMessage forward = new MimeMessage();
    forward.setFrom(getFromAddress(message));
  }
}
