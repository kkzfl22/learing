package com.liujun.code.refactoring.refactoring.sex.order061.extractmethod.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 无局部变量的函数,待重构建的原始函数
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataDemo {

  private final String name;

  public DataDemo(String name) {
    this.name = name;
  }

  private List<Order> valueList = new ArrayList<>();

  public void add(Order order) {
    valueList.add(order);
  }

  /** 执行打印 */
  void printOwing() {
    Iterator<Order> e = valueList.iterator();

    double outstanding = 0.0;

    // print banner
    System.out.println("*************************************************");
    System.out.println("************** Customer Owes ********************");
    System.out.println("*************************************************");

    while (e.hasNext()) {
      Order order = e.next();
      outstanding += order.getAmount();
    }

    // print detail
    System.out.println("name:" + name);
    System.out.println("amount " + outstanding);
  }
}
