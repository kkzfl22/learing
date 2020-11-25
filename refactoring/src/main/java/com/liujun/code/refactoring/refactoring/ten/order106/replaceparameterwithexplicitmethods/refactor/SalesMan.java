package com.liujun.code.refactoring.refactoring.ten.order106.replaceparameterwithexplicitmethods.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class SalesMan extends Employee {

  @Override
  public int getType() {
    return Employee.SALESMAN;
  }

  public static Employee createSalesMan() {
    return new SalesMan();
  }
}
