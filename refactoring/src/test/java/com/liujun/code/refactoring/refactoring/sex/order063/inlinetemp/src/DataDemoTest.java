package com.liujun.code.refactoring.refactoring.sex.order063.inlinetemp.src;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * 测试原始代码
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataDemoTest {

  @Test
  public void testDataGet() {
    DataDemo instance = new DataDemo();
    boolean rsp = instance.runData();
    assertEquals(false, rsp);
  }
}
