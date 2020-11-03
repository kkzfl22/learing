package com.liujun.code.refactoring.refactoring.seven.order077.introduceforeignmethod.src;

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
    Date nextStart = new Date(this.getYear(), this.getMonth(), this.getDate() + 1);

    System.out.println(nextStart);
  }
}
