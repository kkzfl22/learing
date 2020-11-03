package com.liujun.code.refactoring.refactoring.eight.order114.replacetypecodewithsubclasses.src;

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

  Employee(int type) {
    this.type = type;
  }
}
