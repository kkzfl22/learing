package com.liujun.code.refactoring.refactoring.nine.order096.replaceconditionalwithpolymorphism.src;

/**
 * 现在假设需求是，可以将表现出色的工程师提升为经理
 *
 * @author liujun
 * @version 0.0.1
 */
public class Employee {

  private EmployeeType type;

  /** 岗位基本工资 */
  private static final int MONTHLY_SALARY = 100;

  /** */
  private static final int COMMISSION = 30;

  /** */
  private static final int BONUS = 50;

  Employee(int type) {
    this.setType(type);
  }

  int payAmount() {
    switch (this.getType()) {
      case EmployeeType.ENGINEER:
        return MONTHLY_SALARY;
      case EmployeeType.SALESMAN:
        return MONTHLY_SALARY + COMMISSION;
      case EmployeeType.MANAGER:
        return MONTHLY_SALARY + BONUS;
      default:
        throw new RuntimeException("Incorrect Employee");
    }
  }

  /**
   * 1，封装类型码字段
   *
   * @return
   */
  public int getType() {
    return type.getTypeCode();
  }

  public void setType(int typeArgs) {
    type = EmployeeType.newType(typeArgs);
  }
}
