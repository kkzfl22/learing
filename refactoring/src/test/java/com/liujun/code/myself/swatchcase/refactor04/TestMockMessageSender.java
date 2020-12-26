package com.liujun.code.myself.swatchcase.refactor04;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.liujun.code.myself.swatchcase.refactor04.bean.MsgData;
import com.liujun.code.myself.swatchcase.refactor04.sender.DingDingSender;
import com.liujun.code.myself.swatchcase.refactor04.sender.EmailSender;
import com.liujun.code.myself.swatchcase.refactor04.sender.SmsSender;
import com.liujun.code.myself.swatchcase.refactor04.sender.WeChatSender;
import com.liujun.code.myself.swatchcase.refactor04.sender.WorkWeChatSender;
import com.liujun.code.myself.swatchcase.refactor04.sendmsg.MessageSender;
import com.liujun.code.myself.swatchcase.src.common.MessageData;
import com.liujun.code.myself.swatchcase.src.constant.MessageTypeEnum;

import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;

/**
 * 消息的发送的单元测试,使用mock方式
 *
 * <p>执行带有执行顺序
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestMockMessageSender {

  private static final String ERROR_MSG = "invoke";

  @Test
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
