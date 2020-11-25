package com.liujun.code.refactoring.refactoring.nine.order095.replacenestedconditionalwithguardclauses.refactor;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author liujun
 * @version 0.0.1
 */
public class DataPayCount {

  double getPayAmount() {

    // 使用卫语句，对代码进行改造
    if (isDead()) {
      return deadAmount();
    }
    // 去掉else进行检查
    if (isSeparated()) {
      return separatedAmount();
    }

    // 继续检查
    if (isRetired()) {
      return retiredAmount();
    }

    return normalPayAmount();
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
