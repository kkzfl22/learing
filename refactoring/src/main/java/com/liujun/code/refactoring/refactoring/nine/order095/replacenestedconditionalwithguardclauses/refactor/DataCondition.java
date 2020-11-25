package com.liujun.code.refactoring.refactoring.nine.order095.replacenestedconditionalwithguardclauses.refactor;

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

    // 将条件返转，去掉了一层判断
    if (capital() <= 0.0) {
      return 0.0;
    }
    // 去掉取反操作
    if (intRate() <= 0.0 || duration() <= 0.0) {
      return 0.0;
    }

    return (income * duration) * ADJ_FACTOR;
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
