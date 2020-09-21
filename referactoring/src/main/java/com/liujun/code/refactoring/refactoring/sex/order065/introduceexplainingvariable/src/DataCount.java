package com.liujun.code.refactoring.refactoring.sex.order065.introduceexplainingvariable.src;

/**
 * 引入解释性变量的原始代码
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataCount {

  private double quantity = 100;

  private double itemPrice = 50;

  public double price() {
    return quantity * itemPrice
        - (Math.max(0, quantity - 500) * itemPrice * 0.05)
            + Math.min(quantity * itemPrice * 0.1, 100.0);
  }
}
