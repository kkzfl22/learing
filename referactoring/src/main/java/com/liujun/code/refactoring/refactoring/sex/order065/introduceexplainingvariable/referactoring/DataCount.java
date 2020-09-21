package com.liujun.code.refactoring.refactoring.sex.order065.introduceexplainingvariable.referactoring;

/**
 * 引入解释性变量
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataCount {

  private double quantity = 100;

  private double itemPrice = 50;

  public double price() {
    // 将表达式中的部分提取，使用临时变量替代
    final double basePrice = quantity * itemPrice;
    // 再次提取临时变量
    final double quantityDiscount = (Math.max(0, quantity - 500) * itemPrice * 0.05);
    // 提取最后一个表达式
    final double shipping = Math.min(basePrice * 0.1, 100.0);

    return basePrice - quantityDiscount + shipping;
  }
}
