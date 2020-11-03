package com.liujun.code.refactoring.refactoring.seven.order075.hidedelegate.src;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Department {

  private String chargeCode;

  private Person manager;

  public Department(Person manager) {
    this.manager = manager;
  }

  public String getChargeCode() {
    return chargeCode;
  }

  public Person getManager() {
    return manager;
  }

  public void setChargeCode(String chargeCode) {
    this.chargeCode = chargeCode;
  }
}
