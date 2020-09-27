package com.liujun.code.refactoring.refactoring.seven.order071.movemethod.referactoring;

/**
 * 账户的类型
 *
 * @author liujun
 * @version 0.0.1
 */
public class AccountType {

  /**
   * 是否产生额外的费用
   *
   * @return true，产生额外的费用,false 不产生额外的费用
   */
  boolean isPremium() {
    return true;
  }

  /**
   * 透支金额的计费规则
   *
   * @param account 源对外的引用
   * @return 费用
   */
  double overdraftCharge(Account account) {
    // 是否产生额外的费用
    if (this.isPremium()) {
      double result = 10;
      if (account.getDayOverdrawn() > 7) {
        result += (account.getDayOverdrawn() - 7) * 0.85;
      }
      return result;
    } else {
      return account.getDayOverdrawn() * 1.75;
    }
  }
}
