package com.liujun.code.refactoring.refactoring.ten.order114.replaceerrorcodewithexception.refactor;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 透支帐户余额
 *
 * @author liujun
 * @version 0.0.1
 */
public class Account {

  private int balance = ThreadLocalRandom.current().nextInt();

  void withdraw(int amount) {
    Assert.isTrue("sufficent founds :", amount <= balance);
    balance -= amount;
  }

  boolean canWithdraw(int amount) {
    if (amount < balance) {
      return true;
    }
    return false;
  }
}
