package com.liujun.code.refactoring.refactoring.seven.order072.movefield.src;

/**
 * 使用账户类来原始代码
 *
 * @author liujun
 * @version 0.0.1
 */
public class Account {

  private final AccountType type;

  /** 利率计算 */
  private double interestRate;

  public Account(AccountType type, double interestRate) {
    this.type = type;
    this.interestRate = interestRate;
  }

  /**
   * 利率计算
   *
   * @param amount
   * @param days
   * @return
   */
  double interestForAmountDays(double amount, int days) {
    return interestRate * amount * days / 365;
  }
}
