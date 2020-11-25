package com.liujun.code.refactoring.refactoring.ten.order107.preservewholeobject.refactor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author liujun
 * @version 0.0.1
 */
@Getter
@Setter
@ToString
public class TempRange {

  private int low;

  private int hight;

  /**
   * 添加参数项
   *
   * @param roomRange
   * @return
   */
  boolean includes(TempRange roomRange) {
    return roomRange.getLow() >= this.getLow() && roomRange.getHight() <= this.getHight();
  }
}
