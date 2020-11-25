package com.liujun.code.refactoring.refactoring.eleven.order106.extractsubclass.src;

/**
 * @author liujun
 * @version 0.0.1
 */
public class JobItem {

  private int unitPrice;

  private int quantity;

  private boolean isLabor;

  private Employee employee;

  public JobItem(int unitPrice, int quantity, boolean isLabor, Employee employee) {
    this.unitPrice = unitPrice;
    this.quantity = quantity;
    this.isLabor = isLabor;
    this.employee = employee;
  }

  public int getTotalPrice() {
    return getUnitPrice() * quantity;
  }

  public int getUnitPrice() {
    return (isLabor) ? employee.getRate() : unitPrice;
  }

  public int getQuantity() {
    return quantity;
  }

  public Employee getEmployee() {
    return employee;
  }
}
