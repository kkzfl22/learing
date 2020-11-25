package com.liujun.code.refactoring.refactoring.ten.order110.removesettingmethod.src;

/**
 * @author liujun
 * @version 0.0.1
 */
public class AccountInterest extends AccountData {

  private double interestRate;

  public AccountInterest(String id, double interestRate) {
    setId(id);
    this.interestRate = interestRate;
  }
}

class AccountData {
  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
