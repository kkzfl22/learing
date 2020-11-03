package com.liujun.code.refactoring.refactoring.seven.order071.movemethod.src;

/**
 * 使用账户类来原始代码
 *
 * @author liujun
 * @version 0.0.1
 */
public class Account {

  private final AccountType type;

  private final int dayOverdrawn;

  public Account(AccountType type, int dayOverdrawn) {
    this.type = type;
    this.dayOverdrawn = dayOverdrawn;
  }

  /**
   * 帐户透支的费用
   *
   * @return
   */
  double overdraftCharge() {
    // 是否产生额外的费用
    if (type.isPremium()) {
      double result = 10;
      if (dayOverdrawn > 7) {
        result += (dayOverdrawn - 7) * 0.85;
      }
      return result;
    } else {
      return dayOverdrawn * 1.75;
    }
  }

  /**
   * 银行的费用
   *
   * @return
   */
  double bankCharge() {
    double result = 4.5;
    if (dayOverdrawn > 0) {
      return result += overdraftCharge();
    }
    return result;
  }
}
