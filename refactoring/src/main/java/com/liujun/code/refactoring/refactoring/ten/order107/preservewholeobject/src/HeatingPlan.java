package com.liujun.code.refactoring.refactoring.ten.order107.preservewholeobject.src;

/**
 * @author liujun
 * @version 0.0.1
 */
public class HeatingPlan {

  private TempRange range;

  boolean withinRang(int low, int high) {
    return low >= range.getLow() && high <= range.getHight();
  }
}
