package com.liujun.legacy.code.parttwo.order208.addfunction.programingbydifference.tdd;

import com.liujun.legacy.code.parttwo.order208.addfunction.programingbydifference.common.InternetAddress;
import com.liujun.legacy.code.parttwo.order208.addfunction.programingbydifference.common.Message;

/**
 * @author liujun
 * @version 0.0.1
 */
public class AnonymousMessageForwarder extends MessageForwarder {

  public AnonymousMessageForwarder() {}

  /**
   * 获取匿名邮件
   *
   * @return
   */
  public String getListAddress() {
    return super.getDomain();
  }

  @Override
  protected InternetAddress getFromAddress(Message message) {
    String anonymousAddress = "anon-" + getListAddress();
    return new InternetAddress(anonymousAddress);
  }
}
