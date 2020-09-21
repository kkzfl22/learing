package com.liujun.code.refactoring.refactoring.sex.order061.extractmethod.referactoring;

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

  void printOwing() {
    // print banner
    printBanner();

    // 计算函数分离
    double outstanding = countOutStand();

    // print detail
    printDetail(outstanding);
  }

  /** 提取打印方法 */
  private void printBanner() {
    System.out.println("*************************************************");
    System.out.println("************** Customer Owes ********************");
    System.out.println("*************************************************");
  }

  /**
   * 提练带参数的函数，需要携带参数
   *
   * @param outstanding
   */
  private void printDetail(double outstanding) {
    // print detail
    System.out.println("name:" + name);
    System.out.println("amount " + outstanding);
  }

  /**
   * 计算函数分离
   *
   * @return
   */
  private double countOutStand() {
    Iterator<Order> e = valueList.iterator();
    double result = 0.0;

    while (e.hasNext()) {
      Order order = e.next();
      result += order.getAmount();
    }

    return result;
  }
}
