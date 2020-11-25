package com.liujun.code.refactoring.refactoring.ten.order114.replaceerrorcodewithexception.refactor.checkexception;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Client {

  private Account dataAccount = new Account();

  public void use(int money) {
    try {
      dataAccount.withdraw(money);
      doTheUsualThine();
    } catch (Exception e) {
      e.printStackTrace();
      handleOverdrawn();
    }
  }

  private void handleOverdrawn() {
    System.out.println("data out");
  }

  private void doTheUsualThine() {
    System.out.println("do usual thing");
  }
}
