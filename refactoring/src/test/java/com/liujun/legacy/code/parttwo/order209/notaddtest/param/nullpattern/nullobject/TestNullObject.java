package com.liujun.legacy.code.parttwo.order209.notaddtest.param.nullpattern.nullobject;

import org.junit.jupiter.api.Test;

/**
 * @author liujun
 * @version 0.0.1
 */
public class TestNullObject {

  @Test
  public void testCustomer() {
    CustomerFactory factory = new CustomerFactory();

    for (int i = 0; i < 6; i++) {
      CustomerInf customer = factory.query(String.valueOf(i));
      System.out.println(customer.query());
    }
  }

  @Test
  public void testCustomerCount() {
    CustomerFactory factory = new CustomerFactory();

    int countNum = 0;
    for (int i = 0; i < 6; i++) {
      CustomerInf customer = factory.query(String.valueOf(i));
      System.out.println(customer.query());
      countNum++;
    }
    System.out.println(countNum);
  }
}
