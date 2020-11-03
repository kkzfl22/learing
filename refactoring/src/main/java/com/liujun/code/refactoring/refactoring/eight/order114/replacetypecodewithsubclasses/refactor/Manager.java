package com.liujun.code.refactoring.refactoring.eight.order114.replacetypecodewithsubclasses.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Manager extends Employee {

  @Override
  public int getType() {
    return Employee.MANAGER;
  }
}
