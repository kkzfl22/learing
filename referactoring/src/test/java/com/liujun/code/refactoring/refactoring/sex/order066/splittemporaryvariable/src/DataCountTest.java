package com.liujun.code.refactoring.refactoring.sex.order066.splittemporaryvariable.src;

import com.liujun.code.refactoring.refactoring.sex.order066.splittemporaryvariable.src.DataCount;
import org.junit.Assert;
import org.junit.Test;

/**
 * 测试临时变量分析的原代码
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataCountTest {

  @Test
  public void testGetDistanceTravelled() {
    DataCount instance = new DataCount();
    double value = instance.getDistanceTravelled(20);
    Assert.assertEquals(200020.0, value, 0.0);
  }
}
