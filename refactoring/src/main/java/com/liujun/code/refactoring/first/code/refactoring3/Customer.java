package com.liujun.code.refactoring.first.code.refactoring3;

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
      // 调用方法进行积分的计算
      double thisAmount = each.countAmount();

      // 积分计算的方法
      frequentRenterPoints = each.frequentRenterCount();

      result += "\t" + each.getMovie().getTitle() + "\t" + thisAmount + "\n";
      totalAmount += thisAmount;
    }

    result += " Amount owed is " + totalAmount + "\n";
    result += " You earned " + frequentRenterPoints + " frequent renter points";
    return result;
  }
}
