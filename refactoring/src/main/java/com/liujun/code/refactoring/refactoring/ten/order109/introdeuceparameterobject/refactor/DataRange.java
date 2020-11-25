package com.liujun.code.refactoring.refactoring.ten.order109.introdeuceparameterobject.refactor;

import java.util.Date;

/**
 * 表示范围
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataRange {

  private final Date start;

  private final Date end;

  public DataRange(Date start, Date end) {
    this.start = start;
    this.end = end;
  }

  public Date getStart() {
    return start;
  }

  public Date getEnd() {
    return end;
  }

  /**
   * 提取表达式函数，并转换移方法
   *
   * @param item
   * @return
   */
  public boolean include(Date item) {
    return item.equals(this.start)
        || item.equals(this.end)
        || (item.after(this.start) && item.before(this.end));
  }
}
