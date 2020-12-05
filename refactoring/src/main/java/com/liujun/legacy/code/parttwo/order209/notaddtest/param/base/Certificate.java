package com.liujun.legacy.code.parttwo.order209.notaddtest.param.base;

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
public class Certificate {

  private String name;

  public Certificate(String name) {
    this.name = name;
  }
}
