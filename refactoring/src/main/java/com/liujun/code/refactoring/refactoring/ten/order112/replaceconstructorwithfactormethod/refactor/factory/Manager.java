package com.liujun.code.refactoring.refactoring.ten.order112.replaceconstructorwithfactormethod.refactor.factory;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Manager extends EmployeeType {

  @Override
  int getTypeCode() {
    return EmployeeType.MANAGER;
  }

  @Override
  int payAmount(Employee emp) {
    return emp.getMonthlySalary() + emp.getBonus();
  }
}
