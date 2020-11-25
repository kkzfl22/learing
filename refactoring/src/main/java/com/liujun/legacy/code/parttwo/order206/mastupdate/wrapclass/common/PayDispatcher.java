package com.liujun.legacy.code.parttwo.order206.mastupdate.wrapclass.common;

/**
 * 执行显示操作
 *
 * @author liujun
 * @version 0.0.1
 */
public class PayDispatcher {

  public void pay(long dateTime, Memory amount) {
    System.out.println("执行工资的支付操作");
    System.out.println(dateTime);
    System.out.println(amount);
  }
}
