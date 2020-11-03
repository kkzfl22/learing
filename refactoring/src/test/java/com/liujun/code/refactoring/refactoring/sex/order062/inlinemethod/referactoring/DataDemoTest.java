package com.liujun.code.refactoring.refactoring.sex.order062.inlinemethod.referactoring;

import org.junit.Assert;
import org.junit.Test;

/**
 * 测试重构后的内联函数
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataDemoTest {

  @Test
  public void testRating() {
    DataDemo data = new DataDemo(10);
    int value = data.getReting();
    Assert.assertEquals(2, value);
  }
}
