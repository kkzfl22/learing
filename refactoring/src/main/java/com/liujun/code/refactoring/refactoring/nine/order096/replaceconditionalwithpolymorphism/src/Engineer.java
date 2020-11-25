package com.liujun.code.refactoring.refactoring.nine.order096.replaceconditionalwithpolymorphism.src;

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
