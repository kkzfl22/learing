package com.liujun.code.refactoring.refactoring.ten.order112.replaceconstructorwithfactormethod.src.factory;

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

  @Override
  int payAmount(Employee emp) {
    return emp.getMonthlySalary();
  }
}
