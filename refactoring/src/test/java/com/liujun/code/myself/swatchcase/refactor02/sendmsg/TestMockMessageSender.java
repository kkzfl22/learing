package com.liujun.code.myself.swatchcase.refactor02.sendmsg;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.liujun.code.myself.swatchcase.src.common.MessageData;
import com.liujun.code.myself.swatchcase.src.constant.MessageTypeEnum;

import mockit.Expectations;
import mockit.Verifications;

/**
 * 消息的发送的单元测试,使用mock方式
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestMockMessageSender {

  @Test
  @DisplayName("测试发送短信mock")
  public void testSmsSenderMock() {
    MessageSender instance = new MessageSender();
    new Expectations(instance) {
      {
        // 将方法给mock掉，无需与真实的操作进行交互
        instance.sendSms(anyString, anyString, anyString);
      }
    };
    MessageData data = new MessageData();
    data.setFunCode("dataTest");
    data.setNotifyNumber("13412345678");
    data.setTitle("测试短信息通知");
    data.setStatus(0);
    data.setContent("测试短信息的内容信息..");
    instance.sendMessage(MessageTypeEnum.SMS.getMsgType(), data);
    new Verifications() {
      {
        instance.sendSms(anyString, anyString, anyString);
        minTimes = 1;
      }
    };
  }

  @Test
  @DisplayName("邮件发送邮件通知Mock")
  public void testEmailSenderMock() {
    MessageSender instance = new MessageSender();
    new Expectations(instance) {
      {
        // 将方法给mock掉，无需与真实的操作进行交互
        instance.sendMail(anyString, anyString, anyString, anyString);
      }
    };
    MessageData data = new MessageData();
    data.setFunCode("dataTest");
    data.setNotifyNumber("13412345678@163.com");
    data.setTitle("测试邮件息通知");
    data.setStatus(0);
    data.setContent("测试邮件的内容信息..");
    instance.sendMessage(MessageTypeEnum.EMAIL.getMsgType(), data);
    // 无异常即为成功
    assertTrue(true);
    new Verifications() {
      {
        // 验证邮件是否被成功调用
        instance.sendMail(anyString, anyString, anyString, anyString);
        minTimes = 1;
      }
    };
  }

  @Test
  @DisplayName("微信发送通知mock")
  public void testWechatSenderMock() {
    MessageSender instance = new MessageSender();
    new Expectations(instance) {
      {
        // 将方法给mock掉，无需与真实的操作进行交互
        instance.sendWxMsg(anyString, anyString, anyString);
      }
    };
    MessageData data = new MessageData();
    data.setFunCode("dataTest");
    data.setNotifyNumber("1234567");
    data.setTitle("测试微信息通知");
    data.setStatus(0);
    data.setContent("测试微信的内容信息..");
    instance.sendMessage(MessageTypeEnum.WECHAT.getMsgType(), data);
    // 无异常即为成功
    assertTrue(true);

    new Verifications() {
      {
        // 验证邮件是否被成功调用
        instance.sendWxMsg(anyString, anyString, anyString);
        minTimes = 1;
      }
    };
  }

  @Test
  @DisplayName("钉钉发送通知mock")
  public void testDingDingSenderMock() {
    MessageSender instance = new MessageSender();
    new Expectations(instance) {
      {
        // 将方法给mock掉，无需与真实的操作进行交互
        instance.sendDingDingMsg(anyString, anyString, anyString);
      }
    };
    MessageData data = new MessageData();
    data.setFunCode("dataTest");
    data.setNotifyNumber("1234567");
    data.setTitle("测试钉钉通知");
    data.setStatus(0);
    data.setContent("测试钉钉的内容信息..");
    instance.sendMessage(MessageTypeEnum.DINGDING.getMsgType(), data);
    // 无异常即为成功
    assertTrue(true);
    new Verifications() {
      {
        // 验证邮件是否被成功调用
        instance.sendDingDingMsg(anyString, anyString, anyString);
        minTimes = 1;
      }
    };
  }

  @Test
  @DisplayName("微信企业发送通知mock")
  public void testWorkWechatSenderMock() {
    MessageSender instance = new MessageSender();
    new Expectations(instance) {
      {
        // 将方法给mock掉，无需与真实的操作进行交互
        instance.sendWorkWeChatMsg(anyString, anyString, anyString);
      }
    };
    MessageData data = new MessageData();
    data.setFunCode("dataTest");
    data.setNotifyNumber("1234567");
    data.setTitle("测试企业微信通知");
    data.setStatus(0);
    data.setContent("测试企业微信的内容信息..");
    instance.sendMessage(MessageTypeEnum.WORKWECHAT.getMsgType(), data);
    // 无异常即为成功
    assertTrue(true);
    new Verifications() {
      {
        // 验证邮件是否被成功调用
        instance.sendWorkWeChatMsg(anyString, anyString, anyString);
        minTimes = 1;
      }
    };
  }

  @Test
  @DisplayName("测试发送短信通知")
  public void testSmsSender() {
    MessageSender instance = new MessageSender();
    instance.sendSms("datatest", "13412345678", "测试短信息的内容信息");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("邮件发送邮件通知")
  public void testEmailSender() {
    MessageSender instance = new MessageSender();
    instance.sendMail("dataTest", "134@163.com", "测试邮件通知", "测试邮件的内容信息");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("微信发送通知")
  public void testWechatSender() {
    MessageSender instance = new MessageSender();
    instance.sendWxMsg("dataTest", "131234567", "微信消息内容");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("钉钉发送通知")
  public void testDingDingSender() {
    MessageSender instance = new MessageSender();
    instance.sendDingDingMsg("dataTest", "134123456", "钉钉发送通知");
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("微信企业发送通知")
  public void testWorkWechatSender() {
    MessageSender instance = new MessageSender();
    instance.sendWorkWeChatMsg("dataTest", "1231231", "企业钉钉通知");
    // 无异常即为成功
    assertTrue(true);
  }
}
