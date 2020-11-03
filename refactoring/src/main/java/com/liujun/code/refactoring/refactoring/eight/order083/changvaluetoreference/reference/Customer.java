package com.liujun.code.refactoring.refactoring.eight.order083.changvaluetoreference.reference;

import java.util.HashMap;
import java.util.Map;

/**
 * 客户的概念
 *
 * @author liujun
 * @version 0.0.1
 */
public class Customer {

  private static Map<String, Customer> DATA_INSTANCE = new HashMap<>(4);

  private final String name;

  static void loadCustomers() {
    new Customer("Lemon Car Hire ").store();
    new Customer("Associate Coffee Machines").store();
    new Customer("Bilston Gasworks").store();
  }

  private void store() {
    DATA_INSTANCE.put(this.getName(), this);
  }

  /**
   * 工厂函数
   *
   * @param name
   * @return
   */
  public static Customer getNamed(String name) {
    return DATA_INSTANCE.get(name);
  }

  private Customer(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
