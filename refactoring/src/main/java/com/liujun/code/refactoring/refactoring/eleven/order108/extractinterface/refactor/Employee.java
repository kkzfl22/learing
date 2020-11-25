package com.liujun.code.refactoring.refactoring.eleven.order108.extractinterface.refactor;

/**
 * 员工信息
 *
 * @author liujun
 * @version 0.0.1
 */
public class Employee implements Billable {

  private String name;

  private int rate;

  @Override
  public int getRate() {
    return rate;
  }

  @Override
  public boolean hashSpecialSkill() {
    return true;
  }
}
