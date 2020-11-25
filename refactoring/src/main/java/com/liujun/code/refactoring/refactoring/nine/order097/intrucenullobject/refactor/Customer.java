package com.liujun.code.refactoring.refactoring.nine.order097.intrucenullobject.refactor;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 顾客信息
 *
 * @author liujun
 * @version 0.0.1
 */
public class Customer implements Nullable {

  private BillingPlan plan;

  private PaymentHistory history;

  public static Customer newNull() {
    return new NullCustomer();
  }

  public String getName() {
    return String.valueOf(ThreadLocalRandom.current().nextInt());
  }

  public BillingPlan getPlan() {
    return plan;
  }

  public void setPlan(BillingPlan plan) {
    this.plan = plan;
  }

  public PaymentHistory getHistory() {
    return history;
  }

  public void setHistory(PaymentHistory history) {
    this.history = history;
  }

  @Override
  public boolean isNull() {
    return false;
  }
}
