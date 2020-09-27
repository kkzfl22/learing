package com.liujun.code.refactoring.refactoring.seven.order072.movefield.referactoring2;

/**
 * 使用账户类来原始代码
 *
 * @author liujun
 * @version 0.0.1
 */
public class Account {

  private final AccountType type;

  public Account(AccountType type) {
    this.type = type;
  }

  /**
   * 利率计算
   *
   * @param amount
   * @param days
   * @return
   */
  double interestForAmountDays(double amount, int days) {
    return getInterestRate() * amount * days / 365;
  }

  public double getInterestRate() {
    return type.getInterestRate();
  }

  public void setInterestRate(double interestRate) {
    type.setInterestRate(interestRate);
  }
}
