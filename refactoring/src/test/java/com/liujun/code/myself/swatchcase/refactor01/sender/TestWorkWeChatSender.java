package com.liujun.code.myself.swatchcase.refactor01.sender;

import com.liujun.code.myself.swatchcase.refactor01.bean.FunCodeMsgData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 测试企业微信发送
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestWorkWeChatSender {

  @Test
  @DisplayName("测试企业微信")
  public void senderWorkWeChat() {
    WorkWeChatSender instance = new WorkWeChatSender();
    FunCodeMsgData codeMsgData = new FunCodeMsgData("12312", "this is test", "datafun");
    instance.sender(codeMsgData);
    // 无异常即为成功
    assertTrue(true);
  }
}
