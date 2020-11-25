package com.liujun.code.refactoring.refactoring.ten.order106.replaceparameterwithexplicitmethods.refactor;

/**
 * 员工/薪资为例
 *
 * <p>不同的员工有不同的薪资
 *
 * @author liujun
 * @version 0.0.1
 */
public abstract class Employee {

  static final int ENGINEER = 0;

  static final int SALESMAN = 1;

  static final int MANAGER = 2;

  /**
   * 封装类型码
   *
   * @return
   */
  public abstract int getType();
}
