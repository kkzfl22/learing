package com.liujun.code.myself.swatchcase.refactor01.sender;

import com.liujun.code.myself.swatchcase.refactor01.bean.MsgData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
