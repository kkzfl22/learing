package com.liujun.code.refactoring.first.code.refactoring6.type;

import com.liujun.code.refactoring.first.code.refactoring6.MoveTypeCountInf;

/**
 * 孩子类的影片
 *
 * @author liujun
 * @version 0.0.1
 */
public class RegularMove implements MoveTypeCountInf {

  private static final double DEFAULT_VALUE = 1.5;

  /** 费用 */
  private static final double COUNT_PRICE = 2;

  /** 积分点 */
  private static final int FREQUENT_RENTER = 1;

  @Override
  public double countAmount(int daysRented) {
    double thisAmount = COUNT_PRICE;
    if (daysRented > COUNT_PRICE) {
      thisAmount += (daysRented - COUNT_PRICE) * DEFAULT_VALUE;
    }

    return thisAmount;
  }

  @Override
  public int frequentRenterCount(int daysRented) {
    return FREQUENT_RENTER;
  }
}
