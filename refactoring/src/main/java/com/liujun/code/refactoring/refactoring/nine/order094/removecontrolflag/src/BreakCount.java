package com.liujun.code.refactoring.refactoring.nine.order094.removecontrolflag.src;

/**
 * 执行以break语句取代简单的控制标记
 *
 * @author liujun
 * @version 0.0.1
 */
public class BreakCount {

  public void checkSecurity(String[] people) {
    boolean found = false;

    for (int i = 0; i < people.length; i++) {
      if (!found) {
        if (people[i].equals("Don")) {
          sendAlert();
          found = true;
        }
        if (people[i].equals("John")) {
          sendAlert();
          found = true;
        }
      }
    }
  }

  private void sendAlert() {
    System.out.println("alert");
  }
}
