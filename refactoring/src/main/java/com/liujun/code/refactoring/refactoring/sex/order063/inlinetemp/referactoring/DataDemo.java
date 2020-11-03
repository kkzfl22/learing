package com.liujun.code.refactoring.refactoring.sex.order063.inlinetemp.referactoring;

/**
 * 进行内联函数的测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataDemo {

  public boolean runData() {
    return getValue() > 1000;
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
