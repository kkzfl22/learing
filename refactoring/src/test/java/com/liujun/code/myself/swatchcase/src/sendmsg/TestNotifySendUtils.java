package com.liujun.code.myself.swatchcase.src.sendmsg;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.liujun.code.myself.swatchcase.refactor01.sendmsg.NotifySendUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 消息的发送的单元测试,真实的发送
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestNotifySendUtils {

  @Test
  @DisplayName("测试发送短信通知")
  public void testSmsSender() {
    NotifySendUtils.sendSms("datatest", "13412345678", "测试短信息的内容信息");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("邮件发送邮件通知")
  public void testEmailSender() {
    NotifySendUtils.sendMail("dataTest", "134@163.com", "测试邮件通知", "测试邮件的内容信息");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("微信发送通知")
  public void testWechatSender() {
    NotifySendUtils.sendWxMsg("dataTest", "131234567", "微信消息内容");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("钉钉发送通知")
  public void testDingDingSender() {
    NotifySendUtils.sendDingDingMsg("dataTest", "134123456", "钉钉发送通知");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("微信企业发送通知")
  public void testWorkWechatSender() {
    NotifySendUtils.sendWorkWeChatMsg("dataTest", "1231231", "企业钉钉通知");
    // 无异常即为成功
    assertTrue(true);
  }
}
