package com.liujun.code.refactoring.refactoring.nine.order096.replaceconditionalwithpolymorphism.src;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Manager extends EmployeeType {

  @Override
  int getTypeCode() {
    return EmployeeType.MANAGER;
  }
}
