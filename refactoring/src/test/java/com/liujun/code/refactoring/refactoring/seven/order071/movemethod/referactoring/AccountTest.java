package com.liujun.code.refactoring.refactoring.seven.order071.movemethod.referactoring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * 进行账户类的测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class AccountTest {

  /** 进行账户透支金额的计算 */
  @Test
  public void testOverdraftCharge() {
    AccountType type = new AccountType();
    Account account = new Account(type, 10);
    double outValue = account.overdraftCharge();
    System.out.println(outValue);
    assertEquals(12.55, outValue, 0.1);
  }
}
