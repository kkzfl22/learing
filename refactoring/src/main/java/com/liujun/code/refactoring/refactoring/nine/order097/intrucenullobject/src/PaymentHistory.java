package com.liujun.code.refactoring.refactoring.nine.order097.intrucenullobject.src;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 支付历史
 *
 * @author liujun
 * @version 0.0.1
 */
public class PaymentHistory {

  int getWeekDelinquentInLastYear() {
    return ThreadLocalRandom.current().nextInt();
  }
}
