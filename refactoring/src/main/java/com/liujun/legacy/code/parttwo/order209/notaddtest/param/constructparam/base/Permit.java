package com.liujun.legacy.code.parttwo.order209.notaddtest.param.constructparam.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author liujun
 * @version 0.0.1
 */
@Getter
@Setter
@ToString
public class Permit {

  /** 用户名 */
  private String userName;

  public Permit(String userName) {
    this.userName = userName;
  }

  public boolean isValid() {
    return ThreadLocalRandom.current().nextBoolean();
  }

  public void validate() {
    System.out.println("执行验证...");
  }

  public void dataRun() {
    System.out.println("业务执行..");
  }
}
