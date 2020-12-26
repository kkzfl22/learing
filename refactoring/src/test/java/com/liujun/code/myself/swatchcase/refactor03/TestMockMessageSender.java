package com.liujun.code.myself.swatchcase.refactor03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.liujun.code.myself.swatchcase.refactor03.bean.MsgData;
import com.liujun.code.myself.swatchcase.refactor03.sender.DingDingSender;
import com.liujun.code.myself.swatchcase.refactor03.sender.EmailSender;
import com.liujun.code.myself.swatchcase.refactor03.sender.SmsSender;
import com.liujun.code.myself.swatchcase.refactor03.sender.WeChatSender;
import com.liujun.code.myself.swatchcase.refactor03.sender.WorkWeChatSender;
import com.liujun.code.myself.swatchcase.refactor03.sendmsg.MessageSender;
import com.liujun.code.myself.swatchcase.src.common.MessageData;
import com.liujun.code.myself.swatchcase.src.constant.MessageTypeEnum;

import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * 消息的发送的单元测试,使用mock方式
 *
 * <p>执行带有执行顺序
 *
 * @author liujun
 * @version 0.0.1
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestMockMessageSender {

  private static final String ERROR_MSG = "invoke";

  @Test
  @Order(1)
  @DisplayName("测试发送短信通知")
  public void testSmsSender() {
    MessageSender instance = new MessageSender();
    MessageData smsData = new MessageData();
    smsData.setNotifyNumber("13412345678");
    smsData.setContent("测试短信息的内容信息");
    instance.sendSms(smsData);
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @Order(20)
  @DisplayName("邮件发送邮件通知")
  public void testEmailSender() {
    MessageSender instance = new MessageSender();
    MessageData emailData = new MessageData();
    emailData.setNotifyNumber("134@163.com");
    emailData.setTitle("测试邮件通知");
    emailData.setContent("测试邮件的内容信息");
    instance.sendMail(emailData);
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @Order(30)
  @DisplayName("微信发送通知")
  public void testWechatSender() {
    MessageSender instance = new MessageSender();
    MessageData weChatata = new MessageData();
    weChatata.setFunCode("dataTest");
    weChatata.setNotifyNumber("131234567");
    weChatata.setContent("微信消息内容");
    instance.sendWxMsg(weChatata);
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @Order(40)
  @DisplayName("钉钉发送通知")
  public void testDingDingSender() {
    MessageSender instance = new MessageSender();
    MessageData dingdingData = new MessageData();
    dingdingData.setFunCode("dataTest");
    dingdingData.setNotifyNumber("131234567");
    dingdingData.setContent("钉钉发送通知");
    instance.sendDingDingMsg(dingdingData);
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @Order(50)
  @DisplayName("微信企业发送通知")
  public void testWorkWechatSender() {
    MessageSender instance = new MessageSender();
    MessageData workWeChatData = new MessageData();
    workWeChatData.setFunCode("dataTest");
    workWeChatData.setNotifyNumber("1231231");
    workWeChatData.setContent("企业钉钉通知");
    instance.sendWorkWeChatMsg(workWeChatData);
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @Order(100)
  @DisplayName("测试发送短信mock")
  public void testSmsSenderMock() {
    new MockUp<SmsSender>(SmsSender.class) {
      @Mock
      void sender(MsgData msg) {
        throw new IllegalArgumentException(ERROR_MSG);
      }
    };
    MessageSender instance = new MessageSender();
    MessageData data = new MessageData();
    data.setFunCode("dataTest");
    data.setNotifyNumber("13412345678");
    data.setTitle("测试短信息通知");
    data.setStatus(0);
    data.setContent("测试短信息的内容信息..");

    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              instance.sendMessage(MessageTypeEnum.SMS.getMsgType(), data);
            });
    assertEquals(ERROR_MSG, exception.getMessage());
  }

  @Test
  @Order(110)
  @DisplayName("邮件发送邮件通知Mock")
  public void testEmailSenderMock() {
    new MockUp<EmailSender>(EmailSender.class) {
      @Mock
      void sender(MsgData msg) {
        throw new IllegalArgumentException(ERROR_MSG);
      }
    };
    MessageSender instance = new MessageSender();
    MessageData data = new MessageData();
    data.setFunCode("dataTest");
    data.setNotifyNumber("13412345678@163.com");
    data.setTitle("测试邮件息通知");
    data.setStatus(0);
    data.setContent("测试邮件的内容信息..");

    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              instance.sendMessage(MessageTypeEnum.EMAIL.getMsgType(), data);
            });
    assertEquals(ERROR_MSG, exception.getMessage());
  }

  @Test
  @Order(120)
  @DisplayName("微信发送通知mock")
  public void testWechatSenderMock() {
    new MockUp<WeChatSender>() {
      @Mock
      void sender(MsgData msg) {
        throw new IllegalArgumentException(ERROR_MSG);
      }
    };
    MessageSender instance = new MessageSender();

    MessageData data = new MessageData();
    data.setFunCode("dataTest");
    data.setNotifyNumber("1234567");
    data.setTitle("测试微信息通知");
    data.setStatus(0);
    data.setContent("测试微信的内容信息..");

    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              instance.sendMessage(MessageTypeEnum.WECHAT.getMsgType(), data);
            });
    assertEquals(ERROR_MSG, exception.getMessage());
  }

  @Test
  @Order(130)
  @DisplayName("钉钉发送通知mock")
  public void testDingDingSenderMock() {
    new MockUp<DingDingSender>() {
      @Mock
      void sender(MsgData msg) {
        throw new IllegalArgumentException(ERROR_MSG);
      }
    };

    MessageSender instance = new MessageSender();
    MessageData data = new MessageData();
    data.setFunCode("dataTest");
    data.setNotifyNumber("1234567");
    data.setTitle("测试钉钉通知");
    data.setStatus(0);
    data.setContent("测试钉钉的内容信息..");
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              instance.sendMessage(MessageTypeEnum.DINGDING.getMsgType(), data);
            });
    assertEquals(ERROR_MSG, exception.getMessage());
  }

  @Mocked private WorkWeChatSender workWeChatSender;

  @Test
  @Order(140)
  @DisplayName("微信企业发送通知mock")
  public void testWorkWechatSenderMock() {
    new MockUp<WorkWeChatSender>() {
      @Mock
      void sender(MsgData msg) {
        throw new IllegalArgumentException(ERROR_MSG);
      }
    };

    MessageSender instance = new MessageSender();
    MessageData data = new MessageData();
    data.setFunCode("dataTest");
    data.setNotifyNumber("1234567");
    data.setTitle("测试企业微信通知");
    data.setStatus(0);
    data.setContent("测试企业微信的内容信息..");
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              instance.sendMessage(MessageTypeEnum.WORKWECHAT.getMsgType(), data);
            });
    assertEquals(ERROR_MSG, exception.getMessage());
  }
}
