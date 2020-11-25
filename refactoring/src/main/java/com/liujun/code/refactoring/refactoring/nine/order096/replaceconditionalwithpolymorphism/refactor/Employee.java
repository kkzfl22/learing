package com.liujun.code.refactoring.refactoring.nine.order096.replaceconditionalwithpolymorphism.refactor;

/**
 * 现在假设需求是，可以将表现出色的工程师提升为经理
 *
 * @author liujun
 * @version 0.0.1
 */
public class Employee {

  private EmployeeType type;

  /** 岗位基本工资 */
  private final int MONTHLY_SALARY = 100;

  /** */
  private final int COMMISSION = 30;

  /** */
  private final int BONUS = 50;

  Employee(int type) {
    this.setType(type);
  }

  int payAmount() {
    return type.payAmount(this);
  }

  public int getMonthlySalary() {
    return MONTHLY_SALARY;
  }

  public int getCommission() {
    return COMMISSION;
  }

  public int getBonus() {
    return BONUS;
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
