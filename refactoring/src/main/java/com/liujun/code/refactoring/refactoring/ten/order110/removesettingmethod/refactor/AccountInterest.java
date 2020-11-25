package com.liujun.code.refactoring.refactoring.ten.order110.removesettingmethod.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class AccountInterest extends AccountData {

  private double interestRate;

  public AccountInterest(String id, double interestRate) {
    super(id);
    this.interestRate = interestRate;
  }
}

class AccountData {
  private String id;

  public AccountData(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
