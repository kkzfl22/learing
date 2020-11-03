package com.liujun.code.refactoring.refactoring.sex.order063.inlinetemp.src;

/**
 * 内联临时变量的重构原始代码
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataDemo {

  public boolean runData() {
    double basePrice = getValue();
    return basePrice > 1000;
  }

  /**
   * 演示函数，仅获取值使用
   *
   * @return
   */
  private double getValue() {
    return 1;
  }
}
