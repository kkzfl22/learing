package com.liujun.code.refactoring.refactoring.eight.order082.replacedatavaluewithoject.refactor;

/**
 * 订单类
 *
 * @author liujun
 * @version 0.0.1
 */
public class Order {

  private String arg;

  private Customer customer;

  public Order(String customerName) {
    this.customer = new Customer(customerName);
  }

  public String getArg() {
    return arg;
  }

  public void setArg(String arg) {
    this.arg = arg;
  }

  public String getCustomer() {
    return this.customer.getCustomer();
  }

  public void setCustomer(String customerName) {
    this.customer = new Customer(customerName);
  }
}
