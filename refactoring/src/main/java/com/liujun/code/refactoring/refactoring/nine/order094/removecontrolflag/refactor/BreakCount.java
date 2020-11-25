package com.liujun.code.refactoring.refactoring.nine.order094.removecontrolflag.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class BreakCount {

  public void checkSecurity(String[] people) {

    for (int i = 0; i < people.length; i++) {

      if (people[i].equals("Don")) {
        sendAlert();
        // 使用break语句替换掉对变量的赋值
        break;
      }
      if (people[i].equals("John")) {
        sendAlert();
        break;
      }
    }
  }

  private void sendAlert() {
    System.out.println("alert");
  }
}
