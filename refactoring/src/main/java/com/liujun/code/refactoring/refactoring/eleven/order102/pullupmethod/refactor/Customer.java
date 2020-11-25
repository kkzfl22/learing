package com.liujun.code.refactoring.refactoring.eleven.order102.pullupmethod.refactor;

import java.util.Date;

/**
 * 顾客
 *
 * @author liujun
 * @version 0.0.1
 */
public abstract class Customer {

  private long lastBillDate;

  /**
   * 公共方法上移至你类中
   *
   * @param data
   */
  void createBill(Date data) {
    double chargeAmount = chargeFor(getLastBillDate(), data);
    this.addBill(data);
  }

  public void addBill(Date data) {
    System.out.println("parent add bill");
  }

  /**
   * 抽取抽象方法，供子类实现
   *
   * @param lastBillDate
   * @param date
   * @return
   */
  protected abstract double chargeFor(long lastBillDate, Date date);

  public long getLastBillDate() {
    return lastBillDate;
  }
}
