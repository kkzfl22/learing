package com.liujun.code.myself.swatchcase.src.sendmsg;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.liujun.code.myself.swatchcase.refactor01.sendmsg.MessageSender;
import com.liujun.code.myself.swatchcase.refactor01.sendmsg.NotifySendUtils;
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

  private MessageSender instance = new MessageSender();

  @Test
  @DisplayName("测试发送短信mock")
  public void testSmsSenderMock() {

    new Expectations(NotifySendUtils.class) {
      {
        // 将方法给mock掉，无需与真实的操作进行交互
        NotifySendUtils.sendSms(anyString, anyString, anyString);
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
        NotifySendUtils.sendSms(anyString, anyString, anyString);
        minTimes = 1;
      }
    };
  }

  @Test
  @DisplayName("邮件发送邮件通知Mock")
  public void testEmailSender() {

    new Expectations(NotifySendUtils.class) {
      {
        // 将方法给mock掉，无需与真实的操作进行交互
        NotifySendUtils.sendMail(anyString, anyString, anyString, anyString);
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
        NotifySendUtils.sendMail(anyString, anyString, anyString, anyString);
        minTimes = 1;
      }
    };
  }

  @Test
  @DisplayName("微信发送通知mock")
  public void testWechatSender() {

    new Expectations(NotifySendUtils.class) {
      {
        // 将方法给mock掉，无需与真实的操作进行交互
        NotifySendUtils.sendWxMsg(anyString, anyString, anyString);
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
        NotifySendUtils.sendWxMsg(anyString, anyString, anyString);
        minTimes = 1;
      }
    };
  }

  @Test
  @DisplayName("钉钉发送通知mock")
  public void testDingDingSender() {

    new Expectations(NotifySendUtils.class) {
      {
        // 将方法给mock掉，无需与真实的操作进行交互
        NotifySendUtils.sendDingDingMsg(anyString, anyString, anyString);
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
        NotifySendUtils.sendDingDingMsg(anyString, anyString, anyString);
        minTimes = 1;
      }
    };
  }

  @Test
  @DisplayName("微信企业发送通知mock")
  public void testWorkWechatSender() {

    new Expectations(NotifySendUtils.class) {
      {
        // 将方法给mock掉，无需与真实的操作进行交互
        NotifySendUtils.sendWorkWeChatMsg(anyString, anyString, anyString);
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
        NotifySendUtils.sendWorkWeChatMsg(anyString, anyString, anyString);
        minTimes = 1;
      }
    };
  }
}
