package com.liujun.code.refactoring.refactoring.eight.order082.replacedatavaluewithoject.src;

/**
 * 订单类
 *
 * @author liujun
 * @version 0.0.1
 */
public class Order {

  private String arg;

  private String customer;

  public Order(String customer) {
    this.customer = customer;
  }

  public String getArg() {
    return arg;
  }

  public void setArg(String arg) {
    this.arg = arg;
  }

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }
}
