package com.liujun.code.refactoring.refactoring.ten.order106.replaceparameterwithexplicitmethods.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Manager extends Employee {

  @Override
  public int getType() {
    return Employee.MANAGER;
  }

  public static Employee createManager() {
    return new Manager();
  }
}
