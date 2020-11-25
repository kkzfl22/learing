package com.liujun.code.refactoring.refactoring.eleven.order103.pullupconstructor.src;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Manager extends Employee {

  private int grade;

  public Manager(String name, String id, int grade) {
    this.name = name;
    this.id = id;
    this.grade = grade;
  }
}
