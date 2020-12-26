package com.liujun.code.myself.swatchcase.refactor03.sender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.liujun.code.myself.swatchcase.refactor03.bean.FunCodeMsgData;
import com.liujun.code.myself.swatchcase.src.common.MessageData;

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

  @Test
  @DisplayName("测试微信的转换")
  public void weChatParse()
  {
    WeChatSender instance = new WeChatSender();
    MessageData wechatData = new MessageData();
    wechatData.setFunCode("dataTest");
    wechatData.setNotifyNumber("123456");
    wechatData.setContent("微信通知...");
    FunCodeMsgData weChatParse = (FunCodeMsgData) instance.parse(wechatData);
    assertEquals(wechatData.getFunCode(),weChatParse.getFunCode());
    assertEquals(wechatData.getNotifyNumber(),weChatParse.getToId());
    assertEquals(wechatData.getContent(),weChatParse.getContext());
  }
}
