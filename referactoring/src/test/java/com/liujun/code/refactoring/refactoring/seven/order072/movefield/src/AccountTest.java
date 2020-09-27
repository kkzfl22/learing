package com.liujun.code.refactoring.refactoring.seven.order072.movefield.src;

import org.junit.Assert;
import org.junit.Test;

/**
 * 测试利率的计算
 *
 * @author liujun
 * @version 0.0.1
 */
public class AccountTest {

  @Test
  public void testInterestForAmountDays() {
    AccountType type = new AccountType();

    Account instance = new Account(type, 0.1);
    double value = instance.interestForAmountDays(10000, 100);
    System.out.println(value);
    Assert.assertEquals(273.972602739726, value, 0.1);
  }
}
