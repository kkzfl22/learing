package com.liujun.code.refactoring.refactoring.ten.order107.preservewholeobject.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class HeatingPlan {

  private TempRange range;

  /**
   * 添加参数项
   *
   * @param roomRange
   * @return
   */
  boolean withinRang(TempRange roomRange) {
    return range.includes(roomRange);
  }
}
