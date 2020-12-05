package com.liujun.code.refactoring.refactoring.sex.order065.introduceexplainingvariable.src;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * 数据测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataDemoTest {

  @Test
  public void testCount() {
    DataDemo instance = new DataDemo();
    int value = instance.dataCount();

    assertEquals(1, value);
  }
}
