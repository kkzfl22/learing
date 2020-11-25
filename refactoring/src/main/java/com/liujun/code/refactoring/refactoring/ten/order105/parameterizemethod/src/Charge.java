package com.liujun.code.refactoring.refactoring.ten.order105.parameterizemethod.src;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Charge {

  private double lastUsage() {
    return ThreadLocalRandom.current().nextDouble();
  }

  protected Dollars baseCharge() {
    double result = Math.min(lastUsage(), 100) * 0.03;
    if (lastUsage() > 100) {
      result += (Math.min(lastUsage(), 200) - 100) * 0.05;
    }
    if (lastUsage() > 200) {
      result = (lastUsage() - 200) * 0.07;
    }
    return new Dollars(result);
  }
}

class Dollars {
  private final double data;

  public Dollars(double data) {
    this.data = data;
  }
}
