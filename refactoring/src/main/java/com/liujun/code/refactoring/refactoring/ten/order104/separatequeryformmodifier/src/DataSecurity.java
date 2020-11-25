package com.liujun.code.refactoring.refactoring.ten.order104.separatequeryformmodifier.src;

/**
 * 人入侵安全系统，它会告诉我入侵者的名称，并发送一个警报，如果入侵者不止一个，也只发送一条警报。
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataSecurity {

  public String foundMiscreant(String[] people) {
    for (int i = 0; i < people.length; i++) {
      if (people[i].equals("DON")) {
        sendAlert();
        return "DON";
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

  public void checkSecurity(String[] people) {
    String found = foundMiscreant(people);
    someLaterCode(found);
  }

  public void someLaterCode(String found) {
    System.out.println("found:" + found);
  }
}
