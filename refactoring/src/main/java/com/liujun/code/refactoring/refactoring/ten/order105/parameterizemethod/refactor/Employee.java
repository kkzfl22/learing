package com.liujun.code.refactoring.refactoring.ten.order105.parameterizemethod.refactor;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Employee {

  private float salary = ThreadLocalRandom.current().nextFloat();

  void raise(double factor) {
    salary *= (1 + factor);
  }
}
