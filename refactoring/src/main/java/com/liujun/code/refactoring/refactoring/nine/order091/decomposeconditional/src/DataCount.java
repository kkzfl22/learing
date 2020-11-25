package com.liujun.code.refactoring.refactoring.nine.order091.decomposeconditional.src;

import java.util.Date;

/**
 * 计算总价=数量*单价
 *
 * <p>商品在冬季与夏季的价格是不同的
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataCount {

  private double quantity;

  private double winterRate;

  private double summerRate;

  private double winterServiceCharge;

  private static final int SUMMER_START = 6;

  private static final int SUMMER_END = 9;

  public double dataCount(int season, Date currData) {
    double charge;

    if (season < SUMMER_START || season > SUMMER_END) {
      charge = quantity * winterRate * winterServiceCharge;
    } else {
      charge = quantity * summerRate;
    }

    return charge;
  }
}
