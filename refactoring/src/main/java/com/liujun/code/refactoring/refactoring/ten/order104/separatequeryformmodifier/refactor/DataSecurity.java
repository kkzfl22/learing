package com.liujun.code.refactoring.refactoring.ten.order104.separatequeryformmodifier.refactor;

/**
 * 人入侵安全系统，它会告诉我入侵者的名称，并发送一个警报，如果入侵者不止一个，也只发送一条警报。
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataSecurity {

  public void sendAlert(String[] people) {
    if (!"".equals(foundPerson(people))) {
      sendAlert();
    }
  }

  private String foundPerson(String[] people) {
    for (int i = 0; i < people.length; i++) {
      if (people[i].equals("DON")) {
        return "DON";
      }
      if (people[i].equals("John")) {
        return "John";
      }
    }
    return "";
  }

  private void sendAlert() {
    System.out.println("alert");
  }

  public void checkSecurity(String[] people) {
    sendAlert(people);
    // 原原单一调用修改为调用两个函数
    String found = this.foundPerson(people);
    someLaterCode(found);

    int[][] value = new int[3][3];
    value[0] = new int[]{1,6,2};
    value[1] = new int[]{4,10,9};
    value[2] = new int[]{7,3,6};
  }

  public void someLaterCode(String found) {
    System.out.println("found:" + found);
  }
}
