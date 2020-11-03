package com.liujun.code.refactoring.refactoring.seven.order076.removemiddleman.refactoring;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Person {

  /** 部门信息 */
  private Department department;

  public void setDepartment(Department department) {
    this.department = department;
  }

  /**
   * 建立一个函数用于获得受托对象
   *
   * @return
   */
  public Department getDepartment() {
    return department;
  }
}
