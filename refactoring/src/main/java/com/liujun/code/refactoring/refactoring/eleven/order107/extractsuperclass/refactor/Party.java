package com.liujun.code.refactoring.refactoring.eleven.order107.extractsuperclass.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public abstract class Party {

  private String name;

  public Party(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  /**
   * 上移公共方法
   *
   * @return
   */
  public abstract int getAnnualCost();
}
