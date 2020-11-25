package com.liujun.code.refactoring.refactoring.ten.order112.replaceconstructorwithfactormethod.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Employee {

  private int type;

  static final int ENGINEER = 0;

  static final int SALESMAN = 1;

  static final int MANAGER = 2;

  /**
   * 构建函数改为private
   *
   * @param type
   */
  private Employee(int type) {
    this.type = type;
  }

  /**
   * 创建工厂函数
   *
   * @param type
   * @return
   */
  public static Employee create(int type) {
    return new Employee(type);
  }

  public static void main(String[] args) {
    Employee emp = Employee.create(Employee.ENGINEER);
  }
}
