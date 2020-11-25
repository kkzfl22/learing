package com.liujun.code.refactoring.refactoring.nine.order096.replaceconditionalwithpolymorphism.refactor;

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
