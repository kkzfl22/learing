package com.liujun.code.refactoring.refactoring.nine.order095.replacenestedconditionalwithguardclauses.src;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author liujun
 * @version 0.0.1
 */
public class DataPayCount {

  double getPayAmount() {
    double result;

    if (isDead()) {
      result = deadAmount();
    } else {
      if (isSeparated()) {
        result = separatedAmount();
      } else {
        if (isRetired()) {
          result = retiredAmount();
        } else {
          result = normalPayAmount();
        }
      }
    }
    return result;
  }

  private boolean isDead() {
    return ThreadLocalRandom.current().nextBoolean();
  }

  private double deadAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private boolean isSeparated() {
    return ThreadLocalRandom.current().nextBoolean();
  }

  private double separatedAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private boolean isRetired() {
    return ThreadLocalRandom.current().nextBoolean();
  }

  private double retiredAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private double normalPayAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }
}
