package com.liujun.code.refactoring.refactoring.seven.order075.hidedelegate.refactoring;

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
   * 通过此函数便可隐藏调用关系。
   * @return
   */
  public Person getManager() {
    return department.getManager();
  }
}
