package com.liujun.code.refactoring.refactoring.sex.order067.removeassignmentstoParameters.referactoring;

import org.junit.Assert;
import org.junit.Test;

/**
 * 进行数据计算操作
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataCountTest {

  @Test
  public void testDiscount() {
    DataCount instance = new DataCount();
    int value = instance.discount(51, 101, 10001);
    Assert.assertEquals(44, value);
  }
}
