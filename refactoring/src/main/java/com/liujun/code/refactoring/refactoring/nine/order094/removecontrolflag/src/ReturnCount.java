package com.liujun.code.refactoring.refactoring.nine.order094.removecontrolflag.src;

/**
 * @author liujun
 * @version 0.0.1
 */
public class ReturnCount {

  public void checkSecurity(String[] people) {
    String found = "";

    for (int i = 0; i < people.length; i++) {
      if ("".equals(found)) {
        if (people[i].equals("Don")) {
          sendAlert();
          found = "Don";
        }
        if (people[i].equals("John")) {
          sendAlert();
          found = "John";
        }
      }
    }

    someLaterCode(found);
  }

  private void sendAlert() {
    System.out.println("alert");
  }

  private void someLaterCode(String found) {
    System.out.println("data : " + found);
  }
}
