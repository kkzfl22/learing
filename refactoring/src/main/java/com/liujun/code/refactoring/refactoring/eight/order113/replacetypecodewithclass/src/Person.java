package com.liujun.code.refactoring.refactoring.eight.order113.replacetypecodewithclass.src;

/**
 * 以人所有的4类血液类型为例
 *
 * @author liujun
 * @version 0.0.1
 */
public class Person {

  public static final int O = 0;

  public static final int A = 1;

  public static final int B = 2;

  public static final int AB = 3;

  public int bloodGroup;

  public Person(int bloodGroup) {
    this.bloodGroup = bloodGroup;
  }

  public void setBloodGroup(int bloodGroup) {
    this.bloodGroup = bloodGroup;
  }

  public int getBloodGroup() {
    return bloodGroup;
  }
}
