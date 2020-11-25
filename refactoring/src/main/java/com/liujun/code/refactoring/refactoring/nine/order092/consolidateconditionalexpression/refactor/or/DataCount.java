package com.liujun.code.refactoring.refactoring.nine.order092.consolidateconditionalexpression.refactor.or;

/**
 * 数据原始计算操作
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataCount {

  private final int condition1 = 0;

  private final int condition2 = 1;

  private final int condition3 = 2;

  public int dataCount() {
    if (dataCheck()) {
      return 0;
    }

    System.out.println(".........");
    return -1;
  }

  private boolean dataCheck() {
    return condition1 > 0 || condition2 > 1 || condition3 > 0;
  }
}
