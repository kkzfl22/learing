package com.liujun.code.refactoring.refactoring.eleven.order106.extractsubclass.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class JobItem {

  private int unitPrice;

  private int quantity;

  public JobItem(int unitPrice, int quantity) {
    this.unitPrice = unitPrice;
    this.quantity = quantity;
  }

  public int getTotalPrice() {
    return getUnitPrice() * quantity;
  }

  public int getUnitPrice() {
    return unitPrice;
  }

  protected boolean isLabor() {
    return false;
  }

  public int getQuantity() {
    return quantity;
  }
}
