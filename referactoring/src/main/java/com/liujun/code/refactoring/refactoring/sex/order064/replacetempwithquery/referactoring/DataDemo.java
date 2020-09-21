package com.liujun.code.refactoring.refactoring.sex.order064.replacetempwithquery.referactoring;

/**
 * @author liujun
 * @version 0.0.1
 */
public class DataDemo {

  private final double quantity = 200;

  private final double itemPrice = 10;

  public double count() {
    return basePrice() * discountFactor();
  }

  private double basePrice() {
    return quantity * itemPrice;
  }

  private double discountFactor() {
    final double discountFactor;
    if (basePrice() > 1000) {
      discountFactor = 0.95;
    } else {
      discountFactor = 0.98;
    }

    return discountFactor;
  }
}
