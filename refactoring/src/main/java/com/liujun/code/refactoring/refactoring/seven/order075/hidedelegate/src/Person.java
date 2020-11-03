package com.liujun.code.refactoring.refactoring.seven.order075.hidedelegate.src;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Person {

  /** 部门信息 */
  private Department department;

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }
}
