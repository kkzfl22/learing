package com.liujun.code.refactoring.refactoring.ten.order108.replaceparameterwithmethods.refactor;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Price {

  private int quantity = ThreadLocalRandom.current().nextInt();
  private int itemPrice = ThreadLocalRandom.current().nextInt();

  public double getPrice() {
    return discountedPrice();
  }

  /**
   * 提练折扣函数的计算
   *
   * @return
   */
  private int getDiscountLevel() {
    if (quantity > 100) {
      return 2;
    }
    return 1;
  }

  private double discountedPrice() {
    // 替换为对函数的调用
    if (getDiscountLevel() == 2) {
      return basePrice() * 0.1;
    } else {
      return basePrice() * 0.05;
    }
  }

  private int basePrice() {
    return quantity * itemPrice;
  }
}
