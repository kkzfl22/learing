package com.liujun.code.refactoring.refactoring.ten.order108.replaceparameterwithmethods.src;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Price {

  private int quantity = ThreadLocalRandom.current().nextInt();
  private int itemPrice = ThreadLocalRandom.current().nextInt();

  public double getPrice() {

    int basePrice = quantity * itemPrice;
    int discountLevel;
    if (quantity > 100) {
      discountLevel = 2;
    } else {
      discountLevel = 1;
    }

    double finalPrice = discountedPrice(basePrice, discountLevel);
    return finalPrice;
  }

  private double discountedPrice(int basePrice, int discountLevel) {
    if (discountLevel == 2) {
      return basePrice * 0.1;
    } else {
      return basePrice * 0.05;
    }
  }
}
