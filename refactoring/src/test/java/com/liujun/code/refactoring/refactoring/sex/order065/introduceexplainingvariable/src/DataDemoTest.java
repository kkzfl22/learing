package com.liujun.code.refactoring.refactoring.sex.order065.introduceexplainingvariable.src;

import org.junit.Assert;
import org.junit.Test;

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

    Assert.assertEquals(1, value);
  }
}
