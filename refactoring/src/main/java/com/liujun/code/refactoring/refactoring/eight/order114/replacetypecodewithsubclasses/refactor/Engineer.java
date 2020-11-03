package com.liujun.code.refactoring.refactoring.eight.order114.replacetypecodewithsubclasses.refactor;

/**
 * 工程师类
 *
 * @author liujun
 * @version 0.0.1
 */
public class Engineer extends Employee {

  @Override
  public int getType() {
    return Employee.ENGINEER;
  }
}
