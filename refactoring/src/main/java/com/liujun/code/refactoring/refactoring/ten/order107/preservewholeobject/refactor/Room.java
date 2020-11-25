package com.liujun.code.refactoring.refactoring.ten.order107.preservewholeobject.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Room {

  private TempRange daysTempRange;

  boolean withinPlan(HeatingPlan plan) {
    return plan.withinRang(daysTempRange);
  }
}
