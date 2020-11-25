package com.liujun.code.refactoring.refactoring.ten.order110.removesettingmethod.refactor;

/**
 * 对传入的参数进行运算
 *
 * @author liujun
 * @version 0.0.1
 */
public class AccountCount {

  private final String id;

  public AccountCount(String id) {
    this.id = this.initializedIdValue(id);
  }

  public String initializedIdValue(String id) {
    return "ZZ" + id;
  }

  public String getId() {
    return id;
  }
}
