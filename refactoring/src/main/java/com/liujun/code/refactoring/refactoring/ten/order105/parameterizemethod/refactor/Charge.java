package com.liujun.code.refactoring.refactoring.ten.order105.parameterizemethod.refactor;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Charge {

  private double value = ThreadLocalRandom.current().nextDouble();;

  private double lastUsage() {
    return value;
  }

  protected Dollars baseCharge() {
    double result = Math.min(lastUsage(), 100) * 0.03;
    result += usageInRange(100, 200) * 0.05;
    result += usageInRange(200, Integer.MAX_VALUE) * 0.07;
    return new Dollars(result);
  }

  private int usageInRange(int start, int end) {
    if (lastUsage() > start) {
      return (int) Math.min(lastUsage(), end) - start;
    }

    return 0;
  }
}

class Dollars {
  private final double data;

  public Dollars(double data) {
    this.data = data;
  }
}
