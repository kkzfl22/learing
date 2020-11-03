package com.liujun.code.refactoring.refactoring.sex.order061.extractmethod.src;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Order {

  /**
   * 获取订单的相关数据
   *
   * @return
   */
  public double getAmount() {
    return ThreadLocalRandom.current().nextDouble();
  }
}
