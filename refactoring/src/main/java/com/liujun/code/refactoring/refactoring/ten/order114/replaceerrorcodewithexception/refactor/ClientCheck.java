package com.liujun.code.refactoring.refactoring.ten.order114.replaceerrorcodewithexception.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class ClientCheck {

  private Account dataAccount = new Account();

  public void use(int money) {
    if (!dataAccount.canWithdraw(money)) {
      handleOverdrawn();
    } else {
      dataAccount.withdraw(money);
      doTheUsualThine();
    }
  }

  private void handleOverdrawn() {
    System.out.println("data out");
  }

  private void doTheUsualThine() {
    System.out.println("do usual thing");
  }
}
