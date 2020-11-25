package com.liujun.code.refactoring.refactoring.eleven.order103.pullupconstructor.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Manager extends Employee {

  private int grade;

  public Manager(String name, String id, int grade) {
    super(name, id);
    this.grade = grade;
  }
}
