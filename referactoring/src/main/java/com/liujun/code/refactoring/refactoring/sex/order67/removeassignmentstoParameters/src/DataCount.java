package com.liujun.code.refactoring.refactoring.sex.order67.removeassignmentstoParameters.src;

/**
 * 移除对参数的赋值的原代码
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataCount {

  public int discount(int inputVal, int quantity, int yeartoDate) {
    if (inputVal > 50) {
      inputVal -= 2;
    }
    if (quantity > 100) {
      inputVal -= 1;
    }
    if (yeartoDate > 10000) {
      inputVal -= 4;
    }
    return inputVal;
  }
}
