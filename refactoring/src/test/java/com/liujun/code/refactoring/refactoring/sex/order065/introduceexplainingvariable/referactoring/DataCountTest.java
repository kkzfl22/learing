package com.liujun.code.refactoring.refactoring.sex.order065.introduceexplainingvariable.referactoring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * 测试数据计算
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataCountTest {

  @Test
  public void testDataCount() {
    DataCount instance = new DataCount();
    double value = instance.price();
    assertEquals(5100.0, value, 0.0);
  }
}
