package com.liujun.legacy.code.parttwo.order209.notaddtest.param.dependtask.base;

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
public class MeetingResolver {

  /** 名称 */
  private String name;

  /** 年龄信息 */
  private String age;

  public MeetingResolver(String name, String age) {
    this.name = name;
    this.age = age;
  }
}
