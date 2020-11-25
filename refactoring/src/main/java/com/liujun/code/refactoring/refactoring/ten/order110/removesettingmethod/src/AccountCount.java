package com.liujun.code.refactoring.refactoring.ten.order110.removesettingmethod.src;

/**
 * 对传入的参数进行运算
 *
 * @author liujun
 * @version 0.0.1
 */
public class AccountCount {

  private String id;

  public AccountCount(String id) {
    this.setId(id);
  }

  public void setId(String id) {
    this.id = "ZZ" + id;
  }

  public String getId() {
    return id;
  }
}
