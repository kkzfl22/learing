package com.liujun.code.refactoring.refactoring.eleven.order103.pullupconstructor.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Employee {

  protected String name;

  protected String id;

  protected Employee(String name, String id) {
    this.name = name;
    this.id = id;
  }
}
