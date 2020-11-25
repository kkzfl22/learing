package com.liujun.code.refactoring.refactoring.ten.order114.replaceerrorcodewithexception.refactor.checkexception;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 透支帐户余额
 *
 * @author liujun
 * @version 0.0.1
 */
public class Account {

  private int balance = ThreadLocalRandom.current().nextInt();

  int withdraw(int amount) throws BalanceException {
    if (amount > balance) {
      throw new BalanceException();
    }

    balance -= amount;
    return 0;
  }


}
