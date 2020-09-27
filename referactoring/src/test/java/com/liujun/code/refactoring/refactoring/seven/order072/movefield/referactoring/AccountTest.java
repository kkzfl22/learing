package com.liujun.code.refactoring.refactoring.seven.order072.movefield.referactoring;

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
    type.setInterestRate(0.1);

    Account instance = new Account(type);
    double value = instance.interestForAmountDays(10000, 100);
    System.out.println(value);
    Assert.assertEquals(273.972602739726, value, 0.1);
  }
}
