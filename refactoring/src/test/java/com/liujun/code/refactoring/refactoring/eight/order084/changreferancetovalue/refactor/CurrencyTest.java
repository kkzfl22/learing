package com.liujun.code.refactoring.refactoring.eight.order084.changreferancetovalue.refactor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

/**
 * 测试货币对象
 *
 * @author liujun
 * @version 0.0.1
 */
public class CurrencyTest {

  @Test
  public void testInstance() {
    assertEquals(true, new Currency("USD").equals(new Currency("USD")));
  }
}
