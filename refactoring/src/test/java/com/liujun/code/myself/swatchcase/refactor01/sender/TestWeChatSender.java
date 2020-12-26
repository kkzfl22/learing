package com.liujun.code.myself.swatchcase.refactor01.sender;

import com.liujun.code.myself.swatchcase.refactor01.bean.FunCodeMsgData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 发送微信测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestWeChatSender {

  @Test
  @DisplayName("测试微信发送消息")
  public void senderWeChat() {
    WeChatSender instance = new WeChatSender();
    FunCodeMsgData funCodeMsg = new FunCodeMsgData("1231231", "this is test", "datafun");
    instance.sender(funCodeMsg);
    // 无异常即为成功
    assertTrue(true);
  }
}
