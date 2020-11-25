package com.liujun.code.refactoring.refactoring.nine.order097.intrucenullobject.src;

/**
 * @author liujun
 * @version 0.0.1
 */
public class SiteClient {

  public void doInvoke(Site site) {
    Customer customer = site.getCustomer();
    BillingPlan plan;
    if (customer == null) {
      plan = BillingPlan.basic();
    } else {
      plan = customer.getPlan();
    }
    customer.setPlan(plan);

    String customerName;
    if (customer == null) {
      customerName = "occupant";
    } else {
      customerName = customer.getName();
    }

    int weeksdelinquent;
    if (customer == null) {
      weeksdelinquent = 0;
    } else {
      weeksdelinquent = customer.getHistory().getWeekDelinquentInLastYear();
    }

    System.out.println(plan);
    System.out.println(customerName);
    System.out.println(weeksdelinquent);
  }
}
