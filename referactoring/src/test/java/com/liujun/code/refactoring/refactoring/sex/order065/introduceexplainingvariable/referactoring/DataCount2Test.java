package com.liujun.code.refactoring.refactoring.sex.order065.introduceexplainingvariable.referactoring;

import org.junit.Assert;
import org.junit.Test;

/**
 * 测试数据计算
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataCount2Test {

  @Test
  public void testDataCount() {
    DataCount2 instance = new DataCount2();
    double value = instance.price();
    Assert.assertEquals(5100.0, value, 0.0);
  }
}
