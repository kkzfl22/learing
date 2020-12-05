package com.liujun.legacy.code.parttwo.order208.addfunction.programingbydifference.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.liujun.legacy.code.parttwo.order208.addfunction.programingbydifference.common.InternetAddress;
import com.liujun.legacy.code.parttwo.order208.addfunction.programingbydifference.common.Message;

/**
 * 测试驱动开发
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestMessageForwarder {

  @Test
  @DisplayName("匿名邮件")
  public void testAnonymous() throws Exception {
    // 匿名邮件地址
    Message expectedMessage = new Message();
    expectedMessage.setFrom(new InternetAddress[] {new InternetAddress("anon-method@127.0.0.1")});

    MessageForwarder forwarder = new AnonymousMessageForwarder();
    forwarder.forwardMessage(makeFakeMessage());
    assertEquals(
        "anon-method@" + forwarder.getDomain(), expectedMessage.getFrom()[0].getFromAddress());
  }

  private Message makeFakeMessage() {
    Message msg = new Message();
    msg.setFrom(new InternetAddress[] {new InternetAddress("127.0.0.1")});
    return msg;
  }
}
