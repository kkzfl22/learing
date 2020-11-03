package com.liujun.code.refactoring.refactoring.eight.order115.replacetypecodewithstate.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public abstract class EmployeeType {

  /**
   * 获取类型码
   *
   * @return 返回类型信息
   */
  abstract int getTypeCode();

  static final int ENGINEER = 0;

  static final int SALESMAN = 1;

  static final int MANAGER = 2;

  public static EmployeeType newType(int typeArgs) {
    switch (typeArgs) {
      case ENGINEER:
        return new Engineer();
      case SALESMAN:
        return new Saleman();
      case MANAGER:
        return new Manager();
      default:
        throw new IllegalArgumentException("Incorrect Employee code");
    }
  }
}
