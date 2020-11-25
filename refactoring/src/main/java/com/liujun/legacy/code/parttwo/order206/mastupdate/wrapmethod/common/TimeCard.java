package com.liujun.legacy.code.parttwo.order206.mastupdate.wrapmethod.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liujun
 * @version 0.0.1
 */
@Getter
@Setter
@ToString
public class TimeCard {

  /** 工作的小时数 */
  private int hours;

  /** 薪水时间 */
  private List<Integer> amount = new ArrayList<>();

  public void add(int amount) {
    this.amount.add(amount);
  }
}
