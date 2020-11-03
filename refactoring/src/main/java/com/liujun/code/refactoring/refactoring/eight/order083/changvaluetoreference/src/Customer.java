package com.liujun.code.refactoring.refactoring.eight.order083.changvaluetoreference.src;

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
