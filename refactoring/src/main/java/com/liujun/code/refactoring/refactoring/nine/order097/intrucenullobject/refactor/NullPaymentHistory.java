package com.liujun.code.refactoring.refactoring.nine.order097.intrucenullobject.refactor;

/**
 * 历史对象
 *
 * @author liujun
 * @version 0.0.1
 */
public class NullPaymentHistory extends PaymentHistory implements Nullable {

  @Override
  public boolean isNull() {
    return true;
  }

  @Override
  int getWeekDelinquentInLastYear() {
    return 0;
  }
}
