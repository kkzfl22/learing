package com.liujun.code.refactoring.refactoring.sex.order67.removeassignmentstoParameters.referactoring;

/**
 * 移除对参数的赋值的重构。
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataCount {

  public int discount(int inputVal, int quantity, int yeartoDate) {
    // 以临时变量取代参数赋值
    int result = inputVal;

    if (inputVal > 50) {
      result -= 2;
    }
    if (quantity > 100) {
      result -= 1;
    }
    if (yeartoDate > 10000) {
      result -= 4;
    }
    return result;
  }
}
