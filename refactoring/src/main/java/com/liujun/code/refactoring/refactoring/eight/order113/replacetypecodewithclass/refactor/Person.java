package com.liujun.code.refactoring.refactoring.eight.order113.replacetypecodewithclass.refactor;

/**
 * 以人所有的4类血液类型为例
 *
 * @author liujun
 * @version 0.0.1
 */
public class Person {

  public BloodGroup bloodGroup;

  /**
   * 建立构建函数
   *
   * @param bloodGroup
   */
  public Person(BloodGroup bloodGroup) {
    this.bloodGroup = bloodGroup;
  }

  /**
   * 设值函数
   *
   * @param bloodGroup
   */
  public void setBloodGroup(BloodGroup bloodGroup) {
    this.bloodGroup = bloodGroup;
  }



  /**
   * 加入新的取值函数
   *
   * @return 血液类型对象
   */
  public BloodGroup getBloodGroup() {
    return bloodGroup;
  }
}
