package com.liujun.code.refactoring.refactoring.ten.order110.removesettingmethod.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Account {

  private final String id;

  public Account(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
