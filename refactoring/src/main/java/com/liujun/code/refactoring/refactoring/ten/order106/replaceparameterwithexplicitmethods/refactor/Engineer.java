package com.liujun.code.refactoring.refactoring.ten.order106.replaceparameterwithexplicitmethods.refactor;

/**
 * 工程师类
 *
 * @author liujun
 * @version 0.0.1
 */
public class Engineer extends Employee {

  @Override
  public int getType() {
    return Employee.ENGINEER;
  }

  public static Employee createEngineer()
  {
    return new Engineer();
  }

}
