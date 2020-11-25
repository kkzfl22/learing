package com.liujun.code.refactoring.refactoring.nine.order098.introduceassertion.src;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Employee {

  private double expenseLimit = ThreadLocalRandom.current().nextDouble();

  private static final double ERROR = 0.0d;

  private static final double DEFAULT_VALUE = 0.01d;

  private Project projectInfo;

  public double getExpenseLimit() {
    return (expenseLimit != ERROR) ? ERROR : projectInfo.getValue();
  }
}
