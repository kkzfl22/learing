package com.liujun.code.refactoring.first.code.refactoring2;

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
   * 将计算逻辑分离
   *
   * @return 积分计算
   */
  public double countAmount() {
    double thisAmount = 0;
    switch (this.getMovie().getPriceCode()) {
        // 如果当前影片为普通片，且时间超过2天，则1.5倍积分
      case Movie.REGULAR:
        thisAmount += 2;
        if (this.getDaysRented() > 2) {
          thisAmount += (this.getDaysRented() - 2) * 1.5;
        }
        break;
        // 新片为3倍租分
      case Movie.NEW_RELEASE:
        thisAmount += this.getDaysRented() * 3;
        break;
        // 儿童片，时间超过3天，1.5倍积分
      case Movie.CHILDRENS:
        thisAmount += 1.5;
        if (this.getDaysRented() > 3) {
          thisAmount += (this.getDaysRented() - 3) * 1.5;
        }
        break;
    }
    return thisAmount;
  }
}
