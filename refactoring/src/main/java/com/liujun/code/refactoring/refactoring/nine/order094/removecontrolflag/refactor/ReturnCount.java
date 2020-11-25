package com.liujun.code.refactoring.refactoring.nine.order094.removecontrolflag.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class ReturnCount {

  public void checkSecurity(String[] people) {
    String found = foundMiscreant(people);
    someLaterCode(found);
  }

  /**
   * 对方法执行重构，提供出查找的相关的方法
   *
   * @param people
   * @return
   */
  private String foundMiscreant(String[] people) {

    for (int i = 0; i < people.length; i++) {
      if (people[i].equals("Don")) {
        sendAlert();
        return "Don";
      }
      if (people[i].equals("John")) {
        sendAlert();
        return "John";
      }
    }
    return "";
  }

  private void sendAlert() {
    System.out.println("alert");
  }

  private void someLaterCode(String found) {
    System.out.println("data : " + found);
  }
}
