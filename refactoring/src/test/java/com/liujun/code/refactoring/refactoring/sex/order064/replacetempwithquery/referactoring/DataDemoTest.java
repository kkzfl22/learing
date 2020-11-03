package com.liujun.code.refactoring.refactoring.sex.order064.replacetempwithquery.referactoring;

import org.junit.Assert;
import org.junit.Test;

/**
 * 单元测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataDemoTest {

  @Test
  public void testCount() {
    DataDemo instance = new DataDemo();
    double outValue = instance.count();
    System.out.println(outValue);
    Assert.assertEquals(outValue, 1900, 0.95);
    // Assert.assertThat(outValue, Matchers);
  }
}
