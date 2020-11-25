package com.liujun.code.refactoring.refactoring.nine.order093.consolidateduplicateconditionalfragments.src;

/**
 * @author liujun
 * @version 0.0.1
 */
public class DataCount {

  private double total = 10;

  private int price = 20;

  public void dataCount() {

    if (dataCheck()) {
      total = price * 0.98;
      send();
    } else {
      total = price * 0.95;
      send();
    }
  }

  private boolean dataCheck() {
    return true;
  }

  private void send() {
    System.out.println("send");
  }
}
