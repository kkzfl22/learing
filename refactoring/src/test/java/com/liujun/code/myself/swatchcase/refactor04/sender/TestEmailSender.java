package com.liujun.code.myself.swatchcase.refactor04.sender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.liujun.code.myself.swatchcase.refactor04.bean.EmailMsgData;
import com.liujun.code.myself.swatchcase.refactor04.bean.MsgData;
import com.liujun.code.myself.swatchcase.src.common.MessageData;

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

  @Test
  @DisplayName("测试email的转换")
  public void testSmsParse() {
    EmailSender instance = new EmailSender();
    MessageData smsData = new MessageData();
    smsData.setNotifyNumber("134@163.com");
    smsData.setContent("邮件通知...");
    MsgData smsParse = instance.parse(smsData);
    // 检查发送结果
    assertEquals(smsData.getContent(), smsParse.getContext());
    assertEquals(smsData.getNotifyNumber(), smsParse.getToId());
  }
}
