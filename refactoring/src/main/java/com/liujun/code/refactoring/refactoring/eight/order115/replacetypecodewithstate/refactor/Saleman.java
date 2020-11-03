package com.liujun.code.refactoring.refactoring.eight.order115.replacetypecodewithstate.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Saleman extends EmployeeType {

  @Override
  int getTypeCode() {
    return EmployeeType.SALESMAN;
  }
}
