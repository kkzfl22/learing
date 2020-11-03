package com.liujun.code.refactoring.first.code.refactoring6;

/**
 * 表示某位顾客租了一部影片
 *
 * @author liujun
 * @version 0.0.1
 */
public class Rental {

  /** 影片信息 */
  private Movie movie;

  /** 租期 */
  private int daysRented;

  public Rental(Movie movie, int daysRented) {
    this.movie = movie;
    this.daysRented = daysRented;
  }

  public Movie getMovie() {
    return movie;
  }

  public int getDaysRented() {
    return daysRented;
  }

  /**
   * 费用计算的逻辑
   *
   * @return
   */
  public double getCharge() {
    return movie.countAmount(daysRented);
  }

  /**
   * 积分计算方法
   *
   * @return 积分
   */
  public int frequentRenterCount() {
    return movie.frequentRenterCount(daysRented);
  }
}
