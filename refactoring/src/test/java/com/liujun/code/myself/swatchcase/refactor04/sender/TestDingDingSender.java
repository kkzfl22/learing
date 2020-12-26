package com.liujun.code.myself.swatchcase.refactor04.sender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.liujun.code.myself.swatchcase.refactor04.bean.FunCodeMsgData;
import com.liujun.code.myself.swatchcase.refactor04.bean.MsgData;
import com.liujun.code.myself.swatchcase.refactor04.sender.DingDingSender;
import com.liujun.code.myself.swatchcase.src.common.MessageData;

/**
 * 测试钉钉的发送
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestDingDingSender {

  @Test
  @DisplayName("发送钉钉测试")
  public void senderDingDing() {
    DingDingSender instance = new DingDingSender();
    FunCodeMsgData funCodeMsgData = new FunCodeMsgData("12312", "ding ding msg test", "funcode");
    instance.sender(funCodeMsgData);
    // 无异常即为成功
    assertTrue(true);
  }

  @Test
  @DisplayName("钉钉的转换")
  public void testParseDingDing() {
    DingDingSender instance = new DingDingSender();
    MessageData dingMsg = new MessageData();
    dingMsg.setNotifyNumber("123464");
    dingMsg.setContent("钉钉的内容");
    MsgData dingParse = instance.parse(dingMsg);
    // 检查转换的结果
    assertEquals(dingMsg.getContent(), dingParse.getContext());
    assertEquals(dingMsg.getNotifyNumber(), dingParse.getToId());
  }
}
