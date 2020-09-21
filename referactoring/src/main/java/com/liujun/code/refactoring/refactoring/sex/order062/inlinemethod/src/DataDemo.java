package com.liujun.code.refactoring.refactoring.sex.order062.inlinemethod.src;

/**
 * 样例代码
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataDemo {

  private final int numberOfLateDeliveries;

  public DataDemo(int numberOfLateDeliveries) {
    this.numberOfLateDeliveries = numberOfLateDeliveries;
  }

  public int getReting() {
    return (moreThanFiveLateDeliveries()) ? 2 : 1;
  }

  private boolean moreThanFiveLateDeliveries() {
    return numberOfLateDeliveries > 5;
  }
}
