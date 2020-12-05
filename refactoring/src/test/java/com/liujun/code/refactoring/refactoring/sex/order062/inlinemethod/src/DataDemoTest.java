package com.liujun.code.refactoring.refactoring.sex.order062.inlinemethod.src;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * 内联函数的单元测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataDemoTest {

  @Test
  public void testGetRating() {
    DataDemo data = new DataDemo(10);
    int value = data.getReting();
    assertEquals(2, value);
  }
}
