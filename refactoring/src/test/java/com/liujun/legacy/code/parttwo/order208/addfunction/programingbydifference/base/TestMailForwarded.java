package com.liujun.legacy.code.parttwo.order208.addfunction.programingbydifference.base;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.liujun.legacy.code.parttwo.order208.addfunction.programingbydifference.common.InternetAddress;
import com.liujun.legacy.code.parttwo.order208.addfunction.programingbydifference.common.Message;
import com.liujun.legacy.code.parttwo.order208.addfunction.programingbydifference.common.MessagingException;

/**
 * 测试转发
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestMailForwarded {

  @Test
  @DisplayName("用于进行最基本的测试")
  public void testFord() throws MessagingException {
    String address = "192.168.1.1";
    Message message = new Message();
    message.setFrom(new InternetAddress[] {new InternetAddress(address)});

    MailForwarded instance = new MailForwarded();
    instance.forwardMessage(message);
  }
}
