package com.liujun.code.refactoring.refactoring.seven.order077.introduceforeignmethod.referactoring;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author liujun
 * @version 0.0.1
 */
@Getter
@Setter
@ToString
public class DataUse {

  private int year;

  private int month;

  private int date;

  public DataUse(int year, int month, int date) {
    this.year = year;
    this.month = month;
    this.date = date;
  }

  public void useDate() {
    System.out.println(nextDate());
  }

  /**
   * 将所有对date的函数封装到一个函数中，这就是一个外加函数。
   *
   * @return
   */
  private Date nextDate() {
    Date nextStart = new Date(this.getYear(), this.getMonth(), this.getDate() + 1);
    return nextStart;
  }
}
