package com.liujun.code.refactoring.first.code.src;

import java.util.ArrayList;
import java.util.List;

/**
 * 顾客
 *
 * @author liujun
 * @version 0.0.1
 */
public class Customer {

  /** 顾客的名称 */
  private String name;

  /** 租借信息 */
  private List<Rental> rentals = new ArrayList<>();

  public Customer(String name) {
    this.name = name;
  }

  /**
   * 租借信息
   *
   * @param arg
   */
  public void addRental(Rental arg) {
    rentals.add(arg);
  }

  public String getName() {
    return name;
  }

  /**
   * 生成详单的函数
   *
   * @return
   */
  public String statement() {
    double totalAmount = 0;
    int frequentRenterPoints = 0;
    String result = "Rental Record for " + getName() + "\n";
    for (Rental each : rentals) {
      double thisAmount = 0;
      switch (each.getMovie().getPriceCode()) {
          // 如果当前影片为普通片，且时间超过2天，则1.5倍积分
        case Movie.REGULAR:
          thisAmount += 2;
          if (each.getDaysRented() > 2) {
            thisAmount += (each.getDaysRented() - 2) * 1.5;
          }
          break;
          // 新片为3倍租分
        case Movie.NEW_RELEASE:
          thisAmount += each.getDaysRented() * 3;
          break;
          // 儿童片，时间超过3天，1.5倍积分
        case Movie.CHILDRENS:
          thisAmount += 1.5;
          if (each.getDaysRented() > 3) {
            thisAmount += (each.getDaysRented() - 3) * 1.5;
          }
          break;
      }

      frequentRenterPoints++;
      // 如果当前为新片，并且时间超过1天，则租户的点加1,用于表示租借了几部影片
      if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE && each.getDaysRented() > 1) {
        frequentRenterPoints++;
      }
      result += "\t" + each.getMovie().getTitle() + "\t" + thisAmount + "\n";
      totalAmount += thisAmount;
    }

    result += " Amount owed is " + totalAmount + "\n";
    result += " You earned " + frequentRenterPoints + " frequent renter points";
    return result;
  }
}
