package com.liujun.code.refactoring.refactoring.eight.order115.replacetypecodewithstate.refactor;

/**
 * 工程师类
 *
 * @author liujun
 * @version 0.0.1
 */
public class Engineer extends EmployeeType {

  @Override
  int getTypeCode() {
    return EmployeeType.ENGINEER;
  }
}
