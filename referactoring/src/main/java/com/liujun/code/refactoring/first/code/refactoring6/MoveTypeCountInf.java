package com.liujun.code.refactoring.first.code.refactoring6;

/**
 * 影片的分类计算接口
 *
 * @author liujun
 * @version 0.0.1
 */
public interface MoveTypeCountInf {

  /**
   * 费用计算的逻辑
   *
   * @param daysRented 租用时间
   * @return 费用计算
   */
  double countAmount(int daysRented);

  /**
   * 积分计算方法
   *
   * @param daysRented 租用时间
   * @return 积分
   */
  int frequentRenterCount(int daysRented);
}
