package com.liujun.code.refactoring.refactoring.ten.order109.introdeuceparameterobject.refactor;

import java.util.Date;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Client {

  public static void main(String[] args) {
    Account dataAccount = new Account();

    Date start = new Date();
    Date end = new Date();

    dataAccount.getFlowBetween(new DataRange(start, end));
  }
}
