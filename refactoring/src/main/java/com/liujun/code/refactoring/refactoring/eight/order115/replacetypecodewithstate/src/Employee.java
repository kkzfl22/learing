package com.liujun.code.refactoring.refactoring.eight.order115.replacetypecodewithstate.src;

/**
 * 员工/薪资为例
 *
 * <p>不同的员工有不同的薪资
 *
 * @author liujun
 * @version 0.0.1
 */
public class Employee {

  private int type;

  static final int ENGINEER = 0;

  static final int SALESMAN = 1;

  static final int MANAGER = 2;

  /** 岗位基本工资 */
  private static final int MONTHLY_SALARY = 100;

  /** */
  private static final int COMMISSION = 30;

  /** */
  private static final int BONUS = 50;

  Employee(int type) {
    this.type = type;
  }

  int payAmount() {
    switch (type) {
      case ENGINEER:
        return MONTHLY_SALARY;
      case SALESMAN:
        return MONTHLY_SALARY + COMMISSION;
      case MANAGER:
        return MONTHLY_SALARY + BONUS;
      default:
        throw new RuntimeException("Incorrect Employee");
    }
  }
}
