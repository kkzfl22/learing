package com.liujun.code.myself.swatchcase.refactor01.sender;

import com.liujun.code.myself.swatchcase.refactor01.bean.EmailMsgData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 测试邮件发送
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestEmailSender {

  @Test
  @DisplayName("邮件发送")
  public void testSenderEmail() {
    EmailSender instance = new EmailSender();
    EmailMsgData msg = new EmailMsgData("123@163.com", "this context", "邮件通知");
    instance.sender(msg);
    // 无异常即为成功
    assertTrue(true);
  }
}
