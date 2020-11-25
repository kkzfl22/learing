package com.liujun.code.refactoring.refactoring.ten.order110.removesettingmethod.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class AccountInterest2 extends AccountData2 {

  private double interestRate;

  public AccountInterest2(String id, double interestRate) {
    this.initializedIdValue(id);
    this.interestRate = interestRate;
  }
}

class AccountData2 {
  private String id;

  public String getId() {
    return id;
  }

  public void initializedIdValue(String id) {
    this.id = "ZZ" + id;
  }
}
