package com.liujun.code.refactoring.refactoring.nine.order097.intrucenullobject.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class SiteClient {

  public void doInvoke(Site site) {
    Customer customer = site.getCustomer();

    if (customer.isNull()) {
      customer = NullCustomer.newNull();
    }

    System.out.println(customer.getPlan());
    System.out.println(customer.getName());
    System.out.println(customer.getHistory().getWeekDelinquentInLastYear());
  }
}
