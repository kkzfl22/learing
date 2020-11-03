package com.liujun.code.refactoring.refactoring.eight.order084.changreferancetovalue.refactor;

import org.junit.Assert;
import org.junit.Test;

/**
 * 测试货币对象
 *
 * @author liujun
 * @version 0.0.1
 */
public class CurrencyTest {

  @Test
  public void testInstance() {
    Assert.assertEquals(true, new Currency("USD").equals(new Currency("USD")));
  }
}
