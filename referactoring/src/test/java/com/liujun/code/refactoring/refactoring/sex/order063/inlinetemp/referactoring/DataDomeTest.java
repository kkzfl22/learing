package com.liujun.code.refactoring.refactoring.sex.order063.inlinetemp.referactoring;

import org.junit.Assert;
import org.junit.Test;

/**
 * 测试内联函数
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataDomeTest {

  @Test
  public void testDataGet() {
    DataDemo instance = new DataDemo();
    boolean rsp = instance.runData();
    Assert.assertEquals(false, rsp);
  }
}
