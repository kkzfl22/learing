package com.liujun.legacy.code.parttwo.order209.notaddtest.param.nullpattern.base;

import org.junit.jupiter.api.Test;

/**
 * @author liujun
 * @version 0.0.1
 */
public class TestCustomerFactory {

  @Test
  public void testCustomer() {
    CustomerFactory factory = new CustomerFactory();

    for (int i = 0; i < 6; i++) {
      RealDataQuery customer = factory.query(String.valueOf(i));
      System.out.println(customer.query());
    }
  }
}
