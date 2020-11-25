package com.liujun.code.refactoring.refactoring.nine.order097.intrucenullobject.refactor;

/**
 * 场所信息
 *
 * @author liujun
 * @version 0.0.1
 */
public class Site {

  private Customer customer;

  public Customer getCustomer() {
    return (customer == null) ? Customer.newNull() : customer;
  }
}
