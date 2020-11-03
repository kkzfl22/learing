package com.liujun.code.refactoring.first.code.refactoring6;

import java.util.ArrayList;
import java.util.List;

/**
 * 顾客
 *
 * <p>去除临时变量
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
    String result = "Rental Record for " + getName() + "\n";
    for (Rental each : rentals) {
      result += "\t" + each.getMovie().getTitle() + "\t" + each.getCharge() + "\n";
    }

    result += " Amount owed is " + countTotalAmount() + "\n";
    result += " You earned " + frequentRenterPointCount() + " frequent renter points";
    return result;
  }

  /**
   * 提取计算方法
   *
   * @return 值的计算
   */
  private double countTotalAmount() {
    double totalAmount = 0;

    for (Rental each : rentals) {
      // 调用方法进行积分的计算
      totalAmount += each.getCharge();
    }

    return totalAmount;
  }

  /**
   * 计算用户租借点
   *
   * @return
   */
  private int frequentRenterPointCount() {
    int frequentRenterPoints = 0;

    for (Rental each : rentals) {
      // 租借点计算
      frequentRenterPoints = each.frequentRenterCount();
    }
    return frequentRenterPoints;
  }
}
