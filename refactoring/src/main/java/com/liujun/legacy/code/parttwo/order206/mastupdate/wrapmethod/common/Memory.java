package com.liujun.legacy.code.parttwo.order206.mastupdate.wrapmethod.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 工资信息
 *
 * @author liujun
 * @version 0.0.1
 */
@Getter
@Setter
@ToString
public class Memory {

  private long memory;

  public void add(long value) {
    this.memory += value;
  }
}
