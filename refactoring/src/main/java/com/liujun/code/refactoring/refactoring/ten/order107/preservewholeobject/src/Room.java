package com.liujun.code.refactoring.refactoring.ten.order107.preservewholeobject.src;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Room {

  private TempRange daysTempRange;

  boolean withinPlan(HeatingPlan plan) {
    int low = daysTempRange.getLow();
    int high = daysTempRange.getHight();
    return plan.withinRang(low, high);
  }
}
