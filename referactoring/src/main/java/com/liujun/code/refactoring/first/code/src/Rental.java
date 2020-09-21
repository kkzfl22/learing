package com.liujun.code.refactoring.first.code.src;

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
}
