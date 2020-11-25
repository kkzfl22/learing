package com.liujun.code.refactoring.refactoring.ten.order105.parameterizemethod.src;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Employee {

  private float salary = ThreadLocalRandom.current().nextFloat();

  void tenPercentRaise() {
    salary *= 1.1;
  }

  void fivePercentRaise() {
    salary *= 1.05;
  }


}
