package com.liujun.code.refactoring.refactoring.eight.order082.replacedatavaluewithoject.refactor;

/**
 * 客户的概念
 *
 * @author liujun
 * @version 0.0.1
 */
public class Customer {

  private final String customer;

  public Customer(String customer) {
    this.customer = customer;
  }

  public String getCustomer() {
    return customer;
  }
}
