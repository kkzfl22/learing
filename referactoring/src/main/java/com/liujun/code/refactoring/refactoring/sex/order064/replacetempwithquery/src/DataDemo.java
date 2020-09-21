package com.liujun.code.refactoring.refactoring.sex.order064.replacetempwithquery.src;

/**
 * 以查询取代临时变量人原始代码
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataDemo {

  private final double quantity = 200;

  private final double itemPrice = 10;

  public double count() {
    double basePrice = quantity * itemPrice;
    double discountFactor;
    if (basePrice > 1000) {
      discountFactor = 0.95;
    } else {
      discountFactor = 0.98;
    }
    return basePrice * discountFactor;
  }
}
