package com.liujun.code.refactoring.refactoring.ten.order112.replaceconstructorwithfactormethod.src.factory;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Saleman extends EmployeeType {

  @Override
  int getTypeCode() {
    return EmployeeType.SALESMAN;
  }

  @Override
  int payAmount(Employee emp) {
    return emp.getMonthlySalary() + emp.getCommission();
  }
}
