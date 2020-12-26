package com.liujun.code.myself.swatchcase.refactor03.sender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.liujun.code.myself.swatchcase.refactor03.bean.MsgData;
import com.liujun.code.myself.swatchcase.src.common.MessageData;

/**
 * 测试短信发送
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestSmsSender {
  @Test
  @DisplayName("测试短信发送")
  public void testSmsSender() {
    SmsSender instance = new SmsSender();
    MsgData msgData = new MsgData("1341345678", "this is test");
    instance.sender(msgData);
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("测试短信的转换")
  public void testSmsParse() {
    SmsSender instance = new SmsSender();
    MessageData smsData = new MessageData();
    smsData.setNotifyNumber("13412345678");
    smsData.setContent("短信通知...");
    MsgData smsParse = instance.parse(smsData);
    // 检查发送结果
    assertEquals(smsData.getContent(), smsParse.getContext());
    assertEquals(smsData.getNotifyNumber(), smsParse.getToId());
  }
}
