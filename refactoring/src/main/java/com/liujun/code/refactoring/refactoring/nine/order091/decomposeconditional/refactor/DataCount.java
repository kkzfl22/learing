package com.liujun.code.refactoring.refactoring.nine.order091.decomposeconditional.refactor;

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

  public double dataCount(int month) {
    double charge;

    if (this.notSummer(month)) {
      charge = this.winterCharge();
    } else {
      charge = this.summerCharge();
    }

    return charge;
  }

  private boolean notSummer(int month) {
    return month < SUMMER_START || month > SUMMER_END;
  }

  private double summerCharge() {
    return quantity * winterRate * winterServiceCharge;
  }

  private double winterCharge() {
    return quantity * summerRate;
  }
}
