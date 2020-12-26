package com.liujun.code.myself.swatchcase.refactor01.sender;

import com.liujun.code.myself.swatchcase.refactor01.bean.FunCodeMsgData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
