package com.liujun.code.refactoring.refactoring.eleven.order102.pullupmethod.src;

import java.util.Date;

/**
 * @author liujun
 * @version 0.0.1
 */
public class PreferredCustomer extends Customer {

  void createBill(Date data) {
    double chargeAmount = chargeFor(getLastBillDate(), data);
    this.addBill(data);
  }

  private double chargeFor(long lastBillDate, Date date) {
    System.out.println("last :" + lastBillDate + date);
    return lastBillDate + 11;
  }
}
