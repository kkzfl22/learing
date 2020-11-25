package com.liujun.code.refactoring.refactoring.ten.order114.replaceerrorcodewithexception.src;

/**
 * @author liujun
 * @version 0.0.1
 */
public class ClientCheck {

  private Account dataAccount = new Account();

  public void use(int money) {
    if (dataAccount.withdraw(money) == -1) {
      handleOverdrawn();
    } else {
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
