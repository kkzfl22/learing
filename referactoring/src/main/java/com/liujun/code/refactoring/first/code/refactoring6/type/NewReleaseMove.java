package com.liujun.code.refactoring.first.code.refactoring6.type;

import com.liujun.code.refactoring.first.code.refactoring6.MoveTypeCountInf;

/**
 * 孩子类的影片
 *
 * @author liujun
 * @version 0.0.1
 */
public class NewReleaseMove implements MoveTypeCountInf {

  /** 减免期 */
  private static final double USE_VALUE = 3;

  /** 积分点 */
  private static final int FREQUENT_RENTER = 1;

  /** 积分点 */
  private static final int FREQUENT_RENTER_TWO = 2;

  @Override
  public double countAmount(int daysRented) {
    double thisAmount = daysRented * USE_VALUE;

    return thisAmount;
  }

  @Override
  public int frequentRenterCount(int daysRented) {

    if (daysRented > 1) {
      return FREQUENT_RENTER_TWO;
    }

    return FREQUENT_RENTER;
  }
}
