package com.liujun.code.refactoring.refactoring.ten.order109.introdeuceparameterobject.src;

import java.sql.Date;

/**
 * 使用帐目与帐项的范例
 *
 * @author liujun
 * @version 0.0.1
 */
public class Entry {

  private Date chargeDate;

  private double value;

  public Entry(Date chargeDate, double value) {
    this.chargeDate = chargeDate;
    this.value = value;
  }

  public Date getChargeDate() {
    return chargeDate;
  }

  public double getValue() {
    return value;
  }
}
