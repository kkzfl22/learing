package com.liujun.code.refactoring.refactoring.nine.order095.replacenestedconditionalwithguardclauses.src;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 将条件返转的原始代码
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataCondition {

  private double income = ThreadLocalRandom.current().nextDouble();

  private double duration = ThreadLocalRandom.current().nextDouble();

  private static final double ADJ_FACTOR = 0.982;

  public double getAdjustedCapital() {
    double result = 0.0;

    if (capital() > 0.0) {
      if (intRate() > 0.0 && duration() > 0.0) {
        result = (income * duration) * ADJ_FACTOR;
      }
    }

    return result;
  }

  private double capital() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private double intRate() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private double duration() {
    return ThreadLocalRandom.current().nextDouble();
  }
}
