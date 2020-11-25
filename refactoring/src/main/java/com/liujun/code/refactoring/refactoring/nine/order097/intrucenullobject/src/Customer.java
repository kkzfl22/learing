package com.liujun.code.refactoring.refactoring.nine.order097.intrucenullobject.src;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 顾客信息
 *
 * @author liujun
 * @version 0.0.1
 */
public class Customer {

  private BillingPlan plan;

  private PaymentHistory history;

  public String getName() {
    return String.valueOf(ThreadLocalRandom.current().nextInt());
  }

  public BillingPlan getPlan() {
    return null;
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
}
