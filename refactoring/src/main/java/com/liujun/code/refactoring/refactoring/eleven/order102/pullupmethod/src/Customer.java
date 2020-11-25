package com.liujun.code.refactoring.refactoring.eleven.order102.pullupmethod.src;

import java.util.Date;

/**
 * 顾客
 *
 * @author liujun
 * @version 0.0.1
 */
public class Customer {

  private long lastBillDate;

  public void addBill(Date data) {
    System.out.println("parent add bill");
  }

  public long getLastBillDate() {
    return lastBillDate;
  }
}
