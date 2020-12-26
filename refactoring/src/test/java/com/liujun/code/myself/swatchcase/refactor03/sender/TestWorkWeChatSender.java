package com.liujun.code.myself.swatchcase.refactor03.sender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.liujun.code.myself.swatchcase.refactor03.bean.FunCodeMsgData;
import com.liujun.code.myself.swatchcase.src.common.MessageData;

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

  @Test
  @DisplayName("测试工作微信消息的转换")
  public void workWeChatSender() {
    WorkWeChatSender instance = new WorkWeChatSender();

    MessageData workWechatData = new MessageData();
    workWechatData.setFunCode("dataTest");
    workWechatData.setNotifyNumber("123456");
    workWechatData.setContent("工作微信通知...");

    FunCodeMsgData workWeChatParse = (FunCodeMsgData) instance.parse(workWechatData);
    assertEquals(workWechatData.getNotifyNumber(), workWeChatParse.getToId());
    assertEquals(workWechatData.getFunCode(), workWeChatParse.getFunCode());
    assertEquals(workWechatData.getContent(), workWeChatParse.getContext());
  }
}
