package com.liujun.code.refactoring.refactoring.eleven.order107.extractsuperclass.src;

/**
 * 员工信息
 *
 * @author liujun
 * @version 0.0.1
 */
public class Employee {

  private String name;

  private String id;

  private int annualCost;

  public Employee(String name, String id, int annualCost) {
    this.name = name;
    this.id = id;
    this.annualCost = annualCost;
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }

  public int getAnnualCost() {
    return annualCost;
  }
}
