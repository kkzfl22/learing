package com.liujun.code.refactoring.first.code.refactoring6.type;

import com.liujun.code.refactoring.first.code.refactoring6.MoveTypeCountInf;

/**
 * 孩子类的影片
 *
 * @author liujun
 * @version 0.0.1
 */
public class ChildrenMove implements MoveTypeCountInf {

  private static final double DEFAULT_VALUE = 1.5;

  /** 减免期 */
  private static final double USE_VALUE = 3;

  /** 积分点 */
  private static final int FREQUENT_RENTER = 1;

  @Override
  public double countAmount(int daysRented) {
    double thisAmount = DEFAULT_VALUE;
    if (daysRented > USE_VALUE) {
      thisAmount += (daysRented - USE_VALUE) * DEFAULT_VALUE;
    }

    return thisAmount;
  }

  @Override
  public int frequentRenterCount(int daysRented) {
    return FREQUENT_RENTER;
  }
}
