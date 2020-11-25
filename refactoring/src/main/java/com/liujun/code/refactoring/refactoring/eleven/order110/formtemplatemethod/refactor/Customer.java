package com.liujun.code.refactoring.refactoring.eleven.order110.formtemplatemethod.refactor;

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
public abstract class Customer {

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
    return new TextStatement().value(this);
  }

  /**
   * 生成详单的函数
   *
   * @return
   */
  public String htmlStatement() {
    return new HtmlStatement().value(this);
  }

  /**
   * 提取计算方法
   *
   * @return 值的计算
   */
  protected double countTotalAmount() {
    double totalAmount = 0;

    for (Rental each : rentals) {
      // 调用方法进行积分的计算
      totalAmount += each.countAmount();
    }

    return totalAmount;
  }

  public List<Rental> getRentals() {
    return rentals;
  }
}
