package com.liujun.code.refactoring.refactoring.nine.order098.introduceassertion.refactor;

import com.liujun.code.refactoring.refactoring.nine.order098.introduceassertion.src.Project;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 使用java的运行参数-ea即可运行
 *
 * @author liujun
 * @version 0.0.1
 */
public class Employee {

  private double expenseLimit = ThreadLocalRandom.current().nextDouble();

  private static final double ERROR = 0.0d;

  private Project projectInfo;

  public double getExpenseLimit() {

    // 代码中存在两个假设条件，第一个就是参数检查，还有一个就是project不能为空,必须满足其一
    assert expenseLimit != ERROR || projectInfo != null;

    return (expenseLimit != ERROR) ? ERROR : projectInfo.getValue();
  }
}
