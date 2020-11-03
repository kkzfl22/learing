package com.liujun.code.refactoring.refactoring.sex.order065.introduceexplainingvariable.referactoring;

/**
 * 使用提取方法来重构代码
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataCount2 {

  private double quantity = 100;

  private double itemPrice = 50;

  /**
   * 计算价格
   *
   * @return
   */
  public double price() {
    return basePrice() - quantityDiscount() + shipping();
  }

  /**
   * 基础的价格函数
   *
   * @return
   */
  private double basePrice() {
    return quantity * itemPrice;
  }

  private double quantityDiscount() {
    return Math.max(0, quantity - 500) * itemPrice * 0.05;
  }

  private double shipping() {
    return Math.min(basePrice() * 0.1, 100.0);
  }
}
