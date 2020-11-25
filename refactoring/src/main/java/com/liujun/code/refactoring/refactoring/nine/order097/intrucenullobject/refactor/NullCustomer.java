package com.liujun.code.refactoring.refactoring.nine.order097.intrucenullobject.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class NullCustomer extends Customer implements Nullable {

  @Override
  public PaymentHistory getHistory() {
    return PaymentHistory.newNull();
  }

  @Override
  public boolean isNull() {
    return true;
  }

  @Override
  public String getName() {
    return "occupant";
  }

  @Override
  public void setPlan(BillingPlan plan) {}
}
