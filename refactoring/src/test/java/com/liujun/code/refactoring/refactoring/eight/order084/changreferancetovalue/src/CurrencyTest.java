package com.liujun.code.refactoring.refactoring.eight.order084.changreferancetovalue.src;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

/**
 * 货币种类的测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class CurrencyTest {

  @Test
  public void testGetType() {
    System.out.println(Currency.get("USD").equals(Currency.get("USD")));
  }
}
